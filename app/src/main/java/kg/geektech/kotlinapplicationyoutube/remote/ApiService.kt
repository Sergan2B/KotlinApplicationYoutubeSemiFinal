package kg.geektech.kotlinapplicationyoutube.remote

import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int,
    ): Response<PlaylistModel>

    @GET("playlistItems")
    suspend fun getDetailPLayList(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String,
    ): Response<PlaylistModel>
}
