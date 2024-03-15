package com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.haihd1.abmoblibrary.R;
import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel;
import com.haihd1.abmoblibrary.utils.CheckNetWork;

import java.util.Objects;

public abstract class NativeAbstract extends AdsModel implements AdmobHelper, LifecycleEventObserver {
    protected NativeAdView nativeAdview;
    protected NativeAd mNativeAd;
    protected ViewGroup adContainerView;
    protected Lifecycle mLifecycle;
    protected int mResource;
    protected int mShimmer;
    protected Context mContext;
    protected AdLoader adLoader;

    public abstract void initLifecycle(Lifecycle lifecycle);

    abstract void loadNative(Context context);

    protected abstract void loadAdmob(Context context);

    public abstract void initResource(int resource);

    public abstract void initShimmer(int shimmer);

    public abstract void setReload(boolean reload);

    protected void initView(Context context) {
        try {
            if (adContainerView != null) {
                if (CheckNetWork.isConnectNetWork(context)) {
                    View view = LayoutInflater.from(context).inflate(mShimmer, null);
                    adContainerView.removeAllViews();
                    adContainerView.addView(view);
                    Log.e("zzzzzzzzz", "initView: ");
                } else {
                    adContainerView.removeAllViews();
                }
            }
        } catch (Exception exception) {
            Log.e("zzzzzzzzzzzzz", "exception:11111 " + exception.getMessage());
        }
    }

    protected void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((MediaView) nativeAdview.findViewById(R.id.ad_media));
        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));


        // The headline and mediaContent are guaranteed to be in every NativeAd.
        if (adView.getHeadlineView() != null) {
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        }
        if (adView.getMediaView() != null) {
            adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        }

        if (nativeAd.getBody() == null) {
            if (adView.getBodyView() != null) {
                adView.getBodyView().setVisibility(View.INVISIBLE);
            }
        } else {
            if (adView.getBodyView() != null) {
                adView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
            }
        }

        if (nativeAd.getCallToAction() == null) {
            if (adView.getCallToActionView() != null) {
                adView.getCallToActionView().setVisibility(View.INVISIBLE);
            }
        } else {
            if (adView.getCallToActionView() != null) {
                adView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
            }
        }

        if (nativeAd.getIcon() == null) {
            if (adView.getIconView() != null) {
                adView.getIconView().setVisibility(View.GONE);
            }
        } else {
            if (adView.getIconView() != null) {
                ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);
            }
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided,
        // even if the ad doesn't have a video asset.
        VideoController videoController = Objects.requireNonNull(nativeAd.getMediaContent()).getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (videoController.hasVideoContent()) {

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController.
            // The VideoController will call methods on this object when events occur in the
            // video lifecycle.
            videoController.setVideoLifecycleCallbacks(
                    new VideoController.VideoLifecycleCallbacks() {
                        @Override
                        public void onVideoEnd() {
                            // Publishers should allow native ads to complete video playback before
                            // refreshing or replacing them with another ad in the same UI location.
                            super.onVideoEnd();
                        }
                    });
        }
    }

    protected void replaceView() {
        if (adContainerView != null && nativeAdview != null) {
            // Replace ad container with new ad view.
            adContainerView.removeAllViews();
            adContainerView.addView(nativeAdview);
        }
    }

    protected void removeView() {
        if (adContainerView != null) {
            adContainerView.removeAllViews();
        }
    }
}
