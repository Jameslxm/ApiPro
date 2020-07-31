package com.lxm.apipro.canvas.d8

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CanvasView : View {
    private var mContext: Context? = null
    private var mBitmap: Bitmap =
        BitmapFactory.decodeResource(resources, com.lxm.apipro.R.drawable.pic1)
    private var mPaint: Paint = Paint()

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
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.drawBitmap(mBitmap,0f,100f, mPaint)
        canvas?.restore()

    }

}