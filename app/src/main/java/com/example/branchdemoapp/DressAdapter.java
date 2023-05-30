package com.example.branchdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.branchdemoapp.Fragments.DressFragmentArgs;
import com.example.branchdemoapp.Models.ShopModel;

import java.util.List;

public class DressAdapter extends ArrayAdapter<ShopModel> {
    public DressAdapter(@NonNull Context context, @NonNull List<ShopModel> shopModelList) {
        super(context, 0, shopModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        ShopModel shopModel = getItem(position);
        ImageView dressImage = listItemView.findViewById(R.id.dressImage);
        TextView dressName = listItemView.findViewById(R.id.dressName);
        TextView dressCost = listItemView.findViewById(R.id.dressCost);

        dressImage.setImageDrawable(shopModel.getDressImage());
        dressName.setText(shopModel.getDressName());
        String cost = "₹ " + shopModel.getDressCost();
        dressCost.setText(cost);
        listItemView.setOnClickListener(view -> {
            Bundle args = new com.example.branchdemoapp.Fragments.DressFragmentArgs.Builder(shopModel).build().toBundle();
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.dressFragment,args);
        });

        return listItemView;
    }
}
