package kg.geektech.kotlinapplicationyoutube.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PlaylistModel(
    @SerializedName("nextPageToken")
    val nextPageToken: String,

    @SerializedName("items")
    val items:List<PlayListItem>
){
    data class PlayListItem(
        @SerializedName("id")
        val id: String,

        @SerializedName("snippet")
        val snippet: Snippet,

        @SerializedName("contentDetails")
        val contentDetail: ContentDetail
    )

    data class ContentDetail(
        @SerializedName("itemCount")
        val itemCount: Int
    )
}