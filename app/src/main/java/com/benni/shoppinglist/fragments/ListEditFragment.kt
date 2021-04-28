package com.benni.shoppinglist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.benni.shoppinglist.adapter.ItemEditAdapter
import com.benni.shoppinglist.adapter.ItemEditList
import com.benni.shoppinglist.adapter.ItemFullAdapter
import com.benni.shoppinglist.adapter.ItemFullList
import com.benni.shoppinglist.databinding.FragmentListEditViewBinding
import kotlinx.coroutines.launch


class ListEditFragment : Fragment(){
    private lateinit var binder: FragmentListEditViewBinding

    private lateinit var messageReceiver : String

    private lateinit var itemEditAdapter: ItemEditAdapter

    private lateinit var listConverted: MutableList<ItemEditList>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binder = FragmentListEditViewBinding
                .inflate(inflater, container, false)


        messageReceiver = arguments?.getString("message")!!

        Log.d("MESSAGE", messageReceiver)
//
//        lifecycleScope.launch{
//            messageReceiver[0]
//            initRecycleView()
//            addDataSet(listConverted)
//        }



        return binder.root
    }

    private fun addDataSet(items: MutableList<ItemEditList>){
        itemEditAdapter.submitList(items)

    }
    //init the recycle view
    private fun initRecycleView(){
        binder.editShoppingListRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            itemEditAdapter = ItemEditAdapter()
            adapter = itemEditAdapter
        }
    }
}