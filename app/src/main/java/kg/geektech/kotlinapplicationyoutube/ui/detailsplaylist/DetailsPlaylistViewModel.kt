package kg.geektech.kotlinapplicationyoutube.ui.detailsplaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.data.local.AppPreferences
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.repository.Repository

class DetailsPlaylistViewModel(
    private val repository: Repository, private val prefs: AppPreferences
) : BaseViewModel() {
    fun getDetailPlayList(id: String): LiveData<Resource<PlaylistModel>> {
        return repository.createDetailCall(id)
    }

    fun getBoard(): LiveData<Boolean> {
        return MutableLiveData<Boolean>().apply {
            value = prefs.onBoard
        }
    }
}