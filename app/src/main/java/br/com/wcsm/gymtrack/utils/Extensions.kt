package br.com.wcsm.gymtrack.utils

import android.app.Activity
import android.widget.Toast

fun Activity.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}