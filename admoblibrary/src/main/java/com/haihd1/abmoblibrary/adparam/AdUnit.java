package com.haihd1.abmoblibrary.adparam;

import android.util.Log;

import com.haihd1.abmoblibrary.remote_config.AppConfigs;
import com.haihd1.abmoblibrary.remote_config.RemoteKey;

public class AdUnit {
    public static final boolean TEST = true;

    public static void setTimeInterBetween(int timeInterBetween) {
        AdUnit.timeInterBetween = timeInterBetween;
    }

    public static void setTimeFromStart(int timeFromStart) {
        AdUnit.timeFromStart = timeFromStart;
    }

    public static int timeInterBetween = 20;
    public static int timeFromStart = 15;

    public static boolean isTEST() {
        return TEST;
    }

    public static String getAdmobInterId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobInterId();
        return id == null ? "" : id;
    }

    public static String getAdmobNativeId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobNativeId();
        return id == null ? "" : id;
    }

    public static String getAdmobBannerId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobBannerId();
        return id == null ? "" : id;
    }

    public static String getAdmobOpenId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobOpenId();
        return id == null ? "" : id;
    }

    public static int getIntervalBetweenInterstitial() {
        int id = AppConfigs.getInt(RemoteKey.INTERVAL_BETWEEN_INTERSTITIAL);
        return id == 0 ? timeInterBetween : id;
    }

    public static int getInterstitialFromStart() {
        int id =  AppConfigs.getInt(RemoteKey.INTERSTITIAL_FROM_START);
        return id == 0 ? timeFromStart : id;
    }

    public static String getRateAoaInterSplash() {
        String id =  AppConfigs.getString(RemoteKey.RATE_AOA_INTER_SPLASH);
        return id.isEmpty() ? "30_70" : id;
    }

    public static String getAdmobRewardedId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobRewardedId();
        return id == null ? "" : id;
    }
    public static String getAdmobRewardInterstitialId() {
        String id = AdUnitFactory.getInstance(TEST).getAdmobRewardInterstitial();
        return id == null ? "" : id;
    }

    public static String getAdxInterId() {
        String id = AdUnitFactory.getInstance(TEST).getAdxInterId();
        return id == null ? "" : id;
    }

    public static String getAdxBannerId() {
        String id = AdUnitFactory.getInstance(TEST).getAdxBannerId();
        return id == null ? "" : id;
    }

    public static String getAdxNativeId() {
        String id = AdUnitFactory.getInstance(TEST).getAdxNativeId();
        return id == null ? "" : id;
    }

    public static String getAdxOpenId() {
        String id = AdUnitFactory.getInstance(TEST).getAdxOpenId();
        return id == null ? "" : id;
    }

    public static String getFacebookInterId() {
        String id = AdUnitFactory.getInstance(TEST).getFacebookInterId();
        return id == null ? "" : id;
    }

    public static String getFacebookBannerId() {
        String id = AdUnitFactory.getInstance(TEST).getFacebookBannerId();
        return id == null ? "" : id;
    }

    public static String getFacebookNativeId() {
        String id = AdUnitFactory.getInstance(TEST).getFacebookNativeId();
        return id == null ? "" : id;
    }

    public static int getCountShowAds() {
        return AdUnitFactory.getInstance(TEST).getCountShowAds();
    }

    public static int getLimitShowAds() {
        return AdUnitFactory.getInstance(TEST).getLimitShowAds();
    }

    public static long getTimeLastShowAds() {
        return AdUnitFactory.getInstance(TEST).getTimeLastShowAds();
    }


    public static String getMasterAdsNetwork() {
        String id = AdUnitFactory.getInstance(TEST).getMasterAdsNetwork();
        return id == null ? "" : id;
    }


    public static String getApplovinBannerId() {
        String id = AdUnitFactory.getInstance(TEST).getApplovinBannerId();
        return id == null ? "" : id;
    }

    public static String getApplovinInterId() {
        String id = AdUnitFactory.getInstance(TEST).getApplovinInterId();
        return id == null ? "" : id;
    }

    public static String getApplovinRewardId() {
        String id = AdUnitFactory.getInstance(TEST).getApplovinRewardId();
        return id == null ? "" : id;
    }
}
