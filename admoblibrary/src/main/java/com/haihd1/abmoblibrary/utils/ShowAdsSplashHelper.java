package com.haihd1.abmoblibrary.utils;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.adparam.AdUnit;

import java.util.Random;

public class ShowAdsSplashHelper {
    public static int randomAdsAoA(){
        Random rand = new Random();
        return rand.nextInt(101) + 1;
    }

    public static TYPE adsTypeSplash(){
        String rateAoa = "30_70";
        int openSplash = Integer.parseInt(rateAoa.split("_")[0].trim());
        int ran = randomAdsAoA();
        LogUtils.logString(ran + "");
        if (  ran <= openSplash){
            return TYPE.APP_OPEN;
        }
        return TYPE.INTER;
    }

}
