package kg.geektech.kotlinapplicationyoutube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geektech.kotlinapplicationyoutube.BuildConfig.API_KEY
import kg.geektech.kotlinapplicationyoutube.core.network.result.Resource
import kg.geektech.kotlinapplicationyoutube.remote.ApiService
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.utils.Constant
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService) {

    fun createCall(): LiveData<Resource<PlaylistModel?>> = liveData(Dispatchers.IO) {
        val response =
            apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY, Constant.maxResult)
        if (response.isSuccessful && response.body() != null) emit(Resource.success(response.body()))
        else emit(Resource.error(response.message(), response.body()))
    }

    fun createDetailCall(id: String): LiveData<Resource<PlaylistModel?>> =
        liveData(Dispatchers.IO) {
            val response =
                apiService.getDetailPLayList(Constant.part, id, API_KEY, Constant.maxResult)
            if (response.isSuccessful && response.body() != null) emit(Resource.success(response.body()))
            else emit(Resource.error(response.message(), response.body()))
        }
}