package com.example.branchdemoapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.QRCode.BranchQRCode;
import io.branch.referral.util.LinkProperties;

public class QRFragment extends Fragment {

    private ImageView qrImage;
    private MaterialButton getQR;
    private MaterialButton shareQR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qrImage = view.findViewById(R.id.qrImage);
        getQR = view.findViewById(R.id.getQR);
        shareQR = view.findViewById(R.id.shareQR);

        getQR.setOnClickListener(view1 -> setUpQRCode());
    }

    private void setUpQRCode(){
        try {
            BranchQRCode qrCode = new BranchQRCode() //All QR code settings are optional
                    .setCodeColor("#a4c639")
                    .setBackgroundColor(Color.WHITE)
                    .setMargin(1)
                    .setWidth(512)
                    .setImageFormat(BranchQRCode.BranchImageFormat.PNG);

            BranchUniversalObject buo = new BranchUniversalObject()
                    .setTitle("My QR Code");

            LinkProperties lp = new LinkProperties();

            qrCode.getQRCodeAsImage(getActivity(), buo, lp, new BranchQRCode.BranchQRCodeImageHandler() {
                @Override
                public void onSuccess(Bitmap qrCodeImage) {
                    qrImage.setImageBitmap(qrCodeImage);
                    qrImage.setVisibility(View.VISIBLE);

                    shareQR.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        } catch (IOException e){
            Log.d("GET QR",e.getMessage());
        }

    }


//    private class GenerateQRCode extends AsyncTask<Void,Void,Bitmap>{
//
//        @Override
//        protected Bitmap doInBackground(Void... voids) {
//            try {
//                BranchQRCode qrCode = new BranchQRCode() //All QR code settings are optional
//                        .setCodeColor("#a4c639")
//                        .setBackgroundColor(Color.WHITE)
//                        .setMargin(1)
//                        .setWidth(512)
//                        .setImageFormat(BranchQRCode.BranchImageFormat.PNG);
//
//                BranchUniversalObject buo = new BranchUniversalObject()
//                        .setTitle("My QR Code");
//
//                LinkProperties lp = new LinkProperties();
//                Bitmap bitmap = null;
//                qrCode.getQRCodeAsImage(getActivity(), buo, lp, new BranchQRCode.BranchQRCodeImageHandler() {
//                    @Override
//                    public void onSuccess(Bitmap qrCodeImage) {
//                        bitmap = qrCodeImage;
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//
//                    }
//                });
//            }catch (IOException e){
//                Log.d("QR ERROR",e.getMessage());
//            }
//        }
//    }
}