package com.example.mvvmudemy01.db.roomDBPart02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.mvvmudemy01.model.roomModel.ContactSQLite;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "contact_db_sqlite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(ContactSQLite.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ContactSQLite.TABLE_NAME);


        onCreate(db);
    }

    public long insertContact(String name, String email) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ContactSQLite.COLUMN_NAME, name);
        values.put(ContactSQLite.COLUMN_EMAIL, email);


        long id = db.insert(ContactSQLite.TABLE_NAME, null, values);


        db.close();


        return id;
    }

    public ContactSQLite getContact(long id) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ContactSQLite.TABLE_NAME,
                new String[]{ContactSQLite.COLUMN_ID, ContactSQLite.COLUMN_NAME, ContactSQLite.COLUMN_EMAIL},
                ContactSQLite.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        ContactSQLite contactSQLite = new ContactSQLite(
                cursor.getLong(cursor.getColumnIndex(ContactSQLite.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ContactSQLite.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(ContactSQLite.COLUMN_EMAIL)));


        cursor.close();

        return contactSQLite;
    }

    public ArrayList<ContactSQLite> getAllContacts() {
        ArrayList<ContactSQLite> contactSQLites = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + ContactSQLite.TABLE_NAME + " ORDER BY " +
                ContactSQLite.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                ContactSQLite contactSQLite = new ContactSQLite();
                contactSQLite.setId(cursor.getInt(cursor.getColumnIndex(ContactSQLite.COLUMN_ID)));
                contactSQLite.setName(cursor.getString(cursor.getColumnIndex(ContactSQLite.COLUMN_NAME)));
                contactSQLite.setEmail(cursor.getString(cursor.getColumnIndex(ContactSQLite.COLUMN_EMAIL)));

                contactSQLites.add(contactSQLite);
            } while (cursor.moveToNext());
        }


        db.close();


        return contactSQLites;
    }


    public int updateContact(ContactSQLite contactSQLite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactSQLite.COLUMN_NAME, contactSQLite.getName());
        values.put(ContactSQLite.COLUMN_EMAIL, contactSQLite.getEmail());


        return db.update(ContactSQLite.TABLE_NAME, values, ContactSQLite.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactSQLite.getId())});
    }

    public void deleteContact(ContactSQLite contactSQLite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContactSQLite.TABLE_NAME, ContactSQLite.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactSQLite.getId())});
        db.close();
    }
}
