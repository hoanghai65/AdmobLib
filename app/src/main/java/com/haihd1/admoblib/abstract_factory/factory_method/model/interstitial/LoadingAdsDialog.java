package com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.haihd1.admoblib.R;


public class LoadingAdsDialog extends Dialog {

    public LoadingAdsDialog(@NonNull Context context) {
        super(context, R.style.Theme_AdmobLib);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_ads);
    }

    @Override
    public void onBackPressed() {

    }
}
