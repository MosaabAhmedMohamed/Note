package com.example.mosaab.note;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.GetAllNotes();
    }

    public void insert(Note note) {

        new InsertNoteAsyncTask(noteDao).execute(note);

    }

    public void update(Note note)
    {
           new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note)
    {
           new DeletetNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll()
    {
       new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Insert(notes[0]);
            return null;
        }
    }

    private static class DeletetNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private DeletetNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private NoteDao noteDao;

        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteAllNote();
            return null;
        }
    }


}
