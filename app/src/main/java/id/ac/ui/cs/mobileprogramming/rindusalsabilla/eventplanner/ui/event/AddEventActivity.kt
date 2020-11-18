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
import java.text.DateFormat
import java.util.*

class AddEventActivity : AppCompatActivity(){


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
}