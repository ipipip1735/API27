package mine.apptemp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.sql.DriverManager.println

/**
 * Created by Administrator on 2022/4/5.
 */
class WeakReferenceKTActivity : AppCompatActivity(), Runnable {
    lateinit var refA: WeakReference<Runnable>
    lateinit var refB: WeakReference<Runnable>
    lateinit var refC: WeakReference<() -> Unit>
    lateinit var refD: WeakReference<() -> Unit>
    lateinit var refE: WeakReference<() -> Unit>
    lateinit var refF: WeakReference<() -> Unit>
    val tag = this::class.simpleName
    override fun run() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        println("~~GLActivity.onCreate~~")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)


        val a = Runnable {
            println("aa")
        }
        val b = Runnable {
            println("$tag|bb")
        }
        println("a = $a, b = $b")
        refA = WeakReference(a)
        refB = WeakReference(b)


        val c = { println("cc") }
        val d = { println("$tag|dd") }
        println("c = $c, d = $d")
        refC = WeakReference(c)
        refD = WeakReference(d)


        val e = this::come
        val f = this::go
        println("e = $e, f = $f")
        refE = WeakReference(e)
        refF = WeakReference(f)

        println("refA.get() = ${refA.get()}")
        println("refB.get() = ${refB.get()}")
        println("refC.get() = ${refC.get()}")
        println("refD.get() = ${refD.get()}")
        println("refE.get() = ${refE.get()}")
        println("refF.get() = ${refF.get()}")
    }

    override fun onResume() {
        println("~~GLActivity.onResume~~")
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
//        println("h.quit() = ${h.quit()}")
    }

    fun start(view: android.view.View) {
        println("~~MainActivity.start~~")
        println("refA.get() = ${refA.get()}")
        println("refB.get() = ${refB.get()}")
        println("refC.get() = ${refC.get()}")
        println("refD.get() = ${refD.get()}")
        println("refE.get() = ${refE.get()}")
        println("refF.get() = ${refF.get()}")

    }


    fun stop(view: android.view.View) {
        println("~~MainActivity.stop~~")
        Runtime.getRuntime().gc()
    }

    fun go() {
        println("~~GLActivity.run~~")
    }

    fun come() {
        println("$tag|~~GLActivity.run~~")
    }

}