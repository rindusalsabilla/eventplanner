package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventEntity: EventEntity)

    @Query("SELECT * FROM event_details ORDER BY event ASC")
    fun getAll(): LiveData<List<@JvmSuppressWildcards EventEntity>>

    @Delete
    fun delete(eventEntity: EventEntity)

    @Update
    fun update(eventEntity: EventEntity)
}