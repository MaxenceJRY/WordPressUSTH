package vn.edu.usth.wordpress25.ui.me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is me fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}