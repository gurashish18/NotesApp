package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesInterface {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adpater = NotesAdapter(this, this)
        recyclerView.adapter = adpater

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                    .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adpater.updateList(it)
            }
        })
    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.note_text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitNote(view: View) {
        val text_input = findViewById<EditText>(R.id.text_input)
        val note_text = text_input.text.toString()
        if(note_text.isNotEmpty()){
            viewModel.insertNote(Note(note_text))
            Toast.makeText(this, "${note_text} Added", Toast.LENGTH_SHORT).show()
        }
    }
}