package mine.services

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Administrator on 2022/4/10.
 */
class MainKtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~MainKtActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        println("~~MainKtActivity.onResume~~")
        super.onResume()
    }

    override fun onPause() {
        println("~~MainKtActivity.onPause~~")
        super.onPause()
    }

    override fun onStop() {
        println("~~MainKtActivity.onStop~~")
        super.onStop()
    }

    override fun onDestroy() {
        println("~~MainKtActivity.onDestroy~~")
        super.onDestroy()

    }

    fun start(view: android.view.View) {
        println("~~MainKtActivity.start~~")
        Thread {
            val time = SystemClock.elapsedRealtime()
            while (SystemClock.elapsedRealtime() - time < 1000L) {
            }
            startService(Intent(this, BaseService::class.java))
                .also {
                    println("it = ${it}")
                }
        }.start()
    }

    fun stop(view: android.view.View) {
        println("~~MainKtActivity.stop~~")
    }

    fun bind(view: View?) {
        println("~~MainKtActivity.bind~~")


        bindService(Intent("bs").apply {
            `package` = "mine.services"
            println("this = $this")
        }, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                println("~~MainKtActivity.onServiceConnected~~")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                println("~~MainKtActivity.onServiceDisconnected~~")
            }
        }, BIND_AUTO_CREATE)
    }
}