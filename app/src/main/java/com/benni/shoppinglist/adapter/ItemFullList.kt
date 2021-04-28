package com.benni.shoppinglist.adapter

data class ItemFullList(
        val id: Int,
        val title: String,
        val author: String,
        var isChecked: Boolean
)