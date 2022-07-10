package com.example.bitcointicker.util.toast_message_helper

import android.content.Context
import android.widget.Toast
import com.example.bitcointicker.util.Constants.UNEXPECTED_ERROR
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ToastMessageHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ToastMessageHelper {

    override
    fun showToastMessage(message: String?){
        Toast.makeText(context, message ?: UNEXPECTED_ERROR, Toast.LENGTH_SHORT).show()
    }
}