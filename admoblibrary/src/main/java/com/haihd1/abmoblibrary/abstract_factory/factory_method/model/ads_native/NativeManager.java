package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;

import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobCreator;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobFactory;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;

public class NativeManager {
    private static NativeManager instance;
    public static NativeManager getInstance(){
        synchronized (NativeManager.class){
            if (instance == null){
                instance = new NativeManager();
            }
        }
        return instance;
    }

    private NativeAbstract initNative( String id, ViewGroup viewGroup, int resource, int layoutShimmer){
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.NATIVE);
        if (admob != null) {
            NativeAbstract nativeAds = (NativeAbstract) admob;
            nativeAds.initId(id);
            nativeAds.initLayout(viewGroup);
            nativeAds.initResource(resource);
            nativeAds.initShimmer(layoutShimmer);
            return nativeAds;
        }
        return null;
    }

    ///-----------------------------------------
    // Native ads
    public void loadNative(Context context, String id, ViewGroup viewGroup, int resource, int layoutShimmer) {
        try {
            NativeAbstract nativeAds = (NativeAbstract) initNative(id,viewGroup,resource,layoutShimmer);
            if (nativeAds != null){
                nativeAds.loadAdmob(context);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }
    }

    public void loadNative(Context context, String id, ViewGroup viewGroup, int resource, int layoutShimmer, AdmobCallBack admobCallBack) {
        try {
            NativeAbstract nativeAds = (NativeAbstract) initNative(id,viewGroup,resource,layoutShimmer);
            if (nativeAds != null){
                nativeAds.setAdmobCallBack(admobCallBack);
                nativeAds.loadAdmob(context);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }
    }
    public void loadNative(Context context, String id, ViewGroup viewGroup, int resource, int layoutShimmer, Lifecycle lifecycle, boolean reload) {
        try {
            NativeAbstract nativeAds = (NativeAbstract) initNative(id,viewGroup,resource,layoutShimmer);
            if (nativeAds != null){
                nativeAds.initLifecycle(lifecycle);
                nativeAds.setReload(reload);
                nativeAds.loadAdmob(context);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }
    }
    public void loadNative(Context context, String id, ViewGroup viewGroup, int resource, int layoutShimmer,Lifecycle lifecycle,boolean reload, AdmobCallBack admobCallBack) {
        try {
            NativeAbstract nativeAds = (NativeAbstract) initNative(id,viewGroup,resource,layoutShimmer);
            if (nativeAds != null){
                nativeAds.initLifecycle(lifecycle);
                nativeAds.setReload(reload);
                nativeAds.setAdmobCallBack(admobCallBack);
                nativeAds.loadAdmob(context);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }
    }
}
