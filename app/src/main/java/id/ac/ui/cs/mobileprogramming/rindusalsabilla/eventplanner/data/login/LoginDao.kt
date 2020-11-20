package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity

@Dao
interface LoginDao {
    @Insert
    fun createUser(userLogin: LoginEntity?): Long?

    @Query("SELECT * FROM LoginEntity WHERE username =:username AND password =:password")
    fun getUserByUsernameAndPassword(
        username: String?,
        password: String?
    ): LiveData<LoginEntity?>?

    @Query("SELECT * FROM LoginEntity WHERE username =:username")
    fun getUserByUsername(username: String?): LiveData<LoginEntity?>?

    @Query("SELECT * FROM LoginEntity WHERE id=:id")
    fun getUserById(id: Int): LiveData<LoginEntity?>?

    @Query("SELECT * FROM LoginEntity")
    fun getAllUser(): LiveData<List<@JvmSuppressWildcards LoginEntity>>

    @Update
    fun updateUser(userLogin: LoginEntity?)

    @Delete
    fun deleteUser(userLogin: LoginEntity?)
}