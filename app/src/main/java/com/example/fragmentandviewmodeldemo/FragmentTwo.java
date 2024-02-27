package com.example.fragmentandviewmodeldemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentTwo extends Fragment {

    public FragmentTwo() {
        super(R.layout.fragment_two);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DemoViewModel viewModel = new ViewModelProvider(requireActivity()).get(DemoViewModel.class);

        TextView fragTwoTextView = requireView().findViewById(R.id.fragTwoTextView);

        final Observer<String> textObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fragTwoTextView.setText(s);
            }
        };

        viewModel.getFragmentTwoLiveData().observe(getViewLifecycleOwner(), textObserver);
    }
}
