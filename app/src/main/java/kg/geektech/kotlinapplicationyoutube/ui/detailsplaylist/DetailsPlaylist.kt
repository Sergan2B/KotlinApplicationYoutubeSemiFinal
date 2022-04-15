package kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist

import android.annotation.SuppressLint
import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.kotlinapplicationhomework3.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.core.network.result.Status
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseActivity
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityDetailsPlaylistBinding
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistActivity.Companion.PLAYLIST
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.O)
class DetailsPlaylist : BaseActivity<DetailsPlaylistViewModel, ActivityDetailsPlaylistBinding>() {
    private lateinit var detailList: PlaylistModel
    override val viewModel: DetailsPlaylistViewModel by viewModel()
    private val detailAdapter: DetailsPlaylistAdapter by lazy {
        DetailsPlaylistAdapter(detailList)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailsPlaylistBinding {
        return ActivityDetailsPlaylistBinding.inflate(inflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        viewModel.getDetailPlayList(intent.getStringExtra(PLAYLIST).toString()).observe(this) {
            when (it.status) {
                Status.ERROR -> {
                    it.msg?.let { it1 -> showToast(it1) }
                }
                Status.SUCCESS -> {
                    if (it.data != null) with(binding) {
                        detailList = it.data
                        tvDetailPlaylistTitle.text = detailList.items[0].snippet.title
                        tvDetailPlaylistSubtitle.text = detailList.items[0].snippet.description
                        tvDetailPlaylistSubtitle.movementMethod = ScrollingMovementMethod()
                        setupRecycler()
                    }
                }
                Status.LOADING -> {
                }
            }
        }
        val itemCount = intent.getIntExtra("key_item_count", 0)
        binding.tvDetailPlaylistTitle.text = title
        binding.tvDetailSeries.text = "$itemCount  ${getString(R.string.video_series)}"
    }

    private fun setupRecycler() {
        binding.recyclerDetail.apply {
            layoutManager = LinearLayoutManager(this@DetailsPlaylist)
            adapter = this@DetailsPlaylist.detailAdapter
        }
    }
}