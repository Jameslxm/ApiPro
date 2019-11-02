package com.lxm.apipro.canvas.d3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lxm.apipro.R

class CanvasView : View {
    private var mContext: Context? = null
    private  var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)
    private var mPaint: Paint? = null
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
        mPaint = Paint()
        mPaint?.isAntiAlias = true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //需求：图片从坐标（20，20）开始画
        canvas?.save()
        var matrix = Matrix()
        matrix.setTranslate(20f,20f)//画布开始点坐标移到（20，20）
        canvas?.concat(matrix)
        canvas?.drawBitmap(mBitmap,0f,0f,null)
        canvas?.restore()

    }

}