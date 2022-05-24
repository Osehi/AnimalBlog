package com.polishone.animalblog.common.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polishone.animalblog.common.constant.NetworkConstant
import com.polishone.animalblog.common.domain.model.entity.AniBlogEntity

@Database( entities = [AniBlogEntity::class, BlogKey::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class, ListOfStringToStringTypeConverter::class)
abstract class BlogDatabase : RoomDatabase(){

    abstract fun getBlogDAO(): BlogDAO

    companion object {
        fun getInstance(context: Context): BlogDatabase {
            return Room.databaseBuilder(context, BlogDatabase::class.java, NetworkConstant.DB_NAME)
                .build()
        }
    }

}