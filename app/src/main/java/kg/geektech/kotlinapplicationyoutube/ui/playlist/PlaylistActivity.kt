package kg.geektech.kotlinapplicationyoutube.ui.playlist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.kotlinapplicationhomework3.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.core.network.result.Status
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseActivity
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityPlaylistBinding
import kg.geektech.kotlinapplicationyoutube.remote.model.PlayListItem
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist.DetailsPlaylist
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatus.Available
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private lateinit var playList: PlaylistModel
    private val adapter by lazy { PlaylistAdapter(playList, this::initClick) }
    override val viewModel: PlaylistViewModel by viewModel()


    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecycler() {
        binding.recyclerPlayList.apply {
            layoutManager = LinearLayoutManager(this@PlaylistActivity)
            adapter = this@PlaylistActivity.adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this) {
            binding.progress.isVisible = it
            binding.progressBar.isVisible = it
        }
        viewModel.getPlayList().observe(this) {
            when (it.status) {
                Status.ERROR -> {
                    it.msg?.let { it1 -> showToast(it1) }
                    viewModel.loading.postValue(false)
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        playList = it.data
                        viewModel.loading.postValue(false)
                        setupRecycler()
                    }
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        checkInternetMethod()
    }

    private fun checkInternetMethod() {
        NetworkStatusHelper(this@PlaylistActivity).observe(this) {
            if (it == Available) {
                binding.connection.parentConnection.isVisible = false
            } else {
                binding.connection.parentConnection.isVisible = true
                binding.connection.button.setOnClickListener {
                    if (Available == Available) {
                        binding.connection.parentConnection.isVisible = false
                    }
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }

    private fun initClick(id: PlayListItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(this, DetailsPlaylist::class.java).apply {
                putExtra(PLAYLIST, id.id)
                startActivity(this)
            }
        }
    }

    companion object {
        const val PLAYLIST = "playlist"
    }
}
