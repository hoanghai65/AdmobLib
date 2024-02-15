package com.haihd1.admoblib.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.haihd1.admoblib.R;
import com.haihd1.admoblib.abstract_factory.factory_method.model.TYPE;
import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.InterstitialManager;
import com.haihd1.admoblib.abstract_factory.factory_method.model.open_resume.AppOpenAdManager;
import com.haihd1.admoblib.admob_builder.AdmobManager;

public class MainActivity2 extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Button btnEnter;
    private InterstitialManager interAds;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        frameLayout = findViewById(R.id.fr_ads);
        btnEnter = findViewById(R.id.btn_enter);
        interAds = AdmobManager.getInstance().createInter();
        interAds.loadInter(this,"ca-app-pub-3940256099942544/8691691433");

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interAds.showInter(MainActivity2.this);
            }
        });
//        AdmobManager.getInstance()
//                .loadNative(this, "ca-app-pub-3940256099942544/7342230711", frameLayout, R.layout.native_full_screen, R.layout.native_full_screen_shimmer);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        AppOpenAdManager.getInstance().disableAppOpenResumeActivity(this);
    }
}