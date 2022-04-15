package kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist

import androidx.lifecycle.LiveData
import kg.geektech.App
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.repository.Repository
import retrofit2.Response

class DetailsPlaylistViewModel(private val repository: Repository) : BaseViewModel() {
    fun getDetailPlayList(id: String): LiveData<Resource<PlaylistModel?>> {
        return repository.createDetailCall(id)
    }
}