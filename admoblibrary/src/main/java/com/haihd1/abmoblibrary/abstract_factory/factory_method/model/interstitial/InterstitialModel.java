package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;
import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;
import com.haihd1.abmoblibrary.admob_builder.AdmobManager;
import com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager;
import com.haihd1.abmoblibrary.observer.Subject;
import com.haihd1.abmoblibrary.utils.TimeShowInter;

import java.util.List;

public class InterstitialModel extends InterstitialAbstract {
    private String AD_UNIT_ID;
    private List<String> AD_LIST_ID;
    private Subject mSubject;
    private Activity mActivity;


    @Override
    public void initId(String id) {
        AD_UNIT_ID = id;
    }

    @Override
    public void initListId(List<String> ids) {
        AD_LIST_ID = ids;
    }

    @Override
    public void initLayout(ViewGroup frameLayout) {

    }


    @Override
    public void loadAdmob(Activity activity) {
        if (GoogleMobileAdsConsentManager.getInstance(activity).getConsentResult(activity)) {
            isNotInterSplash = true;
            AdmobManager.getInstance().initializeMobileAdsSdk(activity);
            Log.e("iiiiiiiiiiiiiiii", " loadAdmob init" + "   " + adIsLoading);
            if (adIsLoading) {
                Log.e("iiiiiiiiiiiiiiii", "sddddd");
                return;
            }
            adIsLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    adIsLoading = false;
                    mInterstitialAd = null;
                    Log.e("iiiiiiiiiiiiiiii", " onAdFailedToLoad");
                    if (admobCallBack != null) {
                        admobCallBack.onAdFailedToLoad();
                    }
                    if (actionCallBack != null) {
                        actionCallBack.onNextAction();
                    }

                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    adIsLoading = false;
                    mInterstitialAd = interstitialAd;
                    Log.e("iiiiiiiiiiiiiiii", " onAdLoaded" + "   " + mInterstitialAd);
                    mInterstitialAd.setFullScreenContentCallback(getfullScreenContentCallback(activity));
                    if (admobCallBack != null) {
                        admobCallBack.onAdLoaded();
                    }
                }
            });
        }
    }


    @Override
    public void loadInterSplash(Activity activity) {
        if (GoogleMobileAdsConsentManager.getInstance(activity).getConsentResult(activity)) {
            isNotInterSplash = false;
            AdmobManager.getInstance().initializeMobileAdsSdk(activity);
            Log.e("iiiiiiiiiiiiiiii", " loadAdmob init" + "   " + adIsLoading);
            if (adIsLoading) {
                Log.e("iiiiiiiiiiiiiiii", "sddddd");
                return;
            }
            adIsLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    adIsLoading = false;
                    mInterstitialAd = null;
                    Log.e("iiiiiiiiiiiiiiii", " onAdFailedToLoad");
                    if (admobCallBack != null) {
                        admobCallBack.onAdFailedToLoad();
                    }
                    if (actionCallBack != null) {
                        actionCallBack.onNextAction();
                    }

                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    adIsLoading = false;
                    mInterstitialAd = interstitialAd;
                    if (mSubject != null) {
                        mSubject.setState(activity, actionCallBack, TYPE.INTER);
                    }
                    Log.e("iiiiiiiiiiiiiiii", " onAdLoaded" + "   " + mInterstitialAd);
                    mInterstitialAd.setFullScreenContentCallback(getfullScreenContentCallback(activity));
                    if (admobCallBack != null) {
                        admobCallBack.onAdLoaded();
                    }
                }
            });
        }
    }

    @Override
    public void reloadAdmob() {

    }

    @Override
    public void setAdmobCallBack(AdmobCallBack admobCallBack) {
        this.admobCallBack = admobCallBack;
    }


    private FullScreenContentCallback getfullScreenContentCallback(Activity activity) {
        return new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                admobCallBack.onAdClicked();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
//                mInterstitialAd = null;
                if (actionCallBack != null) {
                    actionCallBack.onNextAction();
                }
                if (adsDialog != null) {
                    adsDialog.dismiss();
                    adsDialog = null;
                }
                if (isNotInterSplash) {
                    TimeShowInter.upDateTimeForBetweenInterval();
                }

                if (admobCallBack != null) {
                    admobCallBack.onAdClosed();
                }
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                mInterstitialAd = null;
                if (adsDialog != null) {
                    adsDialog.dismiss();
                    adsDialog = null;
                }
                if (actionCallBack != null) {
                    actionCallBack.onNextAction();
                }
                Log.e("iiiiiiiiiiiiiiii", "onAdFailedToShowFullScreenContent: ");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                if (admobCallBack != null) {
                    admobCallBack.onAdImpression();
                }
            }

            @Override
            public void onAdShowedFullScreenContent() {
                if (isNotInterSplash) {
                    loadAdmob(activity);
                }
                Log.e("iiiiiiiiiiiiiiii", "onAdShowedFullScreenContent");
            }
        };
    }

    @Override
    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }


    @Override
    public void showInter(Activity activity) {
        if (mInterstitialAd == null) {
            Log.e("iiiiiiiiiiiiiiii", " none showInter");
            loadAdmob(activity);
            if (actionCallBack != null) {
                actionCallBack.onNextAction();
            }
        } else {
            Log.e("TIME_INTER_VAL", "showInter: " + TimeShowInter.isShowedInterSplash + "  " + TimeShowInter.showInterThenTimeStart() + "  "  + TimeShowInter.showInterThenTimeBetween());
            if ((!TimeShowInter.isShowedInterSplash && TimeShowInter.showInterThenTimeStart()) || (TimeShowInter.isShowedInterSplash && TimeShowInter.showInterThenTimeBetween())) {
                Log.e("iiiiiiiiiiiiiiii", " showInter" + "   " + adIsLoading);
                handlerShowInter(activity);
            } else {
                Log.e("iiiiiiiiiiiiiiii", " none showInter");
                if (actionCallBack != null) {
                    actionCallBack.onNextAction();
                }
            }
        }
    }

    @Override
    public void showInterSplash(Activity activity) {
        if (mInterstitialAd != null) {
            handlerShowInter(activity);
        } else {
            Log.e("iiiiiiiiiiiiiiii", " none showInter");
            loadInterSplash(activity);
            if (actionCallBack != null) {
                actionCallBack.onNextAction();
            }
        }
    }

    private void handlerShowInter(Activity activity) {
        if (adsDialog == null) {
            adsDialog = new LoadingAdsDialog(activity);
        }
        if (!adIsLoading) {
            adsDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mInterstitialAd.show(activity);

                }
            }, 1000L);
        }
    }

    @Override
    protected void setSubject(Subject subject) {
        mSubject = subject;
    }

    @Override
    public void setActivity(Activity activity) {
        mActivity = activity;
    }

}
