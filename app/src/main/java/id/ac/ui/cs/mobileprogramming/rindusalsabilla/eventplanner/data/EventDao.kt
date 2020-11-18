package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EventDao {

    @Query("SELECT * FROM event_details ORDER BY event ASC")
    fun getAll(): LiveData<List<@JvmSuppressWildcards EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventEntity: EventEntity)

    @Delete
    fun delete(eventEntity: EventEntity)

    @Update
    fun update(eventEntity: EventEntity)
}