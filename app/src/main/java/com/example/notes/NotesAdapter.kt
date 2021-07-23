package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context : Context, val listener : NotesInterface) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val textView = ItemView.findViewById<TextView>(R.id.note_text)
        val delete_button = ItemView.findViewById<ImageView>(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
        viewHolder.delete_button.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.note_text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface NotesInterface{
    fun onItemClick(note : Note)
}