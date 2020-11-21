package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import java.text.SimpleDateFormat
import java.util.*

class AddEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    var hour: Int = 0
    var minute: Int = 0
    var mDay = 0
    var mMonth: Int = 0
    var mYear: Int = 0
    private var txtDate: String = ""

    private val cal: Calendar = Calendar.getInstance()

    private lateinit var editEvent: EditText
    private lateinit var editDesc: EditText
    private lateinit var editDate: EditText
    private lateinit var dueDateBtn: Button

    companion object {
        val EXTRA_EVENT = "id.ac.ui.cs.mobileprogrramming.rindusalsabilla.eventplanner.EXTRA_EVENT"
        val EXTRA_DESC = "id.ac.ui.cs.mobileprogrramming.rindusalsabilla.eventplanner.EXTRA_DESC"
        val EXTRA_DATE = "id.ac.ui.cs.mobileprogrramming.rindusalsabilla.eventplanner.EXTRA_DATE"
        val NOTIFICATION_CHANNEL_ID = "10001"
        val default_notification_channel_id = "default"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        editEvent = findViewById(R.id.edit_event)
        editDesc = findViewById(R.id.edit_desc)
//        editDate = findViewById(R.id.btn_due_date)
        dueDateBtn = findViewById(R.id.btn_due_date)


        dueDateBtn.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, this, year, month, day)
            dpd.show()

        }


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_noun_close)
        setTitle("Add Event")
    }


    fun saveEvent() {
        val event: String = editEvent.text.toString()
        val desc: String = editDesc.text.toString()
        val dueDate: String = txtDate

        if (event.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Please insert event and description", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_EVENT, event)
        data.putExtra(EXTRA_DESC, desc)
        data.putExtra(EXTRA_DATE, dueDate)

        setResult(AppCompatActivity.RESULT_OK, data)
        finish()
        setNotification(getNotification(txtDate), cal.time.time)
    }

    private fun setNotification(notification: Notification, delay: Long) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent)
    }

    private fun getNotification(content: String): Notification {
        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Due Date")
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_event, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_event -> {
                saveEvent()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

        dueDateBtn.text = SpannableStringBuilder(txtDate)

        txtDate = "$mDay/$mMonth/$mYear"

        dueDateBtn.text = txtDate

        val tpd = TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this))
        tpd.show()
    }

    override fun onTimeSet(view: TimePicker?, hours: Int, minute: Int){
        cal.set(Calendar.HOUR_OF_DAY, hours)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, 0)
//        mMinute = minute
//        txtTime = "$mHour:$mMinute"
//        dueDateBtn.text = txtDate + txtTime
        updateLabel()

    }

    private fun updateLabel() {
        val format = "dd/MM/yy hh:mm a" //In which you need put here
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        txtDate = sdf.format(cal.time)
        dueDateBtn.setText(txtDate)
//        scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime())
    }
}