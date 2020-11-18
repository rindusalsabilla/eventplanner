package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    var formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)

    private lateinit var editEvent: EditText
    private lateinit var editDesc: EditText
    private lateinit var dueDateBtn: Button

    companion object {
        val EXTRA_EVENT = "id.ac.ui.cs.mobileprogrramming.rindusalsabilla.eventplanner.EXTRA_EVENT"
        val EXTRA_DESC = "id.ac.ui.cs.mobileprogrramming.rindusalsabilla.eventplanner.EXTRA_EVENT"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        editEvent = findViewById(R.id.edit_event)
        editDesc = findViewById(R.id.edit_desc)
        dueDateBtn = findViewById(R.id.btn_due_date)

        dueDateBtn.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, this, year, month, day)
            val date = formatDate.format(cal.time)
            text_date.text = date
            dpd.show()

        }


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_noun_close)
        setTitle("Add Event")
    }


    fun saveContact() {
        val event: String = editEvent.text.toString()
        val desc: String = editDesc.text.toString()

        if (event.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Please insert event and description", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_EVENT, event)
        data.putExtra(EXTRA_DESC, desc)

        setResult(AppCompatActivity.RESULT_OK, data)
        finish()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_event, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_event -> {
                saveContact()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(this, this, hour, minute, true)
        tpd.show()
        String
    }

    override fun onTimeSet(view: TimePicker?, hours: Int, minute: Int){
        myHour = hours
        myMinute = minute
    }
}