package com.example.mvvmudemy01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.model.ContactRoomDB;
import com.example.mvvmudemy01.model.ContactSQLite;
import com.example.mvvmudemy01.view.Part02_1RoomdbActivity;
import com.example.mvvmudemy01.view.Part02_2RoomdbMainActivity;

import java.util.ArrayList;


public class ContactsRoomAdapter extends RecyclerView.Adapter<ContactsRoomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ContactRoomDB> contactssList;
    private Part02_1RoomdbActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView emil;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            emil = view.findViewById(R.id.email);

        }
    }


    public ContactsRoomAdapter(Context context, ArrayList<ContactRoomDB> contactSQLites, Part02_1RoomdbActivity mainActivity) {
        this.context = context;
        this.contactssList = contactSQLites;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final ContactRoomDB contactSQLite = contactssList.get(position);

        holder.name.setText(contactSQLite.getName());
        holder.emil.setText(contactSQLite.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                mainActivity.addAndEditContacts(true, contactSQLite, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return contactssList.size();
    }


}

