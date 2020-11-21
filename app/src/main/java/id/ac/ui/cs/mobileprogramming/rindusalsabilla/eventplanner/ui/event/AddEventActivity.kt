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
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    private var txtDate: String = ""

    private val calendar: Calendar = Calendar.getInstance()

    private lateinit var editEvent: EditText
    private lateinit var editDesc: EditText
    private lateinit var theDayBtn: Button

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
        theDayBtn = findViewById(R.id.btn_due_date)


        theDayBtn.setOnClickListener{
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_event, menu)
        return true
    }

    private fun setNotification(notification: Notification, delay: Long) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.ID_NOTIF, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIF, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent)
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
        setNotification(getNotification(txtDate), calendar.time.time)
    }

    private fun getNotification(content: String): Notification {
        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Today is The Event!")
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()
    }

    override fun onTimeSet(view: TimePicker?, hours: Int, minute: Int){
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        changeText()

    }

    private fun changeText() {
        val format = "dd/MM/yy hh:mm a"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        txtDate = sdf.format(calendar.time)
        theDayBtn.setText(txtDate)
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
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

        theDayBtn.text = SpannableStringBuilder(txtDate)

        txtDate = "$myDay/$myMonth/$myYear"

        theDayBtn.text = txtDate

        val tpd = TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this))
        tpd.show()
    }

}