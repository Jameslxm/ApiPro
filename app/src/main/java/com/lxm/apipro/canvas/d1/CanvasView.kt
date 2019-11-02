package com.lxm.apipro.canvas.d1

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CanvasView : View {
    private var mContext: Context? = null
    private val mPath: Path = Path()
    private val mPath2: Path = Path()
    private  var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, com.lxm.apipro.R.drawable.pic1)
    private val rids = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 20.0f, 20.0f, 20.0f, 20.0f)
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
        mPath.reset()
        var picWith = mBitmap.width
        var picHeight = mBitmap.height
        var top = 300f - picHeight / 2f
        var left = 300f - picWith / 2f
        var right = left + picWith
        var bottom = top + picHeight
        mPath.addRoundRect(RectF(left,top,right, bottom), rids, Path.Direction.CW)// Path.Direction.CW 顺时针
        var isClipPath: Boolean?
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            isClipPath = canvas?.clipOutPath(mPath)
        }else{
            isClipPath = canvas?.clipPath(mPath)
        }
        Log.d("xxx","isClipPath:$isClipPath")
        canvas?.drawBitmap(mBitmap,left,top,null)
        canvas?.restore()

    }

}