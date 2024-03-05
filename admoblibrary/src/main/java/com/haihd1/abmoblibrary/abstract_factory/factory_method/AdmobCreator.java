package com.haihd1.abmoblibrary.abstract_factory.factory_method;


import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native.NativeModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardInterstitialModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardedModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.BannerModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.CollapseBannerModel;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialModel;

public class AdmobCreator extends AdmobFactory {

    @Override
    public AdmobHelper providerAdmob(TYPE type) {
        switch (type) {
            case BANNER:
                return new BannerModel();
            case COLLAPSE_BANNER:
                return new CollapseBannerModel();
            case NATIVE:
                return new NativeModel();
            case INTER:
                return new InterstitialModel();
            case  REWARDED:
                return new RewardedModel();
            case REWARD_INTERSTITIAL:
                return new RewardInterstitialModel();
        }
        return null;
    }
}
