package com.haihd1.admoblib.admob_builder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.ump.FormError;
import com.haihd1.admoblib.abstract_factory.factory_method.model.ads_native.NativeManager;
import com.haihd1.admoblib.abstract_factory.factory_method.model.banner.BannerManager;
import com.haihd1.admoblib.abstract_factory.factory_method.model.banner.COLLAPSE_BANNER_POSITION;
import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.InterstitialManager;

import java.util.concurrent.atomic.AtomicBoolean;

public class AdmobManager {
//    private AdmobHelper admob;

    private static AdmobManager INSTANCE;
    public static String id_test_banner = "ca-app-pub-3940256099942544/6300978111";
    public static String id_test_banner_2 = "ca-app-pub-3940256099942544/9214589741";
    public static String id_test_inter = "ca-app-pub-3940256099942544/1033173712";
    public static String id_test_collapse_banner = "ca-app-pub-3940256099942544/2014213617";
    public static String id_test_native = "ca-app-pub-3940256099942544/2247696110";


    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private GoogleMobileAdsConsentManager googleMobileAdsConsentManager;

    public interface UMPResultListener{
        public void  umpResultListener();
    }

    public AdmobManager() {

    }

    public static AdmobManager getInstance() {
        synchronized (AdmobManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new AdmobManager();
            }
        }
        return INSTANCE;
    }

    public AtomicBoolean getIsMobileAdsInitializeCalled() {
        return isMobileAdsInitializeCalled;
    }
    public void setIsMobileAdsInitializeCalled(){
        isMobileAdsInitializeCalled.set(true);
    }

    public void initializeMobileAdsSdk(Context context){
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }


        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(
                context,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        // Load an ad.
                        Log.e("initializeMobileAdsSdk", "init sdk");
                        isMobileAdsInitializeCalled.set(true);
                    }
                });
    }

    public void initUmp(Activity activity, boolean reset,UMPResultListener umpResultListener){
        googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(activity);
        googleMobileAdsConsentManager.gatherConsent(activity,reset, new GoogleMobileAdsConsentManager.OnConsentGatheringCompleteListener() {
            @Override
            public void consentGatheringComplete(FormError consentError) {
                if (consentError != null) {
                    // Consent not obtained in current session.
                    Log.w(
                            "aaaaaaaaaaaaa",
                            String.format(
                                    "%s: %s",
                                    consentError.getErrorCode(),
                                    consentError.getMessage()));
                }
                if (googleMobileAdsConsentManager.canRequestAds()) {
                    initializeMobileAdsSdk(activity);
                }
                umpResultListener.umpResultListener();
            }
        });

        if (googleMobileAdsConsentManager.canRequestAds()) {
            initializeMobileAdsSdk(activity);
        }
    }

    ///-----------------------------------------
    /// banner ads
    public void loadBanner(Activity activity, String id, ViewGroup frameLayout) {
        BannerManager.getInstance().loadBanner(activity, id, frameLayout);
    }

    public void loadBanner(Activity activity, String id, ViewGroup frameLayout, AdmobCallBack admobCallBack) {
        BannerManager.getInstance().loadBanner(activity, id, frameLayout, admobCallBack);
    }


    public void loadBanner(Activity activity, String id, ViewGroup frameLayout, Lifecycle lifecycle, Boolean reload) {
        BannerManager.getInstance().loadBanner(activity, lifecycle, id, frameLayout, reload);
    }

    public void loadBanner(Activity activity, String id, ViewGroup frameLayout, Boolean reload, Lifecycle lifecycle, AdmobCallBack admobCallBack) {
        BannerManager.getInstance().loadBanner(activity, lifecycle, id, frameLayout, reload, admobCallBack);
    }

    ///-----------------------------------------
    // CollapseBanner ads
    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position) {
        BannerManager.getInstance().loadCollapseBanner(activity, id, frameLayout, position);
    }

    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, AdmobCallBack admobCallBack) {
        BannerManager.getInstance().loadCollapseBanner(activity, id, frameLayout, position, admobCallBack);
    }


    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, Lifecycle lifecycle, boolean reload) {
        BannerManager.getInstance().loadCollapseBanner(activity, lifecycle, id, frameLayout, position, reload);
    }

    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, Lifecycle lifecycle, boolean reload, AdmobCallBack admobCallBack) {
        BannerManager.getInstance().loadCollapseBanner(activity, lifecycle, id, frameLayout, position, reload, admobCallBack);
    }

    ///-----------------------------------------
    // Native ads
    public void loadNative(Activity activity, String id, ViewGroup viewGroup, int resource, int layoutShimmer) {
        NativeManager.getInstance().loadNative(activity, id, viewGroup, resource, layoutShimmer);
    }

    public void loadNative(Activity activity, String id, ViewGroup viewGroup, int resource, int layoutShimmer, AdmobCallBack admobCallBack) {
        NativeManager.getInstance().loadNative(activity, id, viewGroup, resource, layoutShimmer, admobCallBack);
    }

    public void loadNative(Activity activity, String id, ViewGroup viewGroup, int resource, int layoutShimmer, Lifecycle lifecycle, boolean reload) {
        NativeManager.getInstance().loadNative(activity, id, viewGroup, resource, layoutShimmer, lifecycle, reload);
    }

    public void loadNative(Activity activity, String id, ViewGroup viewGroup, int resource, int layoutShimmer, Lifecycle lifecycle, boolean reload, AdmobCallBack admobCallBack) {
        NativeManager.getInstance().loadNative(activity, id, viewGroup, resource, layoutShimmer, lifecycle, reload, admobCallBack);
    }

    //----------------------
    // Inter ads

    public InterstitialManager createInter() {
        return new InterstitialManager();
    }


}
