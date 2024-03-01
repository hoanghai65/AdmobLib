package com.haihd1.abmoblibrary.utils;

import android.util.Log;

import com.haihd1.abmoblibrary.adparam.AdUnit;

public class TimeShowInter {
    public static long timeBetweenInterval = 0;
    public static long startFromInterval = 0;
    public static boolean isShowInter = false;
    public static boolean isShowedInterSplash = false;

    public static boolean showInterThenTimeStart(){
        if (isShowInter){
            isShowedInterSplash = true;
            return true;
        }
        long currentTime = System.currentTimeMillis();
        Log.e("TIME_INTER_VAL", "showInterThenTimeStart:" +  AdUnit.getInterstitialFromStart() + "  " + currentTime + "  " +  startFromInterval + "   " + (currentTime - startFromInterval) );
        isShowInter = currentTime - startFromInterval  > AdUnit.getInterstitialFromStart() * 1000L;
        return isShowInter;
    }

    public static boolean showInterThenTimeBetween(){
        long currentTime = System.currentTimeMillis();
        Log.e("TIME_INTER_VAL", "showInterThenTimeBetween:"+  AdUnit.getIntervalBetweenInterstitial() + "  " + currentTime + "  " +  timeBetweenInterval + "   " + (currentTime - timeBetweenInterval) );
        return currentTime - timeBetweenInterval  > AdUnit.getIntervalBetweenInterstitial() * 1000L;
    }

    public static void upDateTimeForStartFromInterval(){
        if (!isShowInter){
            startFromInterval = System.currentTimeMillis();
        }
    }
    public static void upDateTimeForBetweenInterval(){
        timeBetweenInterval = System.currentTimeMillis();
    }

}

