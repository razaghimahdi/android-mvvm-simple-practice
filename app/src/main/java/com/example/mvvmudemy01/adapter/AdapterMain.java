package com.example.mvvmudemy01.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvvmudemy01.view.Part01ViewModelActivity;
import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.model.infoMain;
import com.example.mvvmudemy01.view.Part03DataBindingActivity;
import com.example.mvvmudemy01.view.Part04ProjectActivity;
import com.example.mvvmudemy01.view.Part05Activity;
import com.example.mvvmudemy01.view.Part06PagingActivity;
import com.example.mvvmudemy01.view.part07.Part07WorkManagerActivity;
import com.example.mvvmudemy01.view.part08.Part08NavigationActivity;
import com.example.mvvmudemy01.view.roomView.Part02RoomdbActivity;

import java.util.Collections;
import java.util.List;


public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {
    private LayoutInflater inflater;
    List<infoMain> data = Collections.emptyList();
    private Context context;



    public AdapterMain(Context context, List<infoMain> data){
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }


    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent , false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        infoMain cur = data.get(position);
    holder.title.setText(cur.title);
        //Typeface font = Typeface.createFromAsset(context.getAssets(), "AGhasem.ttf");
       // holder.title.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        LinearLayout linear_main_row;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_nav_title);
            linear_main_row = itemView.findViewById(R.id.linear_main_row);

            linear_main_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getPosition() == 0){
                        Intent intent = new Intent(context, Part01ViewModelActivity.class);
                        context.startActivity(intent);
                    }
                    if (getPosition() == 1){
                        Intent intent = new Intent(context, Part02RoomdbActivity.class);
                        context.startActivity(intent);
                    }
                    if (getPosition() == 2){
                        Intent intent = new Intent(context, Part03DataBindingActivity.class);
                        context.startActivity(intent);
                    }
                    if (getPosition() == 3){
                        Intent intent = new Intent(context, Part04ProjectActivity.class);
                        context.startActivity(intent);
                    }

                    if (getPosition() == 4){
                        Intent intent = new Intent(context, Part05Activity.class);
                        context.startActivity(intent);
                    }
                    if (getPosition() == 5){
                        Intent intent = new Intent(context, Part06PagingActivity.class);
                        context.startActivity(intent);
                    }

                    if (getPosition() == 6){
                        Intent intent = new Intent(context, Part07WorkManagerActivity.class);
                        context.startActivity(intent);
                    }
                    if (getPosition() == 7){
                        Intent intent = new Intent(context, Part08NavigationActivity.class);
                        context.startActivity(intent);
                    }
                }
            });


        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"This Position" + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }


}
