package com.example.branchdemoapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.branchdemoapp.Models.ShopModel;

public class DressFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DressFragmentArgs args = DressFragmentArgs.fromBundle(getArguments());

        ShopModel shopModel = args.getShopModel();

        ImageView dressImage = getActivity().findViewById(R.id.imageView);
        TextView dressName = getActivity().findViewById(R.id.textName);
        TextView dressCost = getActivity().findViewById(R.id.textCost);

        dressImage.setImageDrawable(shopModel.getDressImage());
        dressName.setText(shopModel.getDressName());
        String cost = "₹ " + shopModel.getDressCost();
        dressCost.setText(cost);
    }
}