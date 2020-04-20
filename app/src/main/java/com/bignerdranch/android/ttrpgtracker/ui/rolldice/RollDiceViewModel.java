package com.bignerdranch.android.ttrpgtracker.ui.rolldice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RollDiceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RollDiceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the dice rolling fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}