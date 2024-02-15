package com.haihd1.admoblib.abstract_factory.factory_method.model.ads_native;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaAspectRatio;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.haihd1.admoblib.R;
import com.haihd1.admoblib.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.admoblib.admob_builder.AdmobCallBack;
import com.haihd1.admoblib.abstract_factory.AdmobHelper;
import com.haihd1.admoblib.admob_builder.AdmobManager;

import java.util.List;

public class NativeModel extends NativeAbstract {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;

    @Override
    public void initLifecycle(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
        mLifecycle.addObserver(this);
    }

    @Override
    public void initId(String id) {
        AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {
        AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup frameLayout) {
        adContainerView = frameLayout;
    }

    @Override
    public void initResource(int resource) {
        mResource = resource;
    }

    @Override
    public void initShimmer(int shimmer) {
        mShimmer = shimmer;
    }

    @Override
    public void setReload(boolean reload) {
        mReload = reload;
    }

    @Override
    public void loadAdmob(Activity activity) {
        mActivity = activity;
        initView(activity);
        Log.e("initializeMobileAdsSdk", "initializeMobileAdsSdk: " + AdmobManager.getInstance().getIsMobileAdsInitializeCalled().get());
        AdmobManager.getInstance().initializeMobileAdsSdk(activity);
        if (adContainerView != null) {
            Log.e("zzzzzzzzzzzzz", "reloadAdmob:ttt " + adContainerView);
            adContainerView.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            Log.e("zzzzzzzzzzzz", "ccccccccccccccccccc: loadNative");

                            if (!initialLayoutComplete.getAndSet(true)) {
                                loadNative(activity);
                                Log.e("zzzzzzzzzzzz", "loadAdmob: loadNative");
                            }
                        }
                    });
        }
    }

    @Override
    public void reloadAdmob() {
        try {
            Log.e("nnnnnnnnnn", "reloadAdmob:eee " + isLoading + "  " + mActivity);

            if (AD_UNIT_ID != null && adContainerView != null && mActivity != null && !isLoading) {
                initView(mActivity);
                loadNative(mActivity);
            }
        } catch (Exception exception) {
            Log.e("nnnnnnnnnn", "exception: " + exception.getMessage());
        }

    }

    @Override
    public void setAdmobCallBack(AdmobCallBack admobCallBack) {
        this.admobCallBack = admobCallBack;
    }

    @Override
    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }


    @Override
    void loadNative(Activity activity) {
        Log.e("zzzzzzzzzzzzz", "loadNative: ");
        MobileAds.initialize(activity);
        AdLoader.Builder builder = new AdLoader.Builder(activity, AD_UNIT_ID);
        VideoOptions videoOptions =
                new VideoOptions.Builder().setStartMuted(false).setCustomControlsRequested(true).build();
        NativeAdOptions adOptions =
                new NativeAdOptions.Builder()
                        .setMediaAspectRatio(MediaAspectRatio.ANY)
                        .setVideoOptions(videoOptions)
                        .build();
        builder.withNativeAdOptions(adOptions);

        builder.forNativeAd(
                new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(final NativeAd nativeAd) {
                        isLoading = false;
                        if (mNativeAd != null) {
                            mNativeAd.destroy();
                        }
                        mNativeAd = nativeAd;
                        nativeAdview = (NativeAdView) activity.getLayoutInflater().inflate(mResource, adContainerView, false);
                        populateNativeAdView(nativeAd, nativeAdview);
                        replaceView();
//                        adContainerView.removeAllViews();
//                        adContainerView.addView(nativeAdview);
                        Log.e("nnnnnnnnnnn", "onNativeAdLoaded: 3   " + activity.hashCode() + "   " + AD_UNIT_ID);

                    }
                });

        builder.withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("nnnnnnnnnnn", "onAdFailedToLoad: " + loadAdError.toString());
                        removeView();
                        isLoading = false;
                        admobCallBack.onAdFailedToLoad();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        isLoading = false;
                        admobCallBack.onAdLoaded();

                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        admobCallBack.onAdClicked();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        admobCallBack.onAdImpression();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        admobCallBack.onAdClosed();
                    }
                });
        adLoader = builder.build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }


    @Override
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        Log.e("LifeCycleEvent", "onStateChanged: ");
        switch (event) {
            case ON_CREATE: {
                Log.e("LifeCycleEvent", "native on create");
                break;
            }
            case ON_START: {
                Log.e("LifeCycleEvent", "native on start");
                if (mReload) {
                    reloadAdmob();
                }
                break;
            }
            case ON_RESUME: {
                Log.e("LifeCycleEvent", "native on Resume");
                break;
            }
            case ON_PAUSE: {
                Log.e("LifeCycleEvent", "native on pause");
                break;
            }
            case ON_STOP: {
                Log.e("LifeCycleEvent", "native on stop");
                break;
            }
            case ON_DESTROY: {
                Log.e("LifeCycleEvent", "native on destroy");
                if (mNativeAd != null) {
                    mNativeAd.destroy();
                }
                if (mLifecycle != null) {
                    mLifecycle.removeObserver(this);
                }
                break;
            }
            case ON_ANY: {
                Log.e("LifeCycleEvent", "native on any");
                break;
            }
        }
    }
}
