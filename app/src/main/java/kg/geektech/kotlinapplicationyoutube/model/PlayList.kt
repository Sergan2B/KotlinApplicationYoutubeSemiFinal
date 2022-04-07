package kg.geektech.kotlinapplicationyoutube.model

import com.google.gson.annotations.SerializedName

data class PlayList(
    val kind: String? = null,
    @SerializedName("etag")
    val tag: String? = null
)