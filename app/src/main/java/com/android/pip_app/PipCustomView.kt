package com.android.pip_app


import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Rational
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class PipCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
     lateinit var  textView1:TextView
    lateinit var  textView2:TextView
    lateinit var  textView3:TextView
    lateinit var button:Button

    init {
        inflate(context, R.layout.pip_custom_view, this)

         textView1 = findViewById<TextView>(R.id.textView1)
        textView2 = findViewById<TextView>(R.id.textView2)
         textView3 = findViewById<TextView>(R.id.textView3)
        button = findViewById<Button>(R.id.button)

        // Set click listener for the button
        button.setOnClickListener {
            enterPictureInPictureMode(context)
        }
    }

    private fun enterPictureInPictureMode(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (context as? Activity)?.enterPictureInPictureMode()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val aspectRatio = Rational(w, h)

        // Create PictureInPictureParams with the new aspect ratio
        val params = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PictureInPictureParams.Builder().setAspectRatio(aspectRatio).build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        // Set PictureInPictureParams for the current activity
        (context as? Activity)?.setPictureInPictureParams(params)
    }
}
