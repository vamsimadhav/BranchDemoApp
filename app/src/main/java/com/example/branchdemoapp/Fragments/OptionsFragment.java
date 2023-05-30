package com.example.branchdemoapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.branchdemoapp.Networking.ApiHelper;
import com.example.branchdemoapp.R;
import com.google.android.material.button.MaterialButton;

public class OptionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        shoppingButton.setOnClickListener(this::sendApiEvent);
    }

    private void sendApiEvent(View view) {

        ApiHelper.sendStandardEvent(getContext(), success -> {
            Bundle args = new Bundle();
            args.putString("pos","-1");
            if(success){
                Toast.makeText(getContext(),"API Event Success",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getContext(),"API Event Failed",Toast.LENGTH_SHORT).show();
            }
            Navigation.findNavController(view).navigate(R.id.gridDressFragment,args);
        });
    }
}