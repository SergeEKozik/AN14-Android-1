package com.github.krottv.tmstemp

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.widget.TextView

fun TextView.addBorder(
    color : Int = Color.GRAY,
    width : Float = 10F
){
    val drawable = ShapeDrawable().apply {
        shape = RectShape()

        paint.apply {
            this.color = color
            strokeWidth = width
            style = Paint.Style.STROKE
        }
    }
    background = drawable
}