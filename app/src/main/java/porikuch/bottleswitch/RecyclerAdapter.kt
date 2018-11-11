package porikuch.bottleswitch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val titles = arrayOf("Let's be friends with me !",
            "Chapter Two", "Chapter Three", "Chapter Four",
            "Chapter Five", "Chapter Six", "Chapter Seven",
            "Chapter Eight")

    private val details = arrayOf("Item one details", "Item two details",
            "Item three details", "Item four details",
            "Item file details", "Item six details",
            "Item seven details", "Item eight details")

    private val images = intArrayOf(R.drawable.bottle,
            R.drawable.bottle, R.drawable.bottle,
            R.drawable.bottle, R.drawable.bottle,
            R.drawable.bottle, R.drawable.bottle,
            R.drawable.bottle)

    private var bottleList: MutableList<BottleSwitch> = mutableListOf()

    fun bottleListAdd(bottleSwitch: BottleSwitch) {
        this.bottleList.add(bottleSwitch)
    }

    fun bottleClear() {
        this.bottleList = arrayListOf()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var bottleId: Int = 0

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        //viewHolder.itemTitle.text = titles[i]
        //viewHolder.itemDetail.text = details[i]
        //viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemTitle.text = bottleList[i].title
        viewHolder.itemDetail.text = bottleList[i].nickname
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return bottleList.size
    }
}