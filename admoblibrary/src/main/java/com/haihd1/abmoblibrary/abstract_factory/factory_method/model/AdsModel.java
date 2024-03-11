package com.haihd1.abmoblibrary.abstract_factory.factory_method.model;

import android.app.Activity;
import android.content.Context;


import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;

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
