package com.rent.donanmhabertask.util

import android.content.Context
import android.widget.Toast

class ToastHelper {
    companion object {
        fun makeToast(context: Context,message: String) {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}