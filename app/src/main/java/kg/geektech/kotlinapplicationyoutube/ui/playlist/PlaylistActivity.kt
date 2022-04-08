package kg.geektech.kotlinapplicationyoutube.ui.playlist

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationhomework3.extentions.showToast
import kg.geektech.kotlinapplicationyoutube.Base.BaseActivity
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.adapter.PlaylistAdapter
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityPlayListBinding
import kg.geektech.kotlinapplicationyoutube.remote.isNetworkAvailable
import kg.geektech.kotlinapplicationyoutube.ui.Third

/*Дз.
1. Создать свой ApiKey и ознакомиться с документацией  +++++
2. Добавить в класс playlist поле "items", отрисовать первых 2 экрана из фигмы
(Проверка на интернет, и список всех PlayList)  +++++
3. Сделать переход на новую активити и передаете туда id и её отображаете тостом ++

Также прочитайте про корутины желательно

Доп: в PlayListActivity попробуйте реализовать пагинацию с помощью ViewType с RecyclerView ++++ */
class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlayListBinding>() {

    private val adapter = PlaylistAdapter()
    private var isLoading = false
    private var isScroll = false
    private var currentItem = -1
    private var totalItem = -1
    private var scrollOutItem = -1
    private var isAllVideoLoaded = false

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun initView() {
        super.initView()
        val manager = LinearLayoutManager(this@PlaylistActivity)
        binding.recyclerPlayList.layoutManager = manager
        binding.recyclerPlayList.adapter = adapter


        binding.recyclerPlayList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScroll = true
                }
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()
                if (isScroll && (currentItem + scrollOutItem == totalItem)) {
                    isScroll = false
                    if (!isLoading) {
                        if (!isAllVideoLoaded && isNetworkAvailable(this@PlaylistActivity)) {
                            viewModel.getPlaylists()
                        } else {
                            setContentView(R.layout.blank_screen)
                            val button: Button = findViewById(R.id.button)
                            button.setOnClickListener {
                                if (isNetworkAvailable(this@PlaylistActivity)) {
                                    setContentView(R.layout.activity_play_list)
                                    showToast("вава")
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun checkInternet() {
        super.checkInternet()
        checkInternetIn()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetIn() {
        if (!isNetworkAvailable(this)) {
            setContentView(R.layout.blank_screen)
            val button: Button = findViewById(R.id.button)
            button.setOnClickListener {
                if (isNetworkAvailable(this)) {
                    setContentView(R.layout.activity_play_list)
                    showToast("вава")
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlist.observe(this) {
            adapter.setData(it?.items!!, binding.recyclerPlayList)
        }

        viewModel.isLoading.observe(this) {
            isLoading = it
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.isAllPlaylistLoaded.observe(this) {
            isAllVideoLoaded = it
        }
    }

    override fun initListener() {
        super.initListener()
        binding.buttonGoThird.setOnClickListener {
            startActivity(Intent(this@PlaylistActivity, Third::class.java))
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(inflater)
    }
}