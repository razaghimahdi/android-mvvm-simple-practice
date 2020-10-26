package com.example.mvvmudemy01.db.roomDBPart02;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvvmudemy01.model.roomModel.ContactRoomDB;

@Database(entities = ContactRoomDB.class, version = 1)/**NOTE:Database Annotation marks a class as a room database.And this class should also extend RoomDatabase.
 With the database annotation, we also need to mention, list of entity classes and the database version.*/
public abstract class ContactAppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
