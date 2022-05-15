package minee.profiler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Administrator on 2022/4/8.
 */
class CPUActivity : AppCompatActivity(), Handler.Callback {
    val list: ArrayList<Array<Byte>> = ArrayList()
    val workThread: WorkThread = WorkThread(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~CPUActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)


    }

    override fun onResume() {
        println("~~CPUActivity.onResume~~")
        super.onResume()
    }

    override fun onPause() {
        println("~~CPUActivity.onPause~~")
        super.onPause()
    }

    override fun onStop() {
        println("~~CPUActivity.onStop~~")
        super.onStop()
    }

    override fun onDestroy() {
        println("~~CPUActivity.onDestroy~~")
        super.onDestroy()
        workThread.quitSafely()

    }

    fun start(view: android.view.View) {
        println("~~CPUActivity.start~~")


        Message.obtain(workThread.handler) {
            println("Start")
            val time = SystemClock.elapsedRealtime()
            while (true) {
                if (SystemClock.elapsedRealtime() - time > 3000L) break
            }
            println("End")
        }.sendToTarget()
    }


    fun stop(view: android.view.View) {
        println("~~CPUActivity.stop~~")
        workThread.send(Message.obtain(null, 1))
    }


    fun bind(view: android.view.View) {
        println("~~CPUActivity.bind~~")

    }

    fun unbind(view: android.view.View) {
        println("~~CPUActivity.unbind~~")
    }

    override fun handleMessage(msg: Message): Boolean {
        println("~~CPUActivity.handleMessage~~")
        println("msg = [${msg}]")
        return true
    }
}