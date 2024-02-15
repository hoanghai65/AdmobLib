package com.haihd1.admoblib.abstract_factory.factory_method.model.banner;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.haihd1.admoblib.R;
import com.haihd1.admoblib.abstract_factory.AdmobHelper;
import com.haihd1.admoblib.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.admoblib.admob_builder.AdmobManager;
import com.haihd1.admoblib.utils.CheckNetWork;

import java.util.List;

public abstract class BannerAbstract extends AdsModel implements AdmobHelper, LifecycleEventObserver {
    protected AdView adView;
    protected ViewGroup adContainerView;
    protected  Lifecycle mLifecycle ;


    abstract void loadBanner(Activity activity);
    public abstract void initPosition(COLLAPSE_BANNER_POSITION position);

    public abstract void initLifecycle(Lifecycle lifecycle);
    public abstract void setReload(boolean reload);


    protected void initView(Activity activity) {
        try {
            if (adContainerView != null) {
                if (CheckNetWork.isConnectNetWork(activity)) {
                    View view = LayoutInflater.from(activity).inflate(R.layout.banner_shimmer, null);
                    adContainerView.removeAllViews();
                    adContainerView.addView(view);
                }else {
                    adContainerView.removeAllViews();
                    Log.e("ttttttttttttttt", "exception: " + "removeAllViews");
                }
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzzz", "exception: " + exception.getMessage());
        }

    }

    protected AdSize getAdSize(Activity activity) {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    protected void initializeMobileAdsSdk(Activity activity) {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(
                activity,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
//                        loadBanner(activity);
                        AdmobManager.getInstance().setIsMobileAdsInitializeCalled();
                    }
                });

    }

    protected void replaceView() {
        if (adContainerView != null && adView != null) {
            // Replace ad container with new ad view.
            adContainerView.removeAllViews();
            adContainerView.addView(adView);
        }
    }
    protected void removeView() {
        if (adContainerView != null) {
            adContainerView.removeAllViews();
        }
    }

    protected void onAdListener(AdView adView) {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.e("zzzzzzzzzzzz", "onAdImpression: ");
                admobCallBack.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                admobCallBack.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                isLoading = false;
                removeView();
                admobCallBack.onAdFailedToLoad();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.e("zzzzzzzzzzzz", "onAdImpression: ");
                admobCallBack.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                replaceView();
                isLoading = false;
                admobCallBack.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                admobCallBack.onAdOpened();
            }

            @Override
            public void onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked();
            }
        });
    }


}
