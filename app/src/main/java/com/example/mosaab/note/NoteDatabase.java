package com.example.mosaab.note;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class}, version = 1)  //for more entities (entities = {1,2,3})
public abstract class NoteDatabase extends RoomDatabase {


       private static NoteDatabase instance;

       public abstract NoteDao noteDao();

       public static synchronized NoteDatabase getInstance(Context context)
       {
           if (instance == null)
           {
               instance = Room.databaseBuilder(context.getApplicationContext(),
                       NoteDatabase.class, "note_database")
                       .fallbackToDestructiveMigration()
                       .addCallback(roomCallBack)
                       .build();
               }
               return instance;
       }


       private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback()
       {

           @Override
           public void onCreate(@NonNull SupportSQLiteDatabase db) {
               super.onCreate(db);
               new PopulateDbAsyncTask(instance).execute();

           }
       };


       private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
       {

           private NoteDao noteDao;

           private PopulateDbAsyncTask(NoteDatabase db)
           {
               noteDao = db.noteDao();

           }


           @Override
           protected Void doInBackground(Void... voids) {

               noteDao.Insert(new Note("T1","D2",1));
               noteDao.Insert(new Note("T2","D2",2));
               noteDao.Insert(new Note("T3","D2",3));


               return null;
           }
       }
}
