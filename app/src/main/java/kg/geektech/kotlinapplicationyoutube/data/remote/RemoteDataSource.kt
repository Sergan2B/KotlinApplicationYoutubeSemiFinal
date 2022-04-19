package kg.geektech.kotlinapplicationyoutube.data.remote

import kg.geektech.kotlinapplicationyoutube.BuildConfig.API_KEY
import kg.geektech.kotlinapplicationyoutube.core.network.BaseDataSource
import kg.geektech.kotlinapplicationyoutube.utils.Constant.channelId
import kg.geektech.kotlinapplicationyoutube.utils.Constant.maxResult
import kg.geektech.kotlinapplicationyoutube.utils.Constant.part

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(part, channelId, API_KEY, maxResult)
    }

    suspend fun getDetailPlaylist(id: String) = getResult {
        apiService.getDetailPLayList(part, id, API_KEY)
    }

    suspend fun getVideo(id: String) = getResult {
        apiService.getPlaylistVideo(part, id, API_KEY)
    }
}