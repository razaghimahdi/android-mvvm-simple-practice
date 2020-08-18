package com.example.mvvmudemy01.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Part01ViewModel extends ViewModel {
    private int clickCount = 0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
/*
    public int getInitialCount(){
        return clickCount;
    }

    public int getCurrentCount(){
        clickCount++;
        return clickCount;
    }
*/

    public MutableLiveData<Integer> getInitialCount(){
        countLiveData.setValue(clickCount);
        return countLiveData;
    }


    public void getCurrentCount(){
        clickCount++;
        countLiveData.setValue(clickCount);
    }


}
