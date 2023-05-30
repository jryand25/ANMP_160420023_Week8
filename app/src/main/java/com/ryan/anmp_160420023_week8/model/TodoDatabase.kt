package com.ryan.anmp_160420023_week8.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    companion object{
        @Volatile private var instance: TodoDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(contex:Context) =
            Room.databaseBuilder(
                contex.applicationContext,
                TodoDatabase::class.java,
                "newtododb"
            ).build()

        operator fun invoke(contex:Context){
            if(instance != null){
                synchronized(LOCK){
                    instance ?: buildDatabase(contex).also {
                        instance = it
                    }
                }
            }
        }
    }
}