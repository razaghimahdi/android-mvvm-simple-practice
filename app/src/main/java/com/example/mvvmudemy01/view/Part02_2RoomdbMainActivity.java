package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.ContactsSQLiteAdapter;
import com.example.mvvmudemy01.db.DatabaseHelper;
import com.example.mvvmudemy01.model.ContactSQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Part02_2RoomdbMainActivity extends AppCompatActivity {


    private ContactsSQLiteAdapter contactsSQLiteAdapter;
    private ArrayList<ContactSQLite> contactSQLiteArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part02_2_roomdb_main);



        recyclerView = findViewById(R.id.recycler_view_contacts);
        db = new DatabaseHelper(this);

        contactSQLiteArrayList.addAll(db.getAllContacts());

        contactsSQLiteAdapter = new ContactsSQLiteAdapter(this, contactSQLiteArrayList, Part02_2RoomdbMainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsSQLiteAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }


        });



    }


    public void addAndEditContacts(final boolean isUpdate, final ContactSQLite contactSQLite, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.layout_add_contact, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Part02_2RoomdbMainActivity.this);
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
                    Toast.makeText(Part02_2RoomdbMainActivity.this, "Enter contact name!", Toast.LENGTH_SHORT).show();
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

    private void deleteContact(ContactSQLite contactSQLite, int position) {

        contactSQLiteArrayList.remove(position);
        db.deleteContact(contactSQLite);
        contactsSQLiteAdapter.notifyDataSetChanged();
    }

    private void updateContact(String name, String email, int position) {

        ContactSQLite contactSQLite = contactSQLiteArrayList.get(position);

        contactSQLite.setName(name);
        contactSQLite.setEmail(email);

        db.updateContact(contactSQLite);

        contactSQLiteArrayList.set(position, contactSQLite);

        contactsSQLiteAdapter.notifyDataSetChanged();


    }

    private void createContact(String name, String email) {

        long id = db.insertContact(name, email);


        ContactSQLite contactSQLite = db.getContact(id);

        if (contactSQLite != null) {

            contactSQLiteArrayList.add(0, contactSQLite);
            contactsSQLiteAdapter.notifyDataSetChanged();

        }

    }



}