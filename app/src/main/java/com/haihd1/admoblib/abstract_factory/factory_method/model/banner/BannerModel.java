package com.haihd1.admoblib.abstract_factory.factory_method.model.banner;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.haihd1.admoblib.R;
import com.haihd1.admoblib.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.admoblib.admob_builder.AdmobCallBack;
import com.haihd1.admoblib.abstract_factory.AdmobHelper;
import com.haihd1.admoblib.admob_builder.AdmobManager;

import java.util.List;

public class BannerModel extends BannerAbstract implements AdmobHelper {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;


    @Override
    public void initId(String id) {
        AD_UNIT_ID = id;
    }

    public void initListId(List<String> ids) {
        AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup viewGroup) {
        adContainerView = viewGroup;
    }

    @Override
    void loadBanner(Activity activity) {
        // Create a new ad view.
        adView = new AdView(activity);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdSize(getAdSize(activity));
        onAdListener(adView);
//        // Replace ad container with new ad view.
//        adContainerView.removeAllViews();
//        adContainerView.addView(adView);
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        Log.e("bbbbbbbbbb", "loadBanner: adRequest " + AD_UNIT_ID);

    }

    @Override
    public void initPosition(COLLAPSE_BANNER_POSITION position) {

    }

    @Override
    public void initLifecycle(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
        mLifecycle.addObserver(this);
    }

    @Override
    public void setReload(boolean reload) {
        mReload = reload;
    }


    @Override
    public void loadAdmob(Activity activity) {
        mActivity = activity;
        AdmobManager.getInstance().initializeMobileAdsSdk(activity);
        initView(activity);
        if (adContainerView != null) {
            adContainerView.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            if (!initialLayoutComplete.getAndSet(true)) {
                                loadBanner(activity);
                                Log.e("ttttttttttttttt", "loadBanner: loabBanner");
                            }
                        }
                    });
        }

    }

//    @Override
//    public void reloadAdmob(Activity activity) {
//        try {
//            if (AD_UNIT_ID != null && adContainerView != null) {
//                initView(activity);
//                loadBanner(activity);
//                Log.e("zzzzzzzzzzzzz", "reloadAdmob:eee ");
//            }
//        } catch (Exception exception) {
//            Log.e("zzzzzzzzzzzzz", "exception: " + exception.getMessage());
//        }
//
//    }

    @Override
    public void reloadAdmob() {
        try {
            if (AD_UNIT_ID != null && adContainerView != null && mActivity != null && !isLoading) {
                initView(mActivity);
                loadBanner(mActivity);
                Log.e("zzzzzzzzzzzzz", "reloadAdmob:eee ");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzzz", "exception: " + exception.getMessage());
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
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        Log.e("LifeCycleEvent", "onStateChanged: ");
        switch (event) {
            case ON_CREATE: {
                Log.e("LifeCycleEvent", "banner on create");
                break;
            }
            case ON_START: {
                Log.e("LifeCycleEvent", "banner on start");
                if (mReload) {
                    reloadAdmob();
                }
                break;
            }
            case ON_RESUME: {
                if (adView != null) {
                    Log.e("LifeCycleEvent", "banner on Resume  " + adView.hashCode());
                    adView.resume();
                }
                break;
            }
            case ON_PAUSE: {
                Log.e("LifeCycleEvent", "banner on pause");
                if (adView != null) {
                    adView.pause();
                }
                break;
            }
            case ON_STOP: {
                Log.e("LifeCycleEvent", "banner on stop");
                break;
            }
            case ON_DESTROY: {
                Log.e("LifeCycleEvent", "banner on destroy");
                if (adView != null) {
                    adView.destroy();
                }
                if (mLifecycle != null) {
                    mLifecycle.removeObserver(this);
                }
                break;
            }
            case ON_ANY: {
                Log.e("LifeCycleEvent", "banner on any");
                break;
            }
        }
    }
}
