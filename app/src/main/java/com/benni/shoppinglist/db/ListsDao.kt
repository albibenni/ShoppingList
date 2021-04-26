package com.benni.shoppinglist.db

import androidx.room.*
import com.benni.shoppinglist.db.entities.Lists
import com.benni.shoppinglist.db.entities.ShoppingItems
import com.benni.shoppinglist.db.entities.relations.ListsAndShoppingItems

@Dao
interface ListsDao {

    //insert new list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: Lists)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(item: ShoppingItems)

    @Query("SELECT * FROM lists")
    suspend fun getLists(): List<Lists>

//    @Query("SELECT * FROM shoppingitems WHERE expiringdate = :currentDate")
//    suspend fun getExpiringDate(currentDate: Date): List<ShoppingItems>
    @Transaction
    @Query("SELECT * FROM lists")
    suspend fun getRelations(): List<ListsAndShoppingItems>
    //update tab items require relation with lists

    //insert new list -- double table insert and use transaction to syncronize the 2 tables

    //also needs to delete the items
//    @Delete
//    fun deleteList(listID: Int)



}