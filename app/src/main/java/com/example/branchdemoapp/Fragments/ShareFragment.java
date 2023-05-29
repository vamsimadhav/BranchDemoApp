package com.example.branchdemoapp.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.branchdemoapp.Helper;
import com.example.branchdemoapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ShareSheetStyle;

public class ShareFragment extends Fragment {

    private BranchUniversalObject buoShareSheet;
    private BranchUniversalObject buoShare;
    private LinkProperties lpShareSheet;
    private LinkProperties lpShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton shareLink = getActivity().findViewById(R.id.shareSheetButton);
        MaterialButton shareButton = getActivity().findViewById(R.id.androidShareSheetButton);

       //For Share Sheet
       buoShareSheet = new BranchUniversalObject()
               .setTitle("Share Sheet Deep Link")
               .setCanonicalIdentifier("shareSheet/deeplink")
               .setContentDescription(Helper.shareSheetText)
               .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);

        lpShareSheet = new LinkProperties()
                        .setCampaign("shareSheetCampaign")
                        .setFeature("ShareSheetDeepLink");

        //For Android Share
        buoShare = new BranchUniversalObject()
                .setTitle("Share Android Deep Link")
                .setCanonicalIdentifier("shareAndroid/deeplink")
                .setContentDescription(Helper.shareAndroidText)
                .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);

        lpShare = new LinkProperties()
                .setCampaign("shareCampaign")
                .setCampaign("ShareAndroidDeepLink");

       shareLink.setOnClickListener(view1 -> shareSheetDeepLink());
       shareButton.setOnClickListener(view12 -> {
           createShareDeepLink();
           //Share Event in Live View
           new BranchEvent(BRANCH_STANDARD_EVENT.SHARE)
                   .addContentItems(buoShare)
                   .setDescription("Android_Share_Event")
                   .logEvent(getContext());
       });
    }

    private void createShareDeepLink() {
        buoShare.generateShortUrl(getContext(), lpShare, (Branch.BranchLinkCreateListener) (url, error) -> {
            if (error == null) {
                Log.i("BRANCH SDK", "got my Branch link to share: " + url);
                shareUrl(url);
            }
        });
    }

    private void shareSheetDeepLink() {
        ShareSheetStyle shareSheetStyle = new ShareSheetStyle(getActivity(), "Check this out!", "This stuff is awesome: ")
                .setCopyUrlStyle(ContextCompat.getDrawable(getContext(),android.R.drawable.ic_menu_send), "Copy", "Added to clipboard")
                .setMoreOptionStyle(ContextCompat.getDrawable(getContext(),android.R.drawable.ic_menu_search), "Show more")
//                .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
                .setAsFullWidthStyle(true)
                .setSharingTitle("Share With")
                .setDefaultURL(Helper.logo);

        buoShareSheet.showShareSheet(getActivity(),
                lpShareSheet,
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

    private void shareUrl(String url){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);

        // Check if there are any apps that can handle the sharing Intent
        PackageManager packageManager = getContext().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(shareIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start the sharing Intent if there are suitable apps available
        if (isIntentSafe) {
            startActivity(Intent.createChooser(shareIntent, "Share Deep Link via"));
        } else {
            // Handle the case when no apps can handle the sharing Intent
            Toast.makeText(getContext(), "No apps available to handle sharing", Toast.LENGTH_SHORT).show();
        }
    }

}