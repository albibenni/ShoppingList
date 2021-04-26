package com.benni.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benni.shoppinglist.db.entities.Lists
import com.benni.shoppinglist.db.entities.ShoppingItems

@Database(
       entities = [
            Lists::class,
            ShoppingItems::class,
       ],
        version = 1
)

abstract class DataBase : RoomDatabase(){

    abstract val listsDao: ListsDao

    companion object{

        //for the racecondition
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "shopping_list"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}