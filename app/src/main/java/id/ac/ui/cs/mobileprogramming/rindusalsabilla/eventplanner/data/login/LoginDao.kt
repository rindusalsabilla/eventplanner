package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LoginDao {
    @Query("SELECT * FROM login_details WHERE id=:id")
    fun getUserById(id: Int): LiveData<LoginEntity>

    @Query("SELECT * FROM login_details")
    fun getAllUser(): LiveData<List<@JvmSuppressWildcards LoginEntity>>

    @Insert
    fun createUser(userLogin: LoginEntity)

    @Update
    fun updateUser(userLogin: LoginEntity)

    @Delete
    fun deleteUser(userLogin: LoginEntity)

    @Query("SELECT * FROM login_details WHERE username =:username AND password =:password")
    fun getUserByUsernameAndPassword(
        username: String,
        password: String
    ): LiveData<LoginEntity>

    @Query("SELECT * FROM login_details WHERE username =:username")
    fun getUserByUsername(username: String): LiveData<LoginEntity>
}