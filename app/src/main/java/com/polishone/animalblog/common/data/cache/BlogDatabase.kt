package com.polishone.animalblog.common.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.polishone.animalblog.common.constant.NetworkConstant

@Database( entities = [], version = 1, exportSchema = false)
abstract class BlogDatabase : RoomDatabase(){

    companion object {
        fun getInstance(context: Context): BlogDatabase {
            return Room.databaseBuilder(context, BlogDatabase::class.java, NetworkConstant.DB_NAME)
                .build()
        }
    }

}