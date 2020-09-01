package com.example.mvvmudemy01.model.databindingModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmudemy01.BR;

public class ContactDatabinding extends BaseObservable {

    private int id;
    private String name,email;


    public ContactDatabinding( String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
