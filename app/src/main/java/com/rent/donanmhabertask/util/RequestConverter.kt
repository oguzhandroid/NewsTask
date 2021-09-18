package com.rent.donanmhabertask.util

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rent.donanmhabertask.data.model.Image

public class RequestConverter {
    @TypeConverter
    fun stringToOutboxItem(string: String): Image? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, Image::class.java)
    }

    @TypeConverter
     fun outboxItemToString(outboxItem: Image): String {
        return Gson().toJson(outboxItem)
    }

}