package com.example.branchdemoapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.branchdemoapp.R;
import com.google.android.material.button.MaterialButton;

import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;

public class OptionsFragment extends Fragment {
    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton shareLinkButton = getActivity().findViewById(R.id.shareLink);
        MaterialButton shoppingButton = getActivity().findViewById(R.id.shopping);

        shareLinkButton.setOnClickListener(view12 -> {
            Navigation.findNavController(view12).navigate(R.id.shareFragment);
        });
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.shoppingActivity);
            }
        });

    }
}