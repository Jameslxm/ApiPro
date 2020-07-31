package com.lxm.apipro.canvas.d9

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager

class CanvasView : View {
    private var mContext: Context? = null
    private var mBitmap: Bitmap =
        BitmapFactory.decodeResource(resources, com.lxm.apipro.R.drawable.pic1)
    private var mPaint: Paint = Paint()
    //将图像分成多少格
    private val WIDTH = 200
    private val HEIGHT = 200
    //交点坐标的个数
    private val COUNT = (WIDTH + 1) * (HEIGHT + 1)
    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private val verts = FloatArray(COUNT * 2)
    //用于保存原始的坐标
    private val orig = FloatArray(COUNT * 2)
    private var bmWidth: Int = 0
    private var bmHeight: Int = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
        sceenWidth = getSceenWidth()
    }

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        initView()
    }

    private fun getSceenWidth(): Int {
        var windowManager: WindowManager = mContext?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    private fun initView() {
        var index = 0
        bmWidth = mBitmap.width//图片的宽
        bmHeight = mBitmap.height//图片的高
        var vPointCount = HEIGHT

        //保存原图的交点坐标的(x,y)
        for (i in 0..vPointCount) {
            var fy = bmHeight * i / HEIGHT * 1f
            for (j in 0..(WIDTH )) {
                var fx = bmWidth * j / WIDTH * 1f
                verts[index * 2] = fx
                orig[index * 2] = verts[index * 2]

                verts[index * 2 + 1] = fy
                orig[index * 2 + 1] = verts[index * 2 + 1]

                index++
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.drawBitmapMesh(
            mBitmap, WIDTH, HEIGHT, verts, 0,
            null, 0, mPaint
        )
        canvas?.restore()

    }
    private var startX = 0.0f//开始点x轴坐标
    private var startY = 0.0f//开始点y轴坐标
    private var r = 100//定义的一个变量值，用来判断，只有圆形选区内才改变图片的扭曲
    private var sceenWidth = 0//屏幕的宽
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            startX = event.x
            startY = event.y
        }else if (event?.action == MotionEvent.ACTION_UP){
            warp(startX, startY, event.x, event.y)
        }
        return true

    }

    private fun warp(startX: Float, startY: Float, endX: Float, endY: Float) {
        var ddPull = (endX - startX)*(endX - startX) + (endY - startY) * (endY - startY)
        var dPull = Math.sqrt(ddPull.toDouble())//在屏幕上拖的距离
        dPull = if(dPull >= 0.0001f)dPull else (sceenWidth - 0.001f).toDouble()
        var i = 0

        while (i < COUNT * 2) {
            //计算每个坐标点与触摸点之间的距离
            val dx = verts[i] - startX
            val dy = verts[i + 1] - startY
            val dd = dx * dx + dy * dy
            val d = Math.sqrt(dd.toDouble()).toFloat()//交点和开始点的距离

            //文献中提到的算法同样不能实现只有圆形选区内的图像才进行变形的功能，这里需要做一个距离的判断
            if (d < r) {
                //变形系数，扭曲度
                val e =
                    (r * r - dd) * (r * r - dd) / ((r * r - dd + dPull * dPull) * (r * r - dd + dPull * dPull))
                val pullX = e * (endX - startX)
                val pullY = e * (endY - startY)
                verts[i] = (verts[i] + pullX).toFloat()
                verts[i + 1] = (verts[i + 1] + pullY).toFloat()
            }
            i += 2
        }
        invalidate()
    }

}