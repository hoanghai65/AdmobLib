package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.google.android.gms.ads.appopen.AppOpenAd;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public abstract class AppOpenAbstract extends AdsModel implements AdmobHelper {
    protected AppOpenAd mAppOpenAd = null;
    protected boolean isLoadingAd = false;
    protected Dialog dialog;

    protected boolean isShowingAd = false;
    protected boolean enableAdsOpenResume = true;
    protected abstract void loadAds(Context context);
    protected abstract void loadAppOpenSplash(Context context,String id);
    protected abstract void setSubject(Subject subject);

    public abstract void setActivity(Activity activity);
    protected List<Activity> listActivity = new ArrayList<>();
    public abstract void showAdIfAvailable(Activity activity);
    public abstract boolean isAdAvailable();
    public abstract void showAdIfAvailable(Activity activity, AdsApplication.OnShowAdCompleteListener onShowAdCompleteListener);
    public abstract void disableAppOpenResume();
    public abstract void disableAppOpenResumeActivity(Activity activity);
    public abstract void enableAppOpenResume();
    public abstract void enableAppOpenResumeActivity(Activity activity);



}
