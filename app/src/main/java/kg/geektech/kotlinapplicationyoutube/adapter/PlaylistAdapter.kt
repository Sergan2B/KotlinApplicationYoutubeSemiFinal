package kg.geektech.kotlinapplicationyoutube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationhomework3.extentions.load
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.databinding.ItemPlayListBinding
import kg.geektech.kotlinapplicationyoutube.diffutils.PlaylistDiffUtil
import kg.geektech.kotlinapplicationyoutube.model.PlaylistModel

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {
    private val oldItems = ArrayList<PlaylistModel.PlayListItem>()

    class PlaylistHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPlayListBinding.bind(item)

        fun bind(playList: PlaylistModel.PlayListItem) = with(binding) {
            binding.tvTitle.text = playList.snippet.title
            val videoCount = "${playList.contentDetail.itemCount} видео"
            tvUnderTitle.text = videoCount
            imagePlaylist.load(playList.snippet.thumbnails.high.url)
            imagePlaylistShadow.alpha - 0.5f
            imagePlaylistShadow.animate().alpha(0.5f).duration = 200

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return PlaylistHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.bind(oldItems[position])
    }

    override fun getItemCount() = oldItems.size

    fun setData(newList: List<PlaylistModel.PlayListItem>, rv: RecyclerView) {
        val playlistDiff = PlaylistDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(playlistDiff)
        oldItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
        rv.scrollToPosition(oldItems.size - newList.size)
    }
}