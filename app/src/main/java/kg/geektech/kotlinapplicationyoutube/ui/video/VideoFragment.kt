package kg.geektech.kotlinapplicationyoutube.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kg.geektech.kotlinapplicationyoutube.BuildConfig
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseFragment
import kg.geektech.kotlinapplicationyoutube.databinding.FragmentVideoBinding

class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    private lateinit var youtubePlayer: YouTubePlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.youtubePlayerVideo.initialize(BuildConfig.API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                val key = arguments?.getString("token14", "")
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    if (p1 != null) {
                        youtubePlayer = p1
                        youtubePlayer.loadVideo(key)
                        youtubePlayer.play()
                    }
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                }
            })
    }

    override fun inflateVB(inflater: LayoutInflater): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater)
    }
}