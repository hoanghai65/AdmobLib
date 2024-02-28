package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;

public interface RewardListener extends ActionCallBack {
    void onEarnedReward(RewardItem rewardItem);
}
