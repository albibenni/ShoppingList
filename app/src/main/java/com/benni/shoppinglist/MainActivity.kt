package com.benni.shoppinglist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benni.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fullListViewFragment)
            commit()
        }


        //FRAGMENT2 TRANSACTION
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment, listEditFragment)
//            commit()
//        }


    }
}