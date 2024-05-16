package com.example.airtickets

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resId: Int) {
        imageView.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("android:backgroundTint")
    fun setBackgroundTint(layout: LinearLayout, colorId: Int) {
        val color = ContextCompat.getColor(layout.context, colorId)
        layout.backgroundTintList = ColorStateList.valueOf(color)
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}