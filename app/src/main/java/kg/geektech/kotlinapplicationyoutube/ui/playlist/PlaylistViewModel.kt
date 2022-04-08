package kg.geektech.kotlinapplicationyoutube.ui.playlist

import androidx.lifecycle.MutableLiveData
import kg.geektech.kotlinapplicationyoutube.Base.BaseViewModel
import kg.geektech.kotlinapplicationyoutube.BuildConfig.API_KEY
import kg.geektech.kotlinapplicationyoutube.`object`.Constant
import kg.geektech.kotlinapplicationyoutube.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.remote.ApiService
import kg.geektech.kotlinapplicationyoutube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {
    private val _playlist = MutableLiveData<PlaylistModel?>()
    val playlist = _playlist

    /*private var _id = "" починка
    val id = MutableLiveData<String>()*/
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllPlaylistLoaded = MutableLiveData<Boolean>()
    val isAllPlaylistLoaded = _isAllPlaylistLoaded
    var nextPageToken: String? = null

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    init {
        getPlaylists()
    }

    fun getPlaylists() {
        apiService.getPlaylists(
            Constant.part,
            Constant.channelId,
            API_KEY,
            Constant.maxResult,
            nextPageToken
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(
                call: Call<PlaylistModel>,
                response: Response<PlaylistModel>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        nextPageToken = data.nextPageToken
                        if (data.items.isNotEmpty()) {
                            _playlist.value = data
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}