package com.example.mvvmudemy01.db.roomDBPart02;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvvmudemy01.model.roomModel.ContactRoomDB;

@Database(entities = ContactRoomDB.class, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
