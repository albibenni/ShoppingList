package com.benni.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benni.shoppinglist.databinding.FragmentFullListViewBinding

class InsertListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binder = FragmentFullListViewBinding.inflate(inflater, container, false)


        return binder.root
    }
}