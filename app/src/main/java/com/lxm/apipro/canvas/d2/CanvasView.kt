package com.lxm.apipro.canvas.d2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasView : View {
    private var mContext: Context? = null
    private  var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, com.lxm.apipro.R.drawable.pic1)
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
        canvas?.save()
        var picWith = mBitmap.width
        var picHeight = mBitmap.height
        var centerX = picWith / 2
        var centerY = picHeight / 2
        var rect = Rect(centerX - 100,centerY - 100,centerX + 100,centerY + 100)

        canvas?.clipRect(rect,Region.Op.DIFFERENCE)
        canvas?.drawBitmap(mBitmap,0f,0f,null)
        canvas?.restore()

    }

}