package kg.geektech.kotlinapplicationyoutube.ui.playList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationyoutube.Base.BaseActivity
import kg.geektech.kotlinapplicationyoutube.adapter.PlaylistAdapter
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityPlayListBinding

/*Дз.
1. Создать свой ApiKey и ознакомиться с документацией  +++++
2. Добавить в класс playlist поле "items", отрисовать первых 2 экрана из фигмы
(Проверка на интернет, и список всех PlayList)
3. Сделать переход на новую активити и передаете туда id и её отображаете тостом

Также прочитайте про корутины желательно

Доп: в PlayListActivity попробуйте реализовать пагинацию с помощью ViewType с RecyclerView*/
class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlayListBinding>() {

    private val adapter = PlaylistAdapter()
    private var isLoading = false
    private var isScroll = false
    private var currentItem = -1
    private var totalItem = -1
    private var scrollOutItem = -1
    private var isAllVideoLoaded = false
    val manager = LinearLayoutManager(this@PlaylistActivity)

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun initView() {
        super.initView()
        binding.recyclerPlayList.layoutManager = manager
        binding.recyclerPlayList.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()

        binding.recyclerPlayList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()
                if (isScroll && (currentItem + scrollOutItem == totalItem)) {
                    isScroll = false
                    if (!isLoading) {
                        if (!isAllVideoLoaded) {
                            viewModel.playlist
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "All playlist loaded",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        })

        viewModel.playlist.observe(this) {
            adapter.setData(it?.items!!, binding.recyclerPlayList)
            it.nextPageToken.let { token ->
                Log.e("next page token", token)
            }
        }

        viewModel.isLoading.observe(this) {
            isLoading = it
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.isAllPlaylistLoaded.observe(this) {
            isAllVideoLoaded = it
            if (it) Toast.makeText(this, "All playlist has been loaded", Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(inflater)
    }
}