package com.example.mvvmudemy01.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmudemy01.model.ContactRoomDB;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    public long addContact(ContactRoomDB contact);

    @Update
    public void updateContact(ContactRoomDB contact);

    @Delete
    public void deleteContact(ContactRoomDB contact);

    @Query("SELECT * FROM 'contacts-room'")
    public List<ContactRoomDB> getContacts();


    @Query("SELECT * FROM `contacts-room` WHERE contact_id == :contactId")
    public ContactRoomDB getContact(long contactId);


}
