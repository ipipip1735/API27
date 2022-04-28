package mine.threadlooper

import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Administrator on 2022/4/8.
 */
class MainKTActivity : AppCompatActivity() {

    val theHandlerThread:TheHandlerThread = TheHandlerThread().apply { start() }

    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~MainKTActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        println("~~MainKTActivity.onResume~~")
        super.onResume()
    }

    override fun onPause() {
        println("~~MainKTActivity.onPause~~")
        super.onPause()
    }

    override fun onStop() {
        println("~~MainKTActivity.onStop~~")
        super.onStop()
    }

    override fun onDestroy() {
        println("~~MainKTActivity.onDestroy~~")
        super.onDestroy()

    }

    fun start(view: android.view.View) {
        println("~~MainKTActivity.start~~")
        Message.obtain(theHandlerThread.handler, 1).sendToTarget()

    }


    fun stop(view: android.view.View) {
        println("~~MainKTActivity.stop~~")
        Message.obtain(theHandlerThread.handler, 2).sendToTarget()
    }


    fun bind(view: android.view.View) {
        println("~~MainKTActivity.bind~~")
    }
}