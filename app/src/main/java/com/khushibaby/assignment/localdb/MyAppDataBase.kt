package com.khushibaby.assignment.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khushibaby.assignment.model.MovieDataClass

@Database(entities = [MovieDataClass::class], version = 1)
abstract class MyAppDataBase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: MyAppDataBase? = null

        fun getDatabase(context: Context): MyAppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyAppDataBase::class.java,
                    "my_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
