package kg.geektech.kotlinapplicationyoutube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.data.remote.RemoteDataSource
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun createCall(): LiveData<Resource<PlaylistModel>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlaylists()
        emit(response)
    }

    fun createDetailCall(id: String): LiveData<Resource<PlaylistModel>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetailPlaylist(id)
            emit(response)
        }

    fun createVideoCall(id: String): LiveData<Resource<PlaylistModel>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getVideo(id)
        emit(response)
    }
}