package kg.geektech.kotlinapplicationyoutube.diffutils

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.kotlinapplicationyoutube.model.PlaylistModel

class PlaylistDiffUtil(
    private val oldList: List<PlaylistModel.PlayListItem>,
    private val newList: List<PlaylistModel.PlayListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldVideo = oldList[oldItemPosition]
        val newVideo = newList[newItemPosition]
        return oldVideo.snippet.title == newVideo.snippet.title
    }
}