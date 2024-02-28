package com.haihd1.admoblib.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialManager;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager;
import com.haihd1.abmoblibrary.utils.callback.UMPResultListener;
import com.haihd1.admoblib.R;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private FrameLayout frameLayout2;
    private FrameLayout frameLayout3;
    private Button btnEnter;
    private InterstitialManager interstitialManager;

    private ActionCallBack appOpenCallBack = new ActionCallBack() {
        @Override
        public void onNextAction() {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));

        }
    };
    private ActionCallBack interCallBack = new ActionCallBack() {
        @Override
        public void onNextAction() {
//            startActivity(new Intent(MainActivity.this, MainActivity2.class));

        }
    };

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
        Log.e("aaaaaaaaaaaaa", "onCreate: rrrr");

        AdmobManager.getInstance().initUmp(MainActivity.this,
                !GoogleMobileAdsConsentManager.getInstance(this).getConsentResult(this),
                new UMPResultListener() {
                    @Override
                    public void umpResultListener() {
                        AdmobManager.getInstance().adsSplash(MainActivity.this,
                                    "ca-app-pub-3940256099942544/9257395921",
                                "ca-app-pub-3940256099942544/1033173712",
                                appOpenCallBack, interCallBack);
                        AdmobManager.getInstance()
                                .loadNative(MainActivity.this, "ca-app-pub-3940256099942544/2247696110", frameLayout, R.layout.native_larger, R.layout.native_large_shimmer, getLifecycle(), true);
                        AdmobManager.getInstance()
                                .loadBanner(MainActivity.this, "ca-app-pub-3940256099942544/6300978111", frameLayout2, getLifecycle(), true);
                    }
                });




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




    @Override
    protected void onResume() {
        super.onResume();
//        AppOpenAdManager.getInstance().disableAppOpenResumeActivity(this);
        Log.e("aaaaaaaaaaaaa", "onActivityResumed: 111");
    }
}
