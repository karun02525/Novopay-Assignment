package com.novopay.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.novopay.R
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun log(s:String){
    Log.d("TAGS", "log: $s")
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showSnack(message: String): Snackbar {
    val sb = Snackbar.make((this as Activity).findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_LONG)
    sb.view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    val textView = sb.view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.RED)
    sb.show()
    return sb
}

fun ImageView.loadImage(url:String){
    Glide.with(context)
        .load(url)
        .into(this)
}



fun String.dateToTimeFormat(): String? {
    val p = PrettyTime(Locale(getCountry()!!))
    var isTime: String? = null
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val date = sdf.parse(this)
        isTime = p.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return isTime
}

@SuppressLint("SimpleDateFormat")
fun String.dateFormat(): String? {
    val newDate: String?
    val dateFormat = SimpleDateFormat(
        "E, d MMM yyyy",
        Locale(getCountry()!!)
    )
    newDate = try {
        val date =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this)
        dateFormat.format(date!!)
    } catch (e: ParseException) {
        e.printStackTrace()
        this
    }
    return newDate
}

fun getCountry(): String? {
    val locale = Locale.getDefault()
    val country = locale.country.toString()
    return country.toLowerCase()
}

