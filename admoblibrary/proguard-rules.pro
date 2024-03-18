# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.appsflyer.** { *; }
-keep public class com.android.installreferrer.** { *; }
-keep public class com.miui.referrer.** {*;}

-keep class com.adjust.sdk.** { *; }
-keep class com.google.android.gms.common.ConnectionResult {
    int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
    com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
    java.lang.String getId();
    boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.** { *; }

-keep public class com.adjust.sdk.** { *; }

-keep public class com.android.installreferrer.** { *; }


-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native.NativeAbstract {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_native.NativeManager {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.BannerAbstract {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.BannerManager {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.banner.COLLAPSE_BANNER_POSITION {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialAbstract {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.InterstitialManager {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardAbstract {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardListener {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.ads_reward.RewardManager {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.interstitial.LoadingAdsDialog {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AppOpenAbstract {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AppOpenAdManager {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AdsApplication {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobCreator {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.AdmobHelper {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.AdmobFactory {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE {*;}
-keep class com.haihd1.abmoblibrary.abstract_factory.factory_method.model.AdsModel {*;}
-keep class com.haihd1.abmoblibrary.utils.callback.AdmobCallBack {*;}
-keep class com.haihd1.abmoblibrary.admob_builder.AdmobManager {*;}
-keep class com.haihd1.abmoblibrary.admob_builder.GoogleMobileAdsConsentManager {*;}
-keep class com.github.ybq.**{*;}
-keep class com.facebook.shimmer.**{*;}
-keep class com.google.android.material.**{*;}
-keep class com.haihd1.abmoblibrary.remote_config.**{*;}
-keep class com.haihd1.abmoblibrary.utils.**{*;}
-keep class com.haihd1.abmoblibrary.event.**{*;}
-keep class com.haihd1.abmoblibrary.observer.**{*;}
-keep class com.haihd1.abmoblibrary.adparam.**{*;}
-keep class com.google.android.gms.**{*;}
-keep class com.google.android.ump.**{*;}


