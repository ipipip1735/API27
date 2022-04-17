package mine.apptemp

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Administrator on 2022/4/8.
 */
class ServicesKTActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~ServicesKTActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
    }

    override fun onResume() {
        println("~~ServicesKTActivity.onResume~~")
        super.onResume()
    }

    override fun onPause() {
        println("~~ServicesKTActivity.onPause~~")
        super.onPause()
    }

    override fun onStop() {
        println("~~ServicesKTActivity.onStop~~")
        super.onStop()
    }

    override fun onDestroy() {
        println("~~ServicesKTActivity.onDestroy~~")
        super.onDestroy()

    }

    fun start(view: android.view.View) {
        println("~~ServicesKTActivity.start~~")

        bindService(Intent("bs").apply {
            `package` = "mine.services"
            println("this = ${this}")
        }, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                println("~~ServicesKTActivity.onServiceConnected~~")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                println("~~ServicesKTActivity.onServiceDisconnected~~")
            }
        }, BIND_AUTO_CREATE).also {
            if (it) {
                println("bind Service is done")
            } else {
                println("fail to bind Service")
            }
        }
    }


    fun stop(view: android.view.View) {
        println("~~ServicesKTActivity.stop~~")
    }


    fun bind(view: android.view.View) {
        println("~~ServicesKTActivity.bind~~")


        val componentName = ComponentName("mine.services", "mine.services.BasicJobService")
        val jobInfo = JobInfo.Builder(1, componentName)
//            .setPeriodic(15 * 60 * 1000L)
//            .setPeriodic(5 * 1000L) //延迟时间，小于15分钟按15分钟计算
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) //需要网络
//            .setBackoffCriteria(1000L, JobInfo.BACKOFF_POLICY_LINEAR)
            .build()

        val jobScheduler = getSystemService(JobScheduler::class.java)
        val r: Int = jobScheduler.schedule(jobInfo)
        println("r = ${r}")

    }
}