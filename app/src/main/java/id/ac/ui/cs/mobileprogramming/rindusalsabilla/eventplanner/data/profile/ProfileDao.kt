package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Insert
    fun createUserProfile(profileEntity: ProfileEntity)

    @Query("SELECT * FROM profile_details WHERE id=:id")
    fun getUserProfileById(id: Int): LiveData<ProfileEntity>

    @Update
    fun updateUserProfile(profileEntity: ProfileEntity)

    @Delete
    fun deleteUserProfile(profileEntity: ProfileEntity)
}