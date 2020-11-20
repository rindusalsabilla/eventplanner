package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class ProfileEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "numberPhone")
    var numberPhone: String? = null

    @ColumnInfo(name = "created_at")
    var createdAt: String? = null

    @ColumnInfo(name = "modified_at")
    var modifiedAt: String? = null

}