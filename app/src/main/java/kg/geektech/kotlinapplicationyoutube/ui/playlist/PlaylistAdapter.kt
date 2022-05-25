package kg.geektech.kotlinapplicationyoutube.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.core.extentions.load
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlayListItem
import kg.geektech.kotlinapplicationyoutube.data.remote.model.PlaylistModel
import kg.geektech.kotlinapplicationyoutube.databinding.ItemPlayListBinding

class PlaylistAdapter(
    private val playList: PlaylistModel,
    private val initClick: (id: PlayListItem) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playList.items[position])
    }

    override fun getItemCount() = playList.items.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPlayListBinding.bind(item)

        fun bind(playList: PlayListItem) = with(binding) {
            tvTitle.text = playList.snippet.title
            val videoCount = playList.contentDetail.itemCount.toString() + " видео"
            tvUnderTitle.text = videoCount
            imagePlaylist.load(playList.snippet.thumbnails.high.url)
            itemView.setOnClickListener {
                initClick(playList)
            }
        }
    }
}