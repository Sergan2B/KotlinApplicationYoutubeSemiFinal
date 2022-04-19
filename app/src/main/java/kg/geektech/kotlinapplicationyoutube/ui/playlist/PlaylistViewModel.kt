package kg.geektech.kotlinapplicationyoutube.ui.playlist

import androidx.lifecycle.LiveData
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.data.local.AppPreferences
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.repository.Repository


class PlaylistViewModel(private val repository: Repository, private val prefs: AppPreferences) :
    BaseViewModel() {
    fun getPlayList(): LiveData<Resource<PlaylistModel>> {
        return repository.createCall()
    }

    fun setBoard(onBoard: Boolean) {
        prefs.onBoard = onBoard
    }
}