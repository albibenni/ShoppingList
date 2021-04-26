package com.benni.shoppinglist.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.benni.shoppinglist.db.entities.Lists
import com.benni.shoppinglist.db.entities.ShoppingItems

data class ListsAndShoppingItems (
    @Embedded val lists: Lists,
    @Relation(
        parentColumn = "listID",
        entityColumn = "listOwnID"
    )
    val shoppingItems: List<ShoppingItems>
)