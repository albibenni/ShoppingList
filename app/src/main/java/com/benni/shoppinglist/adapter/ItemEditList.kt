package com.benni.shoppinglist.adapter

data class ItemEditList(
        val listID: Int,
        val shoppingListID: Int,
        val title: String,
        val itemName: String,
        val author: String,
        val expiringDate: String,
        var isChecked: Boolean
)