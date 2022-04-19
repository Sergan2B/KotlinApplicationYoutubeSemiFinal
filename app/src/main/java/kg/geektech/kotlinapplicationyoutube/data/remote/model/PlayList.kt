package kg.geektech.kotlinapplicationyoutube.data.remote.model

import com.google.gson.annotations.SerializedName

data class PageInfoX(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class PlaylistModel(
    val pageToken: String,
    val pageInfo: PageInfoX,
    val items: List<PlayListItem>
)

data class PlayListItem(
    val id: String,
    val snippet: Snippet,
    @SerializedName("contentDetails")
    val contentDetail: ContentDetail
)

data class ContentDetail(
    val itemCount: Int,
    val videoId: String,
    val videoPublishedAt: String, //"2022-04-11T17:41:32Z"
    val caption: String,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String

)

data class Snippet(
    val title: String,
    val description: String,
    val customUri: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsY,
    val country: String,
    val playlistId: String,

    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val defaultAudioLanguage: String,
    val liveBroadcastContent: String,
    val tags: List<String>,
)

data class Default(
    var url: String,
    var width: Int,
    var height: Int
)

data class ThumbnailsY(
    var default: Default,
    var high: High
)

data class High(
    val url: String
)

