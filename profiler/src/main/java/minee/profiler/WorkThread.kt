package minee.profiler

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import java.lang.ref.WeakReference

/**
 * Created by Administrator on 2022/5/3.
 */
class WorkThread(callback: Handler.Callback) : HandlerThread(WorkThread::class.simpleName) {
    lateinit var handler: Handler
    val callback: WeakReference<Handler.Callback> = WeakReference(callback)

    init {
        start()
    }


    override fun onLooperPrepared() {
        println("~~WorkThread.onLooperPrepared~~")
        super.onLooperPrepared()
        handler = Handler(looper) {
            println("S|it = ${it}")
            val r = callback.get()?.handleMessage(it) ?: false
            println("E|it = ${it}")
            r
        }
    }

    fun send(msg: Message) {
        handler.sendMessage(msg)
    }

    fun send(what: Int) {
        handler.sendEmptyMessage(what)
    }
}