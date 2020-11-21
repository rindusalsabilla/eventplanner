package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_details")
data class ProfileEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number_phone") var number_phone: String,
    @ColumnInfo(name = "image") var image: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 1
)
