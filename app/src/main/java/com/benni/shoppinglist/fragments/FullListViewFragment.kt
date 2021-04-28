package com.benni.shoppinglist.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.benni.shoppinglist.Communicator
import com.benni.shoppinglist.R
import com.benni.shoppinglist.adapter.ItemEditList
import com.benni.shoppinglist.dataclass.ShoppingList
import com.benni.shoppinglist.adapter.ItemFullAdapter
import com.benni.shoppinglist.adapter.ItemFullList
import com.benni.shoppinglist.databinding.FragmentFullListViewBinding
import com.benni.shoppinglist.db.DataBase
import com.benni.shoppinglist.db.ListsDao
import com.benni.shoppinglist.db.entities.Lists
import com.benni.shoppinglist.db.entities.ShoppingItems
import com.benni.shoppinglist.db.entities.relations.ListsAndShoppingItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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


class FullListViewFragment : Fragment(), ItemFullAdapter.OnItemListener {
    private lateinit var listener: FullListViewFragment
    private lateinit var listEditFragment : ListEditFragment

    private lateinit var comunicator : Communicator //var for communicator interface


    private lateinit var dao: ListsDao

    private lateinit var binder: FragmentFullListViewBinding
    private lateinit var itemFullAdapter: ItemFullAdapter
    private lateinit var listConverted : MutableList<ItemFullList>

    companion object{
        private lateinit var listsAndShoppingItemsLIST : List<ListsAndShoppingItems>
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        listEditFragment = ListEditFragment()
        binder = FragmentFullListViewBinding
                .inflate(inflater, container, false)
        //COMMUNICATOR
//        comunicator = activity as Communicator

        //setClick item

        dao = DataBase
                .getInstance(activity!!.baseContext)
                .listsDao


        //TRANSACTION to listEditFragment ON CLICKING THE FLOATING BUTTON
        binder.floatingActionButton.setOnClickListener {
            transactionTo(listEditFragment)

            //TODO change redirect into create list fragment ---TO BE CREATED
        }

        //insert query here to avoid race conditions
        GlobalScope.launch {
//            shoppingLists.forEach { dao.insertList(it) }
//            shoppingItems.forEach { dao.insertList(it) } //to insert data
            listsAndShoppingItemsLIST = dao.getRelationsList() //Lists JOIN ShoppingItems
            listConverted = convertToItemFullList(listsAndShoppingItemsLIST)  //convert listsAndShoppingItems for the RecicleView OBJ
           //init Recicle view and add data
            initRecycleView()
            addDataSet(listConverted)

//            val shoppingListOfInterest = getList(listsAndShoppingItemsLIST, 1) //relation list with ID = 1
//            shoppingListOfInterest.forEach{Log.d("LISTID 1", it.item)} //log of relation list with ID = 1
//            listsAndShoppingItemsLIST.forEach { Log.d("LIST", it.lists.toString()) }
        }
        return binder.root
    }
    private fun addDataSet(items: MutableList<ItemFullList>){
        itemFullAdapter.submitList(items)

    }

    //init the recycle view
    private fun initRecycleView(){
        binder.shoppingListRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            itemFullAdapter = ItemFullAdapter(this@FullListViewFragment)
            adapter = itemFullAdapter
        }
    }
    //convert the list from the query to the object used for the recycle view
    private fun convertToItemFullList(lists: List<ListsAndShoppingItems>): MutableList<ItemFullList> {
        val localList = mutableListOf<ItemFullList>()
        lists.forEach {
            localList.add(ItemFullList(it.lists.listID, it.lists.title, it.lists.author,false))
        }
        return localList
    }
    //pick the clicked list
    private fun getSelectedShoppingList (lists: List<ListsAndShoppingItems>, id: Int): MutableList<ShoppingList> {
        val localList = mutableListOf<ShoppingList>()
        lists.forEach {
            if (it.lists.listID == id)
                it.shoppingItems.forEach {
                    localList
                            .add(ShoppingList(id, it.item, it.expiringDate))
                }
        }
        return localList
    }


    //general metod for changing fragment
    private fun transactionTo(fragment: Fragment){
        activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit()
    }

    //interface -- recycleview on click
    override fun onItemClick(position: Int) {
        Log.d("REC-CLICK", "Clicked")
        comunicator = activity as Communicator
        val listStringed = getSelectedShoppingList(listsAndShoppingItemsLIST, position).toString()
        Log.d("LISTPASSED", listStringed)

//        reConvertMessage(listStringed)
        Log.d("position", specialCharReplacer(listStringed))

        Log.d("position", position.toString())

        comunicator.passDataFullListToEditList(listStringed)

    }

    private fun spaces(listStringed: String){ //: MutableList<ItemEditList>
        val re = Regex(" ")
        val result = re.findAll(listStringed)
    }

    private fun specialCharReplacer (listStringed: String): String{
        val re = Regex("[^A-Za-z0-9 ]")
        val answer = re.replace(listStringed, "")
        return answer
    }

    private fun getID(range: Int, listStringed: String){
        val regex ="id=".toRegex()
        val found = regex.find(listStringed)
    }



}

