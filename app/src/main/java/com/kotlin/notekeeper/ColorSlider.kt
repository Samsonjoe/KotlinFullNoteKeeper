package com.kotlin.notekeeper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.SeekBar
import androidx.core.content.ContextCompat

@SuppressLint("AppCompatCustomView")
class ColorSlider  @JvmOverloads constructor(context: Context,
                                             attrs:AttributeSet? = null,
                                             defStyleAttr: Int = R.attr.seekBarStyle,
                                             defStyleRes: Int = 0)
        : SeekBar(context, attrs, defStyleAttr, defStyleRes) {
        private var colors: ArrayList<Int> = arrayListOf(Color.RED, Color.YELLOW,
                Color.BLUE)

    //getPixelValueFromDP(16f) = 16 dp....if you want 20dp = 20f , 30dp = 30f
    private val w = getPixelValueFromDP(16f) // Width of color swatch
    private val h = getPixelValueFromDP(16f) // Height of color swatch
    private val halfW :Float = if (w >= 0) w / 2f else 1f
    private val halfH :Float = if (h >= 0) h / 2f else 1f
    private val paint = Paint()
    private var noColorDrawable:Drawable? = null
        set(value) {
             w2 = value?.intrinsicWidth ?: 0
             h2 = value?.intrinsicHeight ?: 0
             halfW2 = if (w2 >= 0) w2 / 2 else 1
             halfH2 = if (h2 >= 0) h2 / 2 else 1
            value?.setBounds(-halfW2, -halfW2, halfW2, halfH2)
            field = value
        }
    var w2 = 0
    private var h2 =  0
    private var halfW2 = 1
    private var halfH2 = 1

    init {
    val typedArray = context.obtainStyledAttributes(attrs,
    R.styleable.ColorSlider)
    try {
        colors = typedArray.getTextArray(R.styleable.ColorSlider_colors)
                .map {
                    Color.parseColor(it.toString())
                } as ArrayList<Int>
    } finally {
        typedArray.recycle()
    }
    colors.add(0,android.R.color.transparent)
    max = colors.size - 1
    progressBackgroundTintList = ContextCompat.getColorStateList(context,
                android.R.color.transparent)
     progressTintList = ContextCompat.getColorStateList(context,
             android.R.color.transparent)
     splitTrack = false
     setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom + getPixelValueFromDP(16f).toInt())
     thumb = context.getDrawable(R.drawable.ic_color_slider_thumb)
     noColorDrawable = context.getDrawable(R.drawable.ic_no_color)

    setOnSeekBarChangeListener(object  : OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            listeners.forEach {
                it(colors[progress])
            }
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {}

        override fun onStopTrackingTouch(p0: SeekBar?) {}
    })
}

    var selectedColorValue: Int = android.R.color.transparent
    set(value) {
        var index = colors.indexOf(value)
        if (index == 1) {
            progress = 0
        } else {
            progress = index
        }
    }

    private  var listeners: ArrayList<(Int) -> Unit> = arrayListOf()

    fun addListener(function: (Int) -> Unit) {
        listeners.add(function)
    }

    override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            drawTickMarks(canvas)
    }

    private fun drawTickMarks(canvas: Canvas?) {
        canvas?.let {
            val count :Int = colors.size
            val saveCount :Int = canvas.saveCount
            canvas.translate(paddingLeft.toFloat(), (height / 2).toFloat() + getPixelValueFromDP(16f))
            if (count > 1) {
                val spacing :Float = (width - paddingLeft - paddingRight) / (count - 1).toFloat()
                for (i :Int in 0 until count) {
                    if (i == 0 ){
                        noColorDrawable?.draw(canvas)
                    } else {
                        paint.color = colors[i]
                        // -halfW is left while (+)halfW is right side
                        canvas.drawRect(-halfW, -halfH,
                                halfW, halfH, paint)
                    }
                    canvas.translate(spacing, 0f)
                 }
                 canvas.restoreToCount(saveCount)
            }
        }
    }

    //METHOD FOR CONVERTING DP to PIXELS
    private fun getPixelValueFromDP(value: Float): Float {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        value,
        context.resources.displayMetrics)
    }
}