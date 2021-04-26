package com.benni.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.benni.shoppinglist.databinding.FragmentListEditViewBinding


class ListEditFragment : Fragment(), FullListViewFragment.FullListViewFragmentListener {
    private var binder: FragmentListEditViewBinding? = null
    private val binding get() = binder!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binder = FragmentListEditViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }
}