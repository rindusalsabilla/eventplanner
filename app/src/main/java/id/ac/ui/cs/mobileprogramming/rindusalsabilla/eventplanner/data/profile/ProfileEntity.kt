package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_details")
data class ProfileEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number_phone") var number_phone: String,
    @ColumnInfo(name = "created_at") var created_at: String,
    @ColumnInfo(name = "modified_at") var modified_at: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
)
