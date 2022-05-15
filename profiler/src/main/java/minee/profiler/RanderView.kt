package minee.profiler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by Administrator on 2022/5/14.
 */
class RanderView(context: Context?) : View(context) {
    val random: Random = Random()

    init {
        println("RanderView.init")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var radius = 0
        while (radius !in 100..300) {
            radius = random.nextInt(300)
        }


        val x = Math.max(random.nextInt(500), radius)
        val y = Math.max(random.nextInt(500), radius)
        println("radius = ${radius}, x = $x, y = $y")
        canvas?.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), Paint().also {
            it.setARGB(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        })
    }
}