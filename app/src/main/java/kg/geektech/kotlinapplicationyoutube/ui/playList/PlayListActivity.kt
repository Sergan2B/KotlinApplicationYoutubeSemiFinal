package kg.geektech.kotlinapplicationyoutube.ui.playList

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kg.geektech.kotlinapplicationyoutube.Base.BaseActivity
import kg.geektech.kotlinapplicationyoutube.databinding.ActivityPlayListBinding

/*Дз.
1. Создать свой ApiKey и ознакомиться с документацией  +++++
2. Добавить в класс playlist поле "items", отрисовать первых 2 экрана из фигмы
(Проверка на интернет, и список всех PlayList)
3. Сделать переход на новую активити и передаете туда id и её отображаете тостом

Также прочитайте про корутины желательно

Доп: в PlayListActivity попробуйте реализовать пагинацию с помощью ViewType с RecyclerView*/
class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlayListBinding>() {

    override val viewModel: PlayListViewModel by lazy {
        ViewModelProvider(this)[PlayListViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlists().observe(this) {
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(inflater)
    }
}