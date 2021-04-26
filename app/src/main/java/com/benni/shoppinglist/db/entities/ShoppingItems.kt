package com.benni.shoppinglist.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ShoppingItems (
    @PrimaryKey(autoGenerate = true) val itemID: Int,
    val listOwnID: Int,
    val expiringDate: String,    //format yyyy-MM-dd
    @ColumnInfo(name = "item_name") val item: String
    )