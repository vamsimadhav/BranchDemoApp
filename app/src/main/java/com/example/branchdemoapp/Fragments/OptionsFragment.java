package com.example.branchdemoapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.branchdemoapp.Networking.ApiCompletionCallback;
import com.example.branchdemoapp.Networking.ApiHelper;
import com.example.branchdemoapp.R;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                sendApiEvent(view);
            }
        });

    }

    private void sendApiEvent(View view) {
        ApiHelper.sendStandardEvent(getContext(), new ApiCompletionCallback() {
            @Override
            public void onCompletion(boolean success) {
                if(success){
                    Navigation.findNavController(view).navigate(R.id.shoppingActivity);
                    Toast.makeText(getContext(),"Event Success",Toast.LENGTH_SHORT).show();
                } else{
                    Navigation.findNavController(view).navigate(R.id.shoppingActivity);
                    Toast.makeText(getContext(),"Event Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}