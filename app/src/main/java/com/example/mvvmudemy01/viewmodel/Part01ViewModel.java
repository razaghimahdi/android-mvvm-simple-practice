package com.example.mvvmudemy01.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Part01ViewModel extends ViewModel {

    /**NOTE:
     * We should not create instance of views in view model classes.
     * View model should not know which view is going to use it.
     * Instead, We create instances of viewModel classes inside views.
     * */

    private int clickCount = 0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();/**NOTE: MutableLiveData allows us to update data using setValue() and postValue() methods.*/
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


    public void getCurrentCount(){/**NOTE: when in Part01ViewModelActivity,button get clicked, getCurrentCount method value of the count will increase by one.
     Now when this happen, TextView should automatically update.*/

        clickCount++;
        countLiveData.setValue(clickCount);
    }


}
