package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val notes_DAO : Notes_DAO) {

    val allNotes : LiveData<List<Note>> = notes_DAO.getAll()

    suspend fun insert(note : Note){
        notes_DAO.insert(note)
    }
    suspend fun delete(note : Note){
        notes_DAO.delete(note)
    }
}