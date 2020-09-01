package com.example.mvvmudemy01.view.roomView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.roomAdapter.ContactsRoomAdapter;
import com.example.mvvmudemy01.db.roomDBPart02.ContactAppDatabase;
import com.example.mvvmudemy01.model.roomModel.ContactRoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Part02_1RoomdbActivity extends AppCompatActivity {


    private ContactsRoomAdapter contactsRoomAdapter;
    private ArrayList<ContactRoomDB> contactRoomArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAppDatabase contactAppDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part02_1_roomdb);


        recyclerView = findViewById(R.id.recycler_view_contacts);
        contactAppDatabase = Room.databaseBuilder(getApplicationContext(), ContactAppDatabase.class,"ContactRoomDB")/*.allowMainThreadQueries()*/.addCallback(callback).build();


        new GetAllContactAsyncTask().execute();

        //contactRoomArrayList.addAll(contactAppDatabase.getContactDAO().getContacts());

        contactsRoomAdapter = new ContactsRoomAdapter(this, contactRoomArrayList, Part02_1RoomdbActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsRoomAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }


        });



    }


    public void addAndEditContacts(final boolean isUpdate, final ContactRoomDB contactSQLite, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.layout_add_contact, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Part02_1RoomdbActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText newContact = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        contactTitle.setText(!isUpdate ? "Add New Contact" : "Edit Contact");

        if (isUpdate && contactSQLite != null) {
            newContact.setText(contactSQLite.getName());
            contactEmail.setText(contactSQLite.getEmail());
        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                if (isUpdate) {

                                    deleteContact(contactSQLite, position);
                                } else {

                                    dialogBox.cancel();

                                }

                            }
                        });


        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(newContact.getText().toString())) {
                    Toast.makeText(Part02_1RoomdbActivity.this, "Enter contact name!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }


                if (isUpdate && contactSQLite != null) {

                    updateContact(newContact.getText().toString(), contactEmail.getText().toString(), position);
                } else {

                    createContact(newContact.getText().toString(), contactEmail.getText().toString());
                }
            }
        });
    }

    private void deleteContact(ContactRoomDB contactSQLite, int position) {

        contactRoomArrayList.remove(position);

        new DeleteContactAsyncTask().execute(contactSQLite);
    }

    private void updateContact(String name, String email, int position) {

        ContactRoomDB contactSQLite = contactRoomArrayList.get(position);

        contactSQLite.setName(name);
        contactSQLite.setEmail(email);

        new UpdateContactAsyncTask().execute(contactSQLite);


        contactRoomArrayList.set(position, contactSQLite);


    }

    private void createContact(String name, String email) {

        /*long id = contactAppDatabase.getContactDAO().addContact(new ContactRoomDB(0,name,email));
        ContactRoomDB contactSQLite = contactAppDatabase.getContactDAO().getContact(id);

        if (contactSQLite != null) {
            contactRoomArrayList.add(0, contactSQLite);
            contactsRoomAdapter.notifyDataSetChanged();
        }*/

        new CreateContactAsyncTask().execute(new ContactRoomDB(0,name,email));

    }


    private class GetAllContactAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            contactRoomArrayList.addAll(contactAppDatabase.getContactDAO().getContacts());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsRoomAdapter.notifyDataSetChanged();

        }
    }

    private class CreateContactAsyncTask extends AsyncTask<ContactRoomDB, Void, Void>{

        @Override
        protected Void doInBackground(ContactRoomDB... contactRoomDBS) {


            long id = contactAppDatabase.getContactDAO().addContact(contactRoomDBS[0]);


            ContactRoomDB contactSQLite = contactAppDatabase.getContactDAO().getContact(id);

            if (contactSQLite != null) {

                contactRoomArrayList.add(0, contactSQLite);

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsRoomAdapter.notifyDataSetChanged();

        }
    }

    private class UpdateContactAsyncTask extends AsyncTask<ContactRoomDB,Void,Void>{

        @Override
        protected Void doInBackground(ContactRoomDB... contactRoomDBS) {
            contactAppDatabase.getContactDAO().updateContact(contactRoomDBS[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsRoomAdapter.notifyDataSetChanged();
        }
    }

    private class DeleteContactAsyncTask extends AsyncTask<ContactRoomDB,Void,Void>{

        @Override
        protected Void doInBackground(ContactRoomDB... contactRoomDBS) {
            contactAppDatabase.getContactDAO().deleteContact(contactRoomDBS[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsRoomAdapter.notifyDataSetChanged();

        }
    }



    RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("TAG", "onCreate: "+db.toString());

            createContact("name 1","email 1");
            createContact("name 2","email 2");
            createContact("name 3","email 3");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("TAG", "onOpen: "+db.toString());
        }
    };


}