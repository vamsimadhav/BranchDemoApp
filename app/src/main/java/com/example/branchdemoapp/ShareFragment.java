package com.example.branchdemoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.PrefHelper;
import io.branch.referral.QRCode.BranchQRCode;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.CommerceEvent;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.Product;
import io.branch.referral.util.ProductCategory;
import io.branch.referral.util.ShareSheetStyle;

public class ShareFragment extends Fragment {

    private MaterialButton shareLink;
    private BranchUniversalObject buo;
    private LinkProperties linkProperties;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       shareLink = getActivity().findViewById(R.id.shareButton);
       buo = new BranchUniversalObject()
               .setTitle("Share Deep Link")
               .setCanonicalIdentifier("share/deeplink")
               .setContentDescription("This make it possible to share the deep link to the app")
               .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);
        linkProperties = new LinkProperties()
                        .setFeature("sharing")
                        .setFeature("ShareDeepLink");

       shareLink.setOnClickListener(view1 -> shareDeepLink());
    }

    private void shareDeepLink() {
        ShareSheetStyle shareSheetStyle = new ShareSheetStyle(getActivity(), "Check this out!", "This stuff is awesome: ")
                .setCopyUrlStyle(ContextCompat.getDrawable(getContext(),android.R.drawable.ic_menu_send), "Copy", "Added to clipboard")
                .setMoreOptionStyle(ContextCompat.getDrawable(getContext(),android.R.drawable.ic_menu_search), "Show more")
//                .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
                .setAsFullWidthStyle(true)
                .setSharingTitle("Share With")
                .setDefaultURL("https://thenounproject.com/api/private/icons/1348584/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0&token=gAAAAABkdBySJ9y0_PJzfEKlQZtvSTLQFXrxx5349Yft-Zuy385ZL6A550aXmQjACzh_1oRTONCxXCiL_d3xtDB7G62qmvwOhg%3D%3D");

        buo.showShareSheet(getActivity(),
                linkProperties,
                shareSheetStyle,
                new Branch.ExtendedBranchLinkShareListener() {
                    @Override
                    public void onShareLinkDialogLaunched() {
                    }
                    @Override
                    public void onShareLinkDialogDismissed() {
                    }

                    @Override
                    public void onLinkShareResponse(String sharedLink, String sharedChannel, BranchError error) {
                    }
                    @Override
                    public void onChannelSelected(String channelName) {

                    }
                    @Override
                    public boolean onChannelSelected(String channelName, BranchUniversalObject buo, LinkProperties linkProperties) {
                        return false;
                    }
                });
    }

}