package kg.geektech.kotlinapplicationyoutube.model

import android.icu.text.CaseMap
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Snippet (
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("customUri")
    val customUri: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsY,
    @SerializedName("country")
    val country: String,
        )