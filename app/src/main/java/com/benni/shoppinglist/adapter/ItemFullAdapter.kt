package com.benni.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benni.shoppinglist.R
import kotlinx.android.synthetic.main.item_of_full_list.view.*
import java.util.ArrayList


class ItemFullAdapter (val clickListener: OnItemListener): RecyclerView.Adapter<ItemFullAdapter.ViewHolder>() {
    private var items: MutableList<ItemFullList> = ArrayList()


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val listTitle: TextView = itemView.tvItemEditList
        val listAuthor: TextView = itemView.tvExpiringDateEditList
        val listCB: CheckBox = itemView.cbItemEditList
        //        val view = itemView.setOnClickListener{}

        fun bind (item: ItemFullList, action: OnItemListener){
            listTitle.setText(item.title)
            listAuthor.setText(item.author)
            listCB.isChecked

            itemView.setOnClickListener {
                action.onItemClick(absoluteAdapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_full_list, parent, false)) //false to no auto attach data to the root
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder){
            is ViewHolder ->{
                holder.bind(items[position],clickListener)
            }
        }
    }

    fun submitList(datalist: MutableList<ItemFullList>) {
        this.items = datalist
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemListener{
        fun onItemClick(position: Int)
    }
}

