package com.example.branchdemoapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class OptionsFragment extends Fragment {

    private MaterialButton qrButton;
    private MaterialButton shareLinkButton;
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
        qrButton = getActivity().findViewById(R.id.qrButton);
        shareLinkButton = getActivity().findViewById(R.id.shareLink);

//        qrButton.setOnClickListener(view1 -> {
//            Navigation.findNavController(view1).navigate(R.id.QRFragment);
//        });

        shareLinkButton.setOnClickListener(view12 -> {
            Navigation.findNavController(view12).navigate(R.id.shareFragment);
        });

    }
}