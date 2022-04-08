package kg.geektech.kotlinapplicationyoutube.remote

import kg.geektech.kotlinapplicationyoutube.model.PlayList
import kg.geektech.kotlinapplicationyoutube.model.PlaylistModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResult") maxResult: Int,
        @Query("pageToken") pageToken: String?
    ): Call<PlaylistModel>

    @GET("search")
    fun getVideo(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order: String,
    ): Call<PlayList>
}