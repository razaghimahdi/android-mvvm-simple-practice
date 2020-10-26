package com.example.mvvmudemy01.db.roomDBPart02;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmudemy01.model.roomModel.ContactRoomDB;

import java.util.List;

@Dao/**NOTE:  Marks the interface as a data access object(DAO).*/
public interface ContactDAO {

    @Insert
    public long addContact(ContactRoomDB contact);/**NOTE: in ContactRoomDB,we defined a long field as the primary key(id).So the return type of this method should be long*/

    @Update
    public void updateContact(ContactRoomDB contact);

    @Delete
    public void deleteContact(ContactRoomDB contact);

    @Query("SELECT * FROM 'contacts-room'")
    public List<ContactRoomDB> getContacts();

    @Query("SELECT * FROM `contacts-room` WHERE contact_id == :contactId")
    public ContactRoomDB getContact(long contactId);


}
