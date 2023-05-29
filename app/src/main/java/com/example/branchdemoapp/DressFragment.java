package com.example.branchdemoapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.branchdemoapp.Models.ShopModel;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.CurrencyType;

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
        Button addToCart = getActivity().findViewById(R.id.addToCart);

        dressImage.setImageDrawable(shopModel.getDressImage());
        dressName.setText(shopModel.getDressName());
        String cost = "₹ " + shopModel.getDressCost();
        dressCost.setText(cost);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BranchUniversalObject buo = new BranchUniversalObject()
                        .setTitle("Add To Cart")
                        .setCanonicalIdentifier("addToCart")
                        .setContentDescription("Tracking Add to Cart ")
                        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);

                new BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART)
                        .addCustomDataProperty("Dress Name",shopModel.getDressName())
                        .addCustomDataProperty("Dress Cost", String.valueOf(shopModel.getDressCost()))
                        .setCurrency(CurrencyType.INR)
                        .addContentItems(buo)
                        .setCustomerEventAlias("Added Dress")
                        .setDescription("Men's Dress")
                        .logEvent(getContext());
                getActivity().finish();
            }
        });
    }
}