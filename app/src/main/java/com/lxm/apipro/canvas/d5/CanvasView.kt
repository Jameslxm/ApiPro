package com.lxm.apipro.canvas.d5

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasView : View {
    private var mContext: Context? = null
    private var mPaint: Paint =  Paint()
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context


    }

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        var oval = RectF(0f,0f,200f,200f)
        canvas?.drawArc(oval,0f,45f,false,mPaint)
        canvas?.restore()

    }

}