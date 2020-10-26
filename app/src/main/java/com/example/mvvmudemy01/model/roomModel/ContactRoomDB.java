package com.example.mvvmudemy01.model.roomModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts-room")/**NOTE: Entity Annotation marks a class as an entity or a model.
 We create entities to represent each of the database tables.
 If you have 5 tables in your database,you may create 5 entity classes.
 */
public class ContactRoomDB {

    @ColumnInfo(name = "contact_id")/**NOTE:  Allows specific customization about the column associated with the field.*/
    @PrimaryKey(autoGenerate = true)/**NOTE:  Marks a field in an entity as the primary key.Also i want this primary key to increase automatically so we use autoGenerate = true*/
    private long id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;


    @Ignore/**NOTE: As Room generate most codes for us, we should clearly say to room which one to ignore.*/
    public ContactRoomDB() {
    }

    public ContactRoomDB(long id, String name, String email) {

        this.name = name;
        this.email = email;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





}
