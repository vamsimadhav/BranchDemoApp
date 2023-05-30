package com.example.branchdemoapp.Activitys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.branchdemoapp.R;
public class DisplayDeepLink extends Fragment {

    public DisplayDeepLink() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_deep_link, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView deppLink = getActivity().findViewById(R.id.deepLinkData);

        Bundle args = getArguments();
        if(args.containsKey("pos")){
            deppLink.setText(args.getString("pos"));
        } else {
            deppLink.setText("DEFAULT");
        }


    }
}