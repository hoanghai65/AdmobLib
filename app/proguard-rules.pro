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

#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.ads_native.NativeAbstract {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.banner.BannerAbstract {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.banner.COLLAPSE_BANNER_POSITION {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.InterstitialAbstract {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.interstitial.LoadingAdsDialog {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.open_resume.AppOpenAbstract {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.open_resume.AdsApplication {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.AdmobCreator {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.AdmobFactory {*;}
#-keep class com.haihd1.admoblib.abstract_factory.AdmobHelper {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.TYPE {*;}
#-keep class com.haihd1.admoblib.abstract_factory.factory_method.model.AdsModel {*;}
#-keep class com.haihd1.admoblib.admob_builder.AdmobCallBack {*;}
#-keep class com.haihd1.admoblib.admob_builder.AdmobManager {*;}
#-keep class com.haihd1.admoblib.admob_builder.GoogleMobileAdsConsentManager {*;}

