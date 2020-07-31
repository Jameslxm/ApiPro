package com.lxm.apipro.canvas.d10

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
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.clipRect(Rect(0,0,200,200))
        canvas?.drawColor(Color.BLUE, PorterDuff.Mode.ADD)
        canvas?.restore()

        canvas?.save()
        var path = Path()
        path.addCircle(100f,350f,100f,Path.Direction.CW)
        canvas?.clipPath(path)
        canvas?.drawColor(Color.BLUE)
        canvas?.restore()

    }

}