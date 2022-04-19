package kg.geektech.kotlinapplicationyoutube.ui.video

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.google.android.youtube.player.YouTubePlayer
import kg.geektech.kotlinapplicationyoutube.core.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.core.network.result.Status
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseActivity
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityVideoBinding
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistActivity
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatus
import kg.geektech.kotlinapplicationyoutube.utils.NetworkStatusHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {
    override val viewModel: VideoViewModel by viewModel()
    var youtubePlayer: YouTubePlayer? = null
    private lateinit var video: PlaylistModel
    private val networkStatusHelper: NetworkStatusHelper by inject()


    override fun initViewModel() {
        super.initViewModel()
        //  val manager = supportFragmentManager
        viewModel.loading.observe(this) { binding.progress.isVisible = it }
        viewModel.getVideo(intent.getStringExtra(PlaylistActivity.PLAYLIST_VIDEO).toString())
            .observe(this) {
                when (it.status) {
                    Status.ERROR -> it.msg?.let { it1 -> showToast(it1) }
                    Status.SUCCESS -> {
                        if (it.data != null) with(binding) {
                            video = it.data
                            tvVideoTitle.text = video.items[0].snippet.title
                            tvVideoSubtitle.text = video.items[0].snippet.description
                            tvVideoSubtitle.movementMethod = ScrollingMovementMethod()
                            /*val transaction = manager.beginTransaction()
                            val fragment = VideoFragment()
                            val id14 = video.items[0].contentDetail.videoId
                            fragment.arguments = bundleOf("token14" to id14)
                            transaction.replace(R.id.video_fragment_1, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()*/
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

    private fun checkInternetMethod() {
        var status: Boolean
        networkStatusHelper.observe(this) {
            status = false
            if (it == NetworkStatus.Available && !status) {
                binding.connection.parentConnection.isVisible = false
            } else {
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

    override fun initListener() {
        super.initListener()
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(inflater)
    }
}