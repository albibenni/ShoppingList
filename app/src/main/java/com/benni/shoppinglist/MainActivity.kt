package com.benni.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benni.shoppinglist.databinding.ActivityMainBinding
import com.benni.shoppinglist.dataclass.ShoppingList
import com.benni.shoppinglist.fragments.FullListViewFragment
import com.benni.shoppinglist.fragments.ListEditFragment


class MainActivity : AppCompatActivity(), Communicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        //viewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FRAGMENTS
        val fullListViewFragment = FullListViewFragment()
        val listEditFragment = ListEditFragment()

        //MANAGE FRAGMENT
        //entry fragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, fullListViewFragment)
                .commit()




        //FRAGMENT2 TRANSACTION
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment, listEditFragment)
//            commit()
//        }


    }

    override fun passDataFullListToEditList(shoppingList: String) {
        val bundle = Bundle()
        bundle.putCharSequence("message", shoppingList)
        val trnasaction = this.supportFragmentManager.beginTransaction()
        val listEditFragment = ListEditFragment()
        listEditFragment.arguments = bundle
        trnasaction.replace(R.id.flFragment, listEditFragment).commit()

    }
}