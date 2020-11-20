package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class LoginEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "username")
    var username: String? = null

    @ColumnInfo(name = "password")
    var password: String? = null

    @ColumnInfo(name = "created_at")
    var createdAt: String? = null

}
