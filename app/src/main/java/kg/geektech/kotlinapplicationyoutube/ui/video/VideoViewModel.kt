package kg.geektech.kotlinapplicationyoutube.ui.video

import androidx.lifecycle.LiveData
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.core.ui.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.repository.Repository

class VideoViewModel(private val repository: Repository) : BaseViewModel() {
    fun getVideo(id: String): LiveData<Resource<PlaylistModel>> {
        return repository.createVideoCall(id)
    }
}