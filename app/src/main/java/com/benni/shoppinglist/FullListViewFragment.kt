package com.benni.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benni.shoppinglist.databinding.FragmentFullListViewBinding

class FullListViewFragment : Fragment() {
    private var binder: FragmentFullListViewBinding? = null
    private val binding get() = binder!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val listEditFragment = ListEditFragment()
        binder = FragmentFullListViewBinding.inflate(inflater, container, false)


        //TRANSACTION to listEditFragment ON CLICKING THE FLOATING BUTTON
        binding.floatingActionButton.setOnClickListener {
            Log.d("floating button", "Clicked")
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.flFragment, listEditFragment).commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }


}