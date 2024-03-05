package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward;

import android.app.Activity;
import android.util.Log;

import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobCreator;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobFactory;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialManager;
import com.haihd1.abmoblibrary.adparam.AdUnit;
import com.haihd1.abmoblibrary.observer.Subject;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;

public class RewardManager {
    private RewardAbstract mRewardAbstract;
    private static InterstitialManager instance;

    public static InterstitialManager getInstance() {
        synchronized (InterstitialManager.class) {
            if (instance == null) {
                instance = new InterstitialManager();

            }
        }
        return instance;
    }

    public void loadRewardInter(Activity activity) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARD_INTERSTITIAL);
            if (admob != null) {
                mRewardAbstract = (RewardAbstract) admob;
                mRewardAbstract.initId(AdUnit.getAdmobRewardInterstitialId());
                mRewardAbstract.loadAdmob(activity);
                Log.e("zzzzzzzzzzzz", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());

        }
    }

    public void loadRewardInter(Activity activity, String id) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARD_INTERSTITIAL);
            if (admob != null) {
                mRewardAbstract = (RewardAbstract) admob;
                mRewardAbstract.initId(id);
                mRewardAbstract.loadAdmob(activity);
                Log.e("rrrrrrrrrrrrr", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("rrrrrrrrrrrrr", "exception: " + exception.getMessage());

        }
    }

    public void loadRewarded(Activity activity) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARDED);
            if (admob != null) {
                mRewardAbstract = (RewardAbstract) admob;
                mRewardAbstract.initId(AdUnit.getAdmobRewardedId());
                mRewardAbstract.loadAdmob(activity);
                Log.e("zzzzzzzzzzzz", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzz", "exception: " + exception.getMessage());

        }
    }

    public void loadRewarded(Activity activity, String id) {
        try {
            AdmobFactory admobFactory = new AdmobCreator();
            AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARDED);
            if (admob != null) {
                mRewardAbstract = (RewardAbstract) admob;
                mRewardAbstract.initId(id);
                mRewardAbstract.loadAdmob(activity);
                Log.e("rrrrrrrrrrrrr", "loadInter: init");
            }
        } catch (Exception exception) {
            Log.e("rrrrrrrrrrrrr", "exception: " + exception.getMessage());

        }
    }

    public void initRewardInter(){
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARD_INTERSTITIAL);
        if (admob != null) {
            mRewardAbstract = (RewardAbstract) admob;
        }
    }

    public void initRewarded(){
        AdmobFactory admobFactory = new AdmobCreator();
        AdmobHelper admob = admobFactory.providerAdmob(TYPE.REWARDED);
        if (admob != null) {
            mRewardAbstract = (RewardAbstract) admob;
        }
    }
    public void setSubject(Subject subject){
        mRewardAbstract.setSubject(subject);
    }
    public void setActivity(Activity activity){
        mRewardAbstract.setActivity(activity);
    }
    public void setActionCallBack(ActionCallBack actionCallBack){
        mRewardAbstract.setActionCallBack(actionCallBack);
    }

    public void showAdsRewardInter(Activity activity) {
        if (mRewardAbstract != null) {
            mRewardAbstract.showAdsReward(activity);
        }
    }
    public void showAdsRewardInter(Activity activity, AdmobCallBack admobCallBack) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setAdmobCallBack(admobCallBack);
            mRewardAbstract.showAdsReward(activity);
        }
    }
    public void showAdsRewardInter(Activity activity, RewardListener rewardListener) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setActionCallBack(rewardListener);
            mRewardAbstract.showAdsReward(activity);
        }
    }

    public void showAdsRewardInter(Activity activity, RewardListener rewardListener,AdmobCallBack admobCallBack) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setActionCallBack(rewardListener);
            mRewardAbstract.setAdmobCallBack(admobCallBack);
            mRewardAbstract.showAdsReward(activity);
        }
    }

    public void showAdsRewarded(Activity activity) {
        if (mRewardAbstract != null) {
            mRewardAbstract.showAdsReward(activity);
        }
    }
    public void showAdsRewarded(Activity activity, AdmobCallBack admobCallBack) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setAdmobCallBack(admobCallBack);
            mRewardAbstract.showAdsReward(activity);
        }
    }
    public void showAdsRewarded(Activity activity, RewardListener rewardListener) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setActionCallBack(rewardListener);
            mRewardAbstract.showAdsReward(activity);
        }
    }

    public void showAdsRewarded(Activity activity, RewardListener rewardListener,AdmobCallBack admobCallBack) {
        if (mRewardAbstract != null) {
            mRewardAbstract.setActionCallBack(rewardListener);
            mRewardAbstract.setAdmobCallBack(admobCallBack);
            mRewardAbstract.showAdsReward(activity);
        }
    }
}
