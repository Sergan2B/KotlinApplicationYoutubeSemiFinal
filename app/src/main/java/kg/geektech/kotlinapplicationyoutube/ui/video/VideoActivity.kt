package kg.geektech.kotlinapplicationyoutube.ui.video

import android.media.session.MediaController
import android.net.Uri
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import kg.geektech.kotlinapplicationhomework3.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.core.network.result.Status
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseActivity
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityVideoBinding
import kg.geektech.kotlinapplicationyoutube.remote.model.PlayListItem
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {
    override val viewModel: VideoViewModel by viewModel()
    private lateinit var video: PlaylistModel

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()

        viewModel.getVideo(intent.getStringExtra(PlaylistActivity.PLAYLIST).toString())
            .observe(this) {
                when (it.status) {
                    Status.ERROR -> {
                        it.msg?.let { it1 -> showToast(it1)}
                    }
                    Status.SUCCESS -> {
                        if (it.data != null) with(binding) {
                            video = it.data
                            tvDetailPlaylistTitle.text = video.items[0].snippet.title
                            tvDetailPlaylistSubtitle.text = video.items[0].snippet.description
                            tvDetailPlaylistSubtitle.movementMethod = ScrollingMovementMethod()
                            ivItemVideo.setVideoURI(Uri.parse(video.items[0].id))
                        }
                    }
                    Status.LOADING -> {
                    }
                }
            }
    }
}