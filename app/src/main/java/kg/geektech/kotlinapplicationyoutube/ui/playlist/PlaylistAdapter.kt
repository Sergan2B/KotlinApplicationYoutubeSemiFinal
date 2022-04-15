package kg.geektech.kotlinapplicationyoutube.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlinapplicationhomework3.extentions.load
import kg.geektech.kotlinapplicationyoutube.R
import kg.geektech.kotlinapplicationyoutube.databinding.ItemPlayListBinding
import kg.geektech.kotlinapplicationyoutube.remote.model.PlayListItem
import kg.geektech.kotlinapplicationyoutube.remote.model.PlaylistModel

class PlaylistAdapter(
    private val playList: PlaylistModel,
    private val initClick: (id: PlayListItem) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistHolderOne>() {

    inner class PlaylistHolderOne(item: View) : RecyclerView.ViewHolder(item) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolderOne {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return PlaylistHolderOne(view)
    }

    override fun onBindViewHolder(holderOne: PlaylistHolderOne, position: Int) {
        holderOne.bind(playList.items[position])
    }

    override fun getItemCount() = playList.items.size

    /* fun setData(newList: List<PlayListItem>, rv: RecyclerView) {
         val playlistDiff = PlaylistDiffUtil(oldItems, newList)
         val diff = DiffUtil.calculateDiff(playlistDiff)
         oldItems.addAll(newList)
         diff.dispatchUpdatesTo(this)
         rv.scrollToPosition(oldItems.size - newList.size)
     }*/
}