package com.example.fragmentandviewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DemoViewModel extends ViewModel {

    private MutableLiveData<String> fragmentOneLiveData;
    private MutableLiveData<String> fragmentTwoLiveData;

    public DemoViewModel() {
        fragmentOneLiveData = new MutableLiveData<String>("Fragment One");
        fragmentTwoLiveData = new MutableLiveData<String>("Fragment Two");
    }

    public MutableLiveData<String> getFragmentOneLiveData() {
        return fragmentOneLiveData;
    }

    public MutableLiveData<String> getFragmentTwoLiveData() {
        return fragmentTwoLiveData;
    }
}

// Notes:

// 1. There's not real special set up you need to do for the ViewModel itself. The main advantage
// of the class is that you can refer to the same instance of this class in multiple fragments or
// activities easily, making it easy to store you logic and state data here. See the notes under
// FragmentOne details on using the class.
//
// 2. This is meant to be used with LiveData objects, which various activities and fragments can
// observe. Because observing an object is most often about reacting to it changing, it seems to me
// that you are most likely going to use MutableLiveData (not the immutable version) most of the
// time. You must specify the <datatype> for a LiveData object, and these must be Objects (no
// primitives, so Double, not double). The argument passed when instantiating it sets the data
// value (though you can leave it blank and it will be null, I think). Otherwise you have
// LiveData.set(arg) and LiveData.get() methods for retrieving and mutating the value.
