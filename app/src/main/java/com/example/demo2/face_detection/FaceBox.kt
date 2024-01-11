package com.example.demo2.face_detection

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import androidx.core.graphics.toRect
import com.google.mlkit.vision.face.Face

class FaceBox(overlay: FaceBoxOverlay, private val face: Face, private val imageRect: Rect) :FaceBoxOverlay.FaceBox(overlay) {

    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6.0f

    }

    override fun draw(canvas: Canvas?) {

        val rect =  getBoxRect(
            imageRectWidth = imageRect.width().toFloat(),
            imageRecHeight = imageRect.height().toFloat(),
            faceBoundingBox = face.boundingBox
        )

        rect.toRect()


        canvas?.drawRect(rect, paint)
    }



}