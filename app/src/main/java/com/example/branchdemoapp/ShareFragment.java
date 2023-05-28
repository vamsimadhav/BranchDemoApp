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
       shareLink = getActivity().findViewById(R.id.shareLink);
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
                .setDefaultURL("data:image/jpeg;base64,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");

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