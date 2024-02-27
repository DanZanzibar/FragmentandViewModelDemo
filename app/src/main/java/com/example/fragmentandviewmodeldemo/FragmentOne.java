package com.example.fragmentandviewmodeldemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentOne extends Fragment {

    public FragmentOne() {
        super(R.layout.fragment_one);
    }

    // Note that I use the IDE's 'insert' feature to add getters/setters and overridden methods.
    // Ask me about this if you haven't found it yet... it's helpful.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This is all you need to get the ViewModel instance. Note the '.class' on the end of the
        // classname.
        DemoViewModel viewModel = new ViewModelProvider(requireActivity()).get(DemoViewModel.class);

        // The rest is all about setting up the observer so that the TextView always has the value
        // stored in the ViewModel's 'fragmentOneLiveData' LiveData object.
        TextView fragOneTextView = requireView().findViewById(R.id.fragOneTextView);

        // Note the <String> matches the LiveData<String> object we are observing.
        final Observer<String> textObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fragOneTextView.setText(s);
            }
        }; // A sneaky semicolon to watch for.

        viewModel.getFragmentOneLiveData().observe(getViewLifecycleOwner(), textObserver);
    }
}

/*
 Notes:
 1. About instantiating the ViewModel: That one line either creates or gets the ViewModel instance.
 A given ViewModel is defined by two things: (1) the activity or fragment that is its
 "LifecycleOwner" (meaning it will exist so long as it exists) and (2) the actual subclass of
 ViewModel that it is (in this case, DemoViewModel). In this example we use requireActivity() to
 make the containing activity - the MainActivity - the LifecycleOwner, so any activity or
 fragment that could refer to the MainActivity could get the ViewModel. The idea is that only one
 instance of DemoViewModel will be created - provided it matches both those 2 criteria - and all
 references to it will be the same instance. This allows a nice easy place to store logic and
 communicate between the various fragments and activities. In my calculator app, this is where
 I stored most of the logic and just left the fragments to set up buttons using methods in the
 ViewModel and other classes that were stored in the ViewModel as variables/fields.

 2. The final step in setting up the observer requires that you get the LiveData object (in this
 case by 'viewModel.getFragmentOneLiveData()' and use its 'observe' method. The parameters are (1)
 the LifecycleOwner you want for the observer and (2) the Observer itself that has the method that
 gets invoked when the LiveData value changes. To elaborate: the idea is that LiveData pays
 attention to which of its observers are active and only notifies them and invoked their 'onChanged'
 methods. So in this case we want the LifecycleOwner of the observer to be the fragment that has the
 TextView that will update. You used to be able to put 'this' in for that parameter, but the proper
 way now is to use getViewLifecycleOwner() to specify the current View.

 3. A thing that is observing LiveData will invoke 'onChanged' as well when it is first created. So
 it guarantees that it will always be matching the LiveData object if set up in this method.

 4. Lastly, I don't use Android Studio's "add a fragment" or whatever it is called functionality,
 because it adds a bunch of boilerplate code that I don't understand. I always simply add a java or
 xml file and fill in everything (except I use Android Studio's insert getters/setters and
 overridden methods like I mention earlier... but this doesn't do things I don't understand).
*/

