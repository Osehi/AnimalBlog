package com.polishone.animalblog.common.data.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.polishone.animalblog.common.domain.model.Owner

/**
 * type converter for the owner
 */
class RoomTypeConverter {

    @TypeConverter
    fun ownerToString(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(str: String): Owner {
        return Gson().fromJson(str, Owner::class.java)
    }
}