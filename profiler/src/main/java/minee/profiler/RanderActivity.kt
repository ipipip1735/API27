package minee.profiler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.widget.FrameLayout

class RanderActivity : AppCompatActivity() {
    var isStop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        findViewById<FrameLayout>(R.id.fl).addView(RanderView(this).also {
            Thread{
                println("start|$isDestroyed")
                while (!isDestroyed) {
                    Thread.sleep(1000L)
                    it.postInvalidate()
                }
                println("end|$isDestroyed")
            }.apply {
                name = "RT"
                start()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        isStop = true
    }

    override fun onDestroy() {
        super.onDestroy()
        println("~~RanderActivity.onDestroy~~")




        Thread{
            Looper.prepare()
            Looper.loop()
        }.start()
    }
}