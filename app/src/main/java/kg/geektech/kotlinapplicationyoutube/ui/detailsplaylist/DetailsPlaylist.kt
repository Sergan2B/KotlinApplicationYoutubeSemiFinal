package kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist

import android.content.Intent
import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import kg.geektech.kotlinapplicationyoutube.core.extentions.checkLog
import kg.geektech.kotlinapplicationyoutube.core.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.core.network.result.Status
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseActivity
import kg.geektech.kotlinapplicationyoutube.data.remote.model.ContentDetail
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityDetailsPlaylistBinding
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistActivity.Companion.PLAYLIST
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistActivity.Companion.PLAYLIST_VIDEO
import kg.geektech.kotlinapplicationyoutube.ui.video.VideoActivity
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatus
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class DetailsPlaylist : BaseActivity<DetailsPlaylistViewModel, ActivityDetailsPlaylistBinding>() {
    override val viewModel: DetailsPlaylistViewModel by viewModel()
    private lateinit var detailList: PlaylistModel
    private val networkStatusHelper: NetworkStatusHelper by inject()
    private val detailAdapter: DetailsPlaylistAdapter by lazy {
        DetailsPlaylistAdapter(detailList, this::initClick)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getBoard().observe(this) { checkLog("lol", it.toString()) }
        viewModel.getDetailPlayList(intent.getStringExtra(PLAYLIST).toString())
            .observe(this) { it ->
                viewModel.loading.observe(this) { binding.progress.isVisible = it }
                when (it.status) {
                    Status.ERROR -> it.msg?.let { it1 -> showToast(it1) }
                    Status.SUCCESS -> {
                        if (it.data != null) with(binding) {
                            detailList = it.data
                            tvDetailPlaylistTitle.text = detailList.items[0].snippet.title
                            tvDetailPlaylistSubtitle.text = detailList.items[0].snippet.description
                            tvDetailPlaylistSubtitle.movementMethod = ScrollingMovementMethod()
                            (detailList.items.size.toString() + getString(R.string.video)).also { it1 ->
                                binding.tvDetailSeries.text = it1
                            } // Примечание 2
                            setupRecycler()
                        }
                    }
                    Status.LOADING -> viewModel.loading.postValue(true)
                }
            }
    }

    override fun checkInternet() {
        super.checkInternet()
        checkInternetMethod()
    }


    override fun initListener() {
        super.initListener()
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initClick(id: ContentDetail) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra(PLAYLIST_VIDEO, id.videoId)
            startActivity(this)
        }
    }

    private fun setupRecycler() {
        binding.recyclerDetail.apply {
            layoutManager = LinearLayoutManager(this@DetailsPlaylist)
            adapter = this@DetailsPlaylist.detailAdapter
        }
    }

    private fun checkInternetMethod() {
        var status: Boolean
        networkStatusHelper.observe(this) {
            status = false
            if (it == NetworkStatus.Available && !status) binding.connection.parentConnection.isVisible =
                false
            else {
                status = true
                binding.connection.parentConnection.isVisible = true
                binding.connection.button.setOnClickListener {
                    if (NetworkStatus.Available == NetworkStatus.Available) {
                        binding.connection.parentConnection.isVisible = false
                    }
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailsPlaylistBinding {
        return ActivityDetailsPlaylistBinding.inflate(inflater)
    }
}