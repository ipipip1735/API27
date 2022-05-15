package minee.profiler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Administrator on 2022/4/8.
 */
class MemoryActivity : AppCompatActivity(), Handler.Callback {
    val list: ArrayList<Array<Byte>> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~MemoryActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
    }

    override fun onResume() {
        println("~~MemoryActivity.onResume~~")
        super.onResume()
    }

    override fun onPause() {
        println("~~MemoryActivity.onPause~~")
        super.onPause()
    }

    override fun onStop() {
        println("~~MemoryActivity.onStop~~")
        super.onStop()
    }

    override fun onDestroy() {
        println("~~MemoryActivity.onDestroy~~")
        super.onDestroy()

    }

    fun start(view: android.view.View) {
        println("~~MemoryActivity.start~~")
        list.add(Array(256 * 1024){
            0
        })
    }


    fun stop(view: android.view.View) {
        println("~~MemoryActivity.stop~~")
        Array(512 * 1024) {
            0
        }
    }


    fun bind(view: android.view.View) {
        println("~~MemoryActivity.bind~~")

        if (!list.isEmpty()) list.removeAt(list.lastIndex)

    }

    fun unbind(view: android.view.View) {
        println("~~MemoryActivity.unbind~~")
    }

    override fun handleMessage(msg: Message): Boolean {
        println("~~MemoryActivity.handleMessage~~")
        println("msg = [${msg}]")
        return true
    }
}