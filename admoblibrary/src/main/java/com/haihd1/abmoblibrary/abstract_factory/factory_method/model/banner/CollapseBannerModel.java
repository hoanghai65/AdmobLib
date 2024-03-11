package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;

import java.util.List;

public class CollapseBannerModel extends BannerAbstract {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;
    private COLLAPSE_BANNER_POSITION position = COLLAPSE_BANNER_POSITION.bottom;

    @Override
    public void initId(String id) {
        this.AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {
        AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup viewGroup) {
        adContainerView = viewGroup;
    }

    @Override
    public void initPosition(COLLAPSE_BANNER_POSITION type) {
        position = type;
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
        Bundle extras = new Bundle();
        extras.putString("collapsible", position.name());
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        adView.loadAd(adRequest);
        Log.e("zzzzzzzzzzzz", "loadBanner: adRequest");
    }

    @Override
    public void loadAdmob(Activity activity) {
        AdmobManager.getInstance().initializeMobileAdsSdk(activity);
        mActivity = activity;
        initView(activity);
        if (adContainerView != null) {
            adContainerView.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            if (!initialLayoutComplete.getAndSet(true)) {
                                loadBanner(activity);
                                Log.e("zzzzzzzzzzzz", "loadBanner: loabBanner");
                            }
                        }
                    });
        }
    }

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
        switch (event) {
            case ON_CREATE: {
                Log.e("LifeCycleEvent", "collapse on create");
                break;
            }
            case ON_START: {
                Log.e("LifeCycleEvent", "collapse on start");
                if (mReload && adView != null) {
                    adView.destroy();
                    reloadAdmob();
                }
                break;
            }
            case ON_RESUME: {
                Log.e("LifeCycleEvent", "collapse on Resume");
                if (adView != null) {
                    adView.resume();
                }
                break;
            }
            case ON_PAUSE: {
                Log.e("LifeCycleEvent", "collapse on pause");
                if (adView != null) {
                    adView.pause();
                }
                break;
            }
            case ON_STOP: {
                Log.e("LifeCycleEvent", "collapse on stop");
                break;
            }
            case ON_DESTROY: {
                Log.e("LifeCycleEvent", "collapse on destroy");
                if (adView != null) {
                    adView.destroy();
                }
                if (mLifecycle != null) {
                    mLifecycle.removeObserver(this);
                }
                break;
            }
            case ON_ANY: {
                Log.e("LifeCycleEvent", "collapse on any");
                break;
            }
        }
    }
}
