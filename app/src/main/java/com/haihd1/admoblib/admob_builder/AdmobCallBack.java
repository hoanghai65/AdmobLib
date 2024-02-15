package com.haihd1.admoblib.admob_builder;

public interface AdmobCallBack {
    // Code to be executed when the user clicks on an ad.
    void onAdClicked();

    // Code to be executed when the user is about to return
    // to the app after tapping on an ad.
    void onAdClosed();

    // Code to be executed when an ad request fails.
    void onAdFailedToLoad();

    // Code to be executed when an impression is recorded
    // for an ad.
    void onAdImpression();

    // Code to be executed when an ad finishes loading.
    void onAdLoaded();

    // Code to be executed when an ad opens an overlay that
    // covers the screen.
    void onAdOpened();

}
