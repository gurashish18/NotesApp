package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Notes_DAO {
    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAll() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note : Note)
}