package com.benni.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benni.shoppinglist.R
import kotlinx.android.synthetic.main.item_of_edit_list.view.*
import java.util.ArrayList


class ItemEditAdapter (): RecyclerView.Adapter<ItemEditAdapter.ViewHolder>() {
    private var items: MutableList<ItemEditList> = ArrayList()


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.tvItemEditList
        val expringDate: TextView = itemView.tvExpiringDateEditList
        val checkBox: CheckBox = itemView.cbItemEditList

        fun bind (item: ItemEditList){
            itemName.setText(item.itemName)
            expringDate.setText(item.expiringDate)
            checkBox.isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_edit_list, parent, false)) //false to no auto attach data to the root
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder){
            is ViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    fun submitList(datalist: MutableList<ItemEditList>) {
        this.items = datalist
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemEditListener{
        fun onItemClick(position: Int)
    }
}

