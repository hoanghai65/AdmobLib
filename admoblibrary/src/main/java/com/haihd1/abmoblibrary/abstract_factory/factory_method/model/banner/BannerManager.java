package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;

import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobCreator;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobFactory;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.admob_builder.AdmobCallBack;


public class BannerManager {
    private static BannerManager instance;

    public static BannerManager getInstance() {
        synchronized (BannerManager.class) {
            if (instance == null) {
                instance = new BannerManager();
            }
        }
        return instance;
    }

    ///-----------------------------------------
    /// banner ads

    private BannerAbstract initBanner(String id, ViewGroup frameLayout) {
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.BANNER);
        if (admob != null) {
            BannerAbstract bannerAds = (BannerAbstract) admob;
            bannerAds.initId(id);
            bannerAds.initLayout(frameLayout);
            Log.e("zzzzzzzzzzzz", "loadBanner: init");
            return bannerAds;
        }
        return null;
    }

    private BannerAbstract initCollapseBanner(String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position) {
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.COLLAPSE_BANNER);
        if (admob != null) {
            BannerAbstract bannerAds = (BannerAbstract) admob;
            bannerAds.initId(id);
            bannerAds.initLayout(frameLayout);
            bannerAds.initPosition(position);
            Log.e("zzzzzzzzzzzz", "initCollapseBanner: init");
            return bannerAds;
        }
        return null;
    }

    public void loadBanner(Activity activity, String id, ViewGroup frameLayout) {
        BannerAbstract bannerAds = (BannerAbstract) initBanner(id, frameLayout);
        if (bannerAds != null) {
            bannerAds.loadAdmob(activity);
        }
    }

    public void loadBanner(Activity activity, String id, ViewGroup frameLayout, AdmobCallBack admobCallBack) {
        BannerAbstract bannerAds = (BannerAbstract) initBanner(id, frameLayout);
        if (bannerAds != null) {
            bannerAds.setAdmobCallBack(admobCallBack);
            bannerAds.loadAdmob(activity);
        }
    }


    public void loadBanner(Activity activity, Lifecycle lifecycle, String id, ViewGroup frameLayout, Boolean reload) {
        BannerAbstract bannerAds = (BannerAbstract) initBanner(id, frameLayout);
        if (bannerAds != null) {
            bannerAds.initLifecycle(lifecycle);
            bannerAds.setReload(reload);
            bannerAds.loadAdmob(activity);
        }
    }

    public void loadBanner(Activity activity, Lifecycle lifecycle, String id, ViewGroup frameLayout, Boolean reload, AdmobCallBack admobCallBack) {
        BannerAbstract bannerAds = (BannerAbstract) initBanner(id, frameLayout);
        if (bannerAds != null) {
            bannerAds.initLifecycle(lifecycle);
            bannerAds.setReload(reload);
            bannerAds.setAdmobCallBack(admobCallBack);
            bannerAds.loadAdmob(activity);
        }
    }

    ///-----------------------------------------
    // CollapseBanner ads
    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position) {
        try {
            BannerAbstract bannerAds = (BannerAbstract) initCollapseBanner(id, frameLayout, position);
            if (bannerAds != null) {
                bannerAds.loadAdmob(activity);
            }

        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }

    }

    public void loadCollapseBanner(Activity activity, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, AdmobCallBack admobCallBack) {
        try {
            BannerAbstract bannerAds = (BannerAbstract) initCollapseBanner(id, frameLayout, position);
            if (bannerAds != null) {
                bannerAds.setAdmobCallBack(admobCallBack);
                bannerAds.loadAdmob(activity);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }

    }


    public void loadCollapseBanner(Activity activity, Lifecycle lifecycle, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, boolean reload) {
        try {
            BannerAbstract bannerAds = (BannerAbstract) initCollapseBanner(id, frameLayout, position);
            if (bannerAds != null) {
                bannerAds.initLifecycle(lifecycle);
                bannerAds.setReload(reload);
                bannerAds.loadAdmob(activity);
            }

        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }

    }

    public void loadCollapseBanner(Activity activity, Lifecycle lifecycle, String id, ViewGroup frameLayout, COLLAPSE_BANNER_POSITION position, boolean reload, AdmobCallBack admobCallBack) {
        try {
            BannerAbstract bannerAds = (BannerAbstract) initCollapseBanner(id, frameLayout, position);
            if (bannerAds != null) {
                bannerAds.initLifecycle(lifecycle);
                bannerAds.setReload(reload);
                bannerAds.setAdmobCallBack(admobCallBack);
                bannerAds.loadAdmob(activity);
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());
        }

    }

}
