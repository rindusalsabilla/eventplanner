package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_details WHERE id=1")
    fun getProfile(): ProfileEntity

    @Query("SELECT * FROM profile_details")
    fun getAllProfile(): List<ProfileEntity>

    @Query("SELECT * FROM profile_details WHERE name=:name")
    fun getUserByName(name: String): ProfileEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: ProfileEntity)

    @Delete
    fun delete(profile: ProfileEntity)

    @Update
    fun update(profile: ProfileEntity)
}