package com.haihd1.abmoblibrary.utils;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.adparam.AdUnit;
import com.haihd1.abmoblibrary.remote_config.AppConfigs;

import java.util.Random;

public class ShowAdsSplashHelper {
    public static int randomAdsAoA(){
        Random rand = new Random();
        return rand.nextInt(101) + 1;
    }

    public static TYPE adsTypeSplash(){
        String rateAoa = AdUnit.getRateAoaInterSplash();
        int openSplash = Integer.parseInt(rateAoa.split("_")[0].trim());
        int ran = randomAdsAoA();
        LogUtils.logString(ran + " " + rateAoa);
        if (  ran <= openSplash){
            return TYPE.APP_OPEN;
        }
        return TYPE.INTER;
    }

}
