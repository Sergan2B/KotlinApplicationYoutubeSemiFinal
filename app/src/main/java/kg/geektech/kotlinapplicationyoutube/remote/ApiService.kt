package kg.geektech.kotlinapplicationyoutube.remote

import kg.geektech.kotlinapplicationyoutube.model.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("part") apiKey: String,
        @Query("maxResult") maxResult: Int,
    ): Call<PlayList>
}