package porikuch.bottleswitch

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import porikuch.bottleswitch.R.id.sendMessageButton

class MessageSwitchRecyclerAdapter(private val image: Int, private val listener: ListListener) : RecyclerView.Adapter<MessageSwitchRecyclerAdapter.ViewHolder>() {

    private var messageSwitchList: MutableList<MessageSwitch> = mutableListOf()

    fun messageSwitchListAdd(messageSwitch: MessageSwitch) {
        this.messageSwitchList.add(messageSwitch)
    }

    fun messageSwitchClear() {
        this.messageSwitchList = arrayListOf()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemSendMessageButton: Button
        var itemCreateBottleButton: Button
        //var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemSendMessageButton = itemView.findViewById(R.id.sendMessageButton)
            itemCreateBottleButton = itemView.findViewById(R.id.createBottleButton)
            //itemDetail = itemView.findViewById(R.id.item_detail)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.message_switch_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        //viewHolder.itemTitle.text = titles[i]
        //viewHolder.itemDetail.text = details[i]
        //viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemTitle.text = messageSwitchList[i].nickname
        //viewHolder.itemDetail.text = ""
        viewHolder.itemImage.setImageResource(image)
        viewHolder.itemSendMessageButton.setOnClickListener {
            listener.onClickSendMessageButton(it, messageSwitchList[i])
        }
        viewHolder.itemCreateBottleButton.setOnClickListener{
            listener.onClickCreateBottleButton(it, messageSwitchList[i])
        }
        viewHolder.itemView.setOnClickListener {
            listener.onClickRow(it, messageSwitchList[i])
        }
    }

    override fun getItemCount(): Int {
        return messageSwitchList.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, messageSwitch: MessageSwitch)
        fun onClickSendMessageButton(tappedView: View, messageSwitch: MessageSwitch)
        fun onClickCreateBottleButton(tappedView: View, messageSwitch: MessageSwitch)
    }
}