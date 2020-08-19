package com.example.mvvmudemy01.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvvmudemy01.model.ContactRoomDB;

@Database(entities = ContactRoomDB.class, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
