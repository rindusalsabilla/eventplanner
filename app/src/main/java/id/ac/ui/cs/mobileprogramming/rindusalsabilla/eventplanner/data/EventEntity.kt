package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_details")
data class EventEntity (
                        @ColumnInfo(name = "event") var event: String,
                        @ColumnInfo(name = "desc") var desc: String,
                        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0)