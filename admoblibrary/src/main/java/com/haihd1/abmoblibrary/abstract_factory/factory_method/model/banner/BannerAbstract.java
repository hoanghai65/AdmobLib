package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

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
import com.haihd1.abmoblibrary.R;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.event.AdType;
import com.haihd1.abmoblibrary.event.AdjustEventUtils;
import com.haihd1.abmoblibrary.event.AppsflyerEventUtils;
import com.haihd1.abmoblibrary.utils.CheckNetWork;

import java.lang.reflect.Type;

public abstract class BannerAbstract extends AdsModel implements AdmobHelper, LifecycleEventObserver {
    protected AdView adView;
    protected ViewGroup adContainerView;
    protected  Lifecycle mLifecycle ;
    protected Context mContext;


    abstract void loadBanner(Context context);
    public abstract void initPosition(COLLAPSE_BANNER_POSITION position);
    protected abstract void loadAdmob(Context context);
    public abstract void initLifecycle(Lifecycle lifecycle);
    public abstract void setReload(boolean reload);


    public void initView(Context context) {
        try {
            if (adContainerView != null) {
                if (CheckNetWork.isConnectNetWork(context)) {
                    View view = LayoutInflater.from(context).inflate(R.layout.banner_shimmer, null);
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

    protected AdSize getAdSize(Context context) {

        // Determine the screen width (less decorations) to use for the ad width.
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }
        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
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

    protected void onAdListener(AdView adView, TYPE type) {
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
                adView.setOnPaidEventListener(adValue -> {
                    AdjustEventUtils.getInstance().trackRevenue(adView.getResponseInfo().getLoadedAdapterResponseInfo(), adValue);
                    AppsflyerEventUtils.logPaidAdImpression(mContext,
                            adValue,
                            adView.getAdUnitId(), type);
                });
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
