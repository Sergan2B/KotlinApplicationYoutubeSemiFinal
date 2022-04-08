package kg.geektech.kotlinapplicationyoutube.model

import com.google.gson.annotations.SerializedName

data class ThumbnailsY(
    @SerializedName("high")
    val high: High
) {
    data class High(
        @SerializedName("url")
        val url: String
    )
}