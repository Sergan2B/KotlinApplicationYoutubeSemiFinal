package kg.geektech.kotlinapplicationyoutube.ui.playList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.kotlinapplicationyoutube.Base.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.BuildConfig.API_KEY
import kg.geektech.kotlinapplicationyoutube.`object`.Constant
import kg.geektech.kotlinapplicationyoutube.model.PlayList
import kg.geektech.kotlinapplicationyoutube.remote.ApiService
import kg.geektech.kotlinapplicationyoutube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<PlayList> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<PlayList> {
        val liveData = MutableLiveData<PlayList>()
        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY, Constant.maxResult)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful) {
                        liveData.value = response.body()
                    }
                    //200-299
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {

                }
            })
        return liveData
    }
}