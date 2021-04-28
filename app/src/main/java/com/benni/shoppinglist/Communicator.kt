package com.benni.shoppinglist

import com.benni.shoppinglist.dataclass.ShoppingList
import com.benni.shoppinglist.db.entities.relations.ListsAndShoppingItems

interface Communicator {
    fun passDataFullListToEditList (shoppingList: String)
}