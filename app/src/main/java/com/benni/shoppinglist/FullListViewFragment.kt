package com.benni.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.benni.shoppinglist.databinding.FragmentFullListViewBinding
import com.benni.shoppinglist.db.DataBase
import com.benni.shoppinglist.db.ListsDao
import com.benni.shoppinglist.db.entities.Lists
import com.benni.shoppinglist.db.entities.ShoppingItems
import kotlinx.coroutines.launch

class FullListViewFragment : Fragment() {
    private lateinit var listener: FullListViewFragment
    private lateinit var binder: FragmentFullListViewBinding
    private val binding get() = binder
    private lateinit var dao: ListsDao


    interface FullListViewFragmentListener {

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val listEditFragment = ListEditFragment()
        binder = FragmentFullListViewBinding.inflate(inflater, container, false)

        val shoppingLists = listOf<Lists>(
                Lists(1, "spesa1", "albi"),
                Lists(2, "spesa2", "albi"),
                Lists(3, "spesa3", "albi1"),
                Lists(4, "spesa4", "albi3"),
                Lists(5, "spesa5", "albi2")
        )
        val shoppingItems = listOf<ShoppingItems>(
                ShoppingItems(1, 1, "2021-04-28", "pollo"),
                ShoppingItems(2, 1, "2021-04-28", "pollo"),
                ShoppingItems(3, 2, "2021-04-28", "pollo"),
                ShoppingItems(4, 2, "2021-04-28", "pollo"),
                ShoppingItems(5, 2, "2021-04-28", "pollo"),
                ShoppingItems(6, 4, "2021-04-28", "pollo")
        )
        dao = DataBase.getInstance(activity!!.baseContext).listsDao


        //TRANSACTION to listEditFragment ON CLICKING THE FLOATING BUTTON
        binding.floatingActionButton.setOnClickListener {
            Log.d("floating button", "Clicked")
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.flFragment, listEditFragment).commit()
        }
        //insert query here to avoid race conditions
        lifecycleScope.launch {
//            shoppingLists.forEach { dao.insertList(it) }
//            shoppingItems.forEach { dao.insertList(it) }

            val listsAndShoppingItems = dao.getRelations()
//            listsAndShoppingItems.first().lists
            listsAndShoppingItems.forEach { Log.d("LISTandSHOPPING", it.toString()) }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }


}