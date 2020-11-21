package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "login_details")
data class LoginEntity(
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "created_at") var createdAt: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
)
