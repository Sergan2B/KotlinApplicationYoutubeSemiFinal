package kg.geektech.kotlinapplicationyoutube.ui.video

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.core.view.isVisible
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
    private val networkStatusHelper: NetworkStatusHelper by inject()
    private lateinit var video: PlaylistModel

    override fun initViewModel() {
        super.initViewModel()
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