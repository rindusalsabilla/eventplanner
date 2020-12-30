package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import kotlinx.android.synthetic.main.count_activity.*

class CountEventActivity : AppCompatActivity()  {
    private var num: Int = 100
    private external fun minNumber(num: Int): Int

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.count_activity)

        btnMin.setOnClickListener {
            num = minNumber(num)
            txtNum.text = num.toString()
        }

    }
}