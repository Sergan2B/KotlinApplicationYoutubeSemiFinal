package kg.geektech.kotlinapplicationyoutube.ui.playlist

import androidx.lifecycle.LiveData
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.repository.Repository


class PlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getPlayList(): LiveData<Resource<PlaylistModel>> {
        return repository.createCall()
    }
}