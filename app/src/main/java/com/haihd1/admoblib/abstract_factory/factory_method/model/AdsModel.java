package com.haihd1.admoblib.abstract_factory.factory_method.model;

import android.app.Activity;

import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.admoblib.admob_builder.AdmobCallBack;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AdsModel {
    protected AdmobCallBack admobCallBack;
    protected ActionCallBack actionCallBack;
    protected Activity mActivity;
    protected boolean isLoading = true;
    protected boolean mReload = false;
    protected AtomicBoolean initialLayoutComplete = new AtomicBoolean(false);
    protected final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
}
