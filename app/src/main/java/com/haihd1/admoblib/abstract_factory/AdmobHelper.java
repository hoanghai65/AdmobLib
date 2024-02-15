package com.haihd1.admoblib.abstract_factory;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.ActionCallBack;
import com.haihd1.admoblib.admob_builder.AdmobCallBack;

import java.util.List;

public interface AdmobHelper {
    void initId(String id);
    void initListId(List<String> ids);
    void initLayout(ViewGroup viewGroup);
    void loadAdmob(Activity activity);
    void reloadAdmob();
    void setAdmobCallBack(AdmobCallBack admobCallBack);
    void setActionCallBack(ActionCallBack admobCallBack);
}
