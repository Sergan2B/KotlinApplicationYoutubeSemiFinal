package kg.geektech.kotlinapplicationyoutube.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import kg.geektech.kotlinapplicationhomework3.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.Base.BaseActivity
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityThirdBinding
import kg.geektech.kotlinapplicationyoutube.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.ui.playlist.PlaylistViewModel

class Third : BaseActivity<PlaylistViewModel, ActivityThirdBinding>() {
    private lateinit var playList: PlaylistModel.PlayListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun initView() {
        super.initView()
        viewModel.playlist.observe(this) {
            binding.tvIdThird.text = playList.id
            showToast(playList.id + "")
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityThirdBinding {
        return ActivityThirdBinding.inflate(inflater)
    }
}