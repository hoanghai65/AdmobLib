package com.haihd1.abmoblibrary.admob_builder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.ump.FormError;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native.NativeManager;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardManager;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.BannerManager;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.COLLAPSE_BANNER_POSITION;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialManager;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AdsApplication;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AppOpenAdManager;
import com.haihd1.abmoblibrary.observer.Observer;
import com.haihd1.abmoblibrary.observer.Subject;
import com.haihd1.abmoblibrary.utils.TimeShowInter;
import com.haihd1.abmoblibrary.utils.ShowAdsSplashHelper;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.utils.callback.UMPResultListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class AdmobManager extends Observer {
//    private AdmobHelper admob;
    private static AdmobManager INSTANCE;
    public static String id_test_banner = "ca-app-pub-3940256099942544/6300978111";
    public static String id_test_banner_2 = "ca-app-pub-3940256099942544/9214589741";
    public static String id_test_inter = "ca-app-pub-3940256099942544/1033173712";
    public static String id_test_collapse_banner = "ca-app-pub-3940256099942544/2014213617";
    public static String id_test_native = "ca-app-pub-3940256099942544/2247696110";


    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private GoogleMobileAdsConsentManager googleMobileAdsConsentManager;

    @Override
    public void onListenerLoadedAds(Activity activity, ActionCallBack actionCallBack, TYPE type) {
        if (activity != null && actionCallBack != null) {
            if (type == TYPE.INTER){
                InterstitialManager.getInstance().showInterSplash(activity, new ActionCallBack() {
                    @Override
                    public void onNextAction() {
                        removeObserver();
                        actionCallBack.onNextAction();
                    }
                });
            }
            else if (type == TYPE.APP_OPEN){
                AppOpenAdManager.getInstance().showAdIfAvailable(activity, new AdsApplication.OnShowAdCompleteListener() {
                    @Override
                    public void onShowAdComplete() {
                        actionCallBack.onNextAction();
                        removeObserver();
                    }
                });
            }

//
        }
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

    public void setIsMobileAdsInitializeCalled() {
        isMobileAdsInitializeCalled.set(true);
    }

    public void initializeMobileAdsSdk(Context context) {
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


    public void initUmp(Activity activity, boolean reset, UMPResultListener umpResultListener) {
        TimeShowInter.upDateTimeForStartFromInterval();
        googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(activity);
        googleMobileAdsConsentManager.gatherConsent(activity, reset, new GoogleMobileAdsConsentManager.OnConsentGatheringCompleteListener() {
            @Override
            public void consentGatheringComplete(FormError consentError) {
                Log.e("tttttttttt", "consentGatheringComplete: " + GoogleMobileAdsConsentManager.getInstance(activity).getConsentResult(activity) + "   " + consentError);
                if (consentError != null) {
                    // Consent not obtained in current session.
                    Log.w(
                            "tttttttttt",
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

    public void adsSplash(Activity activity, String idAppOpenSplash, String idInterSplash, ActionCallBack appOpenCallBack, ActionCallBack interCallBack) {
        if (TYPE.APP_OPEN ==  ShowAdsSplashHelper.adsTypeSplash()) {
            obServerAppOpen(activity, appOpenCallBack);
            Log.e("Rate_Show", "adsSplash: open" );
            AppOpenAdManager.getInstance().loadAppOpenSplash(activity, idAppOpenSplash);
        }else {
            obServerInter(activity, interCallBack);
            Log.e("Rate_Show", "adsSplash: inter" );
            InterstitialManager.getInstance().loadInterSplash(activity, idInterSplash);
        }
    }

    private void obServerAppOpen(Activity activity, ActionCallBack actionCallBack) {
        Subject subject = new Subject();
        AppOpenAdManager.getInstance().setSubject(subject);
        AppOpenAdManager.getInstance().setActionCallBack(actionCallBack);
        AppOpenAdManager.getInstance().setActivity(activity);
        this.subject = subject;
        this.subject.attach(this);
    }

    private void obServerInter(Activity activity, ActionCallBack actionCallBack) {
        Subject subject = new Subject();
        InterstitialManager.getInstance().initInter();
        InterstitialManager.getInstance().setSubject(subject);
        InterstitialManager.getInstance().setActionCallBack(actionCallBack);
        InterstitialManager.getInstance().setActivity(activity);
        this.subject = subject;
        this.subject.attach(this);
    }


    private void removeObserver() {
        if (this.subject != null) {
            this.subject.remove(this);
            this.subject = null;
        }
    }

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

    // Reward ads
    public RewardManager createReward() {
        return new RewardManager();
    }


}
