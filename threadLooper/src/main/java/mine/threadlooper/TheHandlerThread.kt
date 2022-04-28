package mine.threadlooper

import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock

/**
 * Created by Administrator on 2022/4/26.
 */
class TheHandlerThread : HandlerThread("HT") {
    public lateinit var handler: Handler

    override fun onLooperPrepared() {
        handler = Handler(looper) {
            println("it = $it")
            val time = SystemClock.elapsedRealtime()
            while (it.what == 1) {
                if (SystemClock.elapsedRealtime() - time > 5000L) break
            }
            return@Handler true
        }
    }
}