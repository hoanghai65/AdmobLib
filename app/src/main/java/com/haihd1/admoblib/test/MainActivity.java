package com.haihd1.admoblib.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialManager;
import com.haihd1.abmoblibrary.admob_builder.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager;
import com.haihd1.admoblib.R;
import com.haihd1.admoblib.abstract_factory.factory_method.model.open_resume.AppOpenAdManager;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private FrameLayout frameLayout2;
    private FrameLayout frameLayout3;
    private Button btnEnter;
    private InterstitialManager interstitialManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.ad_view_container);
        frameLayout2 = findViewById(R.id.banner_ads);
        frameLayout3 = findViewById(R.id.banner_ads_2);
        btnEnter = findViewById(R.id.btnEnter);
        interstitialManager = AdmobManager.getInstance().createInter();
        interstitialManager.loadInter(this, AdmobManager.id_test_inter);
        AdmobManager.getInstance().initUmp(this, !GoogleMobileAdsConsentManager.getInstance(this).getConsentResult(this),() -> {

//        admobManagerBanner
//                .loadCollapseBanner(this, "ca-app-pub-3940256099942544/2014213617", frameLayout2, COLLAPSE_BANNER_POSITION.top)
//                .build();
            AdmobManager.getInstance()
                    .loadNative(this, "ca-app-pub-3940256099942544/2247696110", frameLayout, R.layout.native_larger, R.layout.native_large_shimmer, getLifecycle(), true);
        });
        AdmobManager.getInstance()
                .loadBanner(this, "ca-app-pub-3940256099942544/6300978111", frameLayout2, getLifecycle(), true);

        AdmobManager.getInstance()
                .loadBanner(this, "ca-app-pub-3940256099942544/9214589741", frameLayout3, getLifecycle(), true);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialManager.showInter(MainActivity.this, new ActionCallBack() {
                    @Override
                    public void onNextAction() {
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                }, new AdmobCallBack() {
                    @Override
                    public void onAdClicked() {

                    }

                    @Override
                    public void onAdClosed() {

                    }

                    @Override
                    public void onAdFailedToLoad() {
                        Log.e("iiiiiiiiiiiiiiiii", "onAdFailedToLoad: 2222");
                    }

                    @Override
                    public void onAdImpression() {

                    }

                    @Override
                    public void onAdLoaded() {

                    }

                    @Override
                    public void onAdOpened() {

                    }
                });
            }
        });
    }



    private void loadNative() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        // Show the ad.
                        Log.e("zzzzzzzzzzzz", "onNativeAdLoaded: main");
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppOpenAdManager.getInstance().disableAppOpenResumeActivity(this);
        Log.e("aaaaaaaaaaaaa", "onActivityResumed: 111" );
    }
}
