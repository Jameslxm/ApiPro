package com.lxm.apipro.canvas.d4

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lxm.apipro.R

class CanvasView : View {
    private var mContext: Context? = null
    private  var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)
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
        mPaint?.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //需求：图片从坐标（20，20）开始画
        canvas?.save()

        canvas?.drawBitmap(mBitmap,0f,0f,null)
        canvas?.drawARGB(127,255,218,185)
        canvas?.restore()

    }

}