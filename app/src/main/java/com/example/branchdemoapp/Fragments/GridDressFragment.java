package com.example.branchdemoapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.branchdemoapp.DressAdapter;
import com.example.branchdemoapp.Models.ShopModel;
import com.example.branchdemoapp.R;

import java.util.ArrayList;

public class GridDressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dress_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Variable To See if navigated from DeepLink
        int pos = -1;

        Bundle args = getArguments();
        if(args.containsKey("pos")){
            pos = Integer.parseInt(args.getString("pos"));
        }

        GridView dressGrid = getActivity().findViewById(R.id.gridView);

        ArrayList<ShopModel> shopModelArrayList = new ArrayList<>();

        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress1),"EYEBOGLER",182));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress2),"KRYPTIC",465));
        shopModelArrayList.add(new ShopModel(ContextCompat.getDrawable(getContext(),R.drawable.dress3),"CAMPUS",1200));

        DressAdapter adapter = new DressAdapter(getContext(),shopModelArrayList,pos,getView());
        dressGrid.setAdapter(adapter);
    }
}