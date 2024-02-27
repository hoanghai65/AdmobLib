package com.haihd1.abmoblibrary.remote_config;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.haihd1.abmoblibrary.R;
import com.haihd1.abmoblibrary.adparam.AdUnit;
import com.haihd1.abmoblibrary.utils.LogUtils;

public class AppConfigs {

    private Context mContext;
    private int mRemoteDefault;
    private static AppConfigs INSTANCE;
    private FirebaseRemoteConfig config;

    private static final long CONFIG_EXPIRE_SECOND = 12 * 3600;

    private AppConfigs(Context context,int remoteDefault) {
        mContext = context;
        mRemoteDefault = remoteDefault;
        setConfig();

    }
    public FirebaseRemoteConfig getConfig() {
        return this.config;
    }


    public static AppConfigs getInstance(Application application,int remoteDefault) {
        synchronized (AppConfigs.class) {
            if (INSTANCE == null) {
                INSTANCE = new AppConfigs(application,remoteDefault);
            }
        }
        return INSTANCE;
    }

    public void setConfig() {
        LogUtils.logString(AppConfigs.class, "setConfig");
        if (FirebaseApp.getApps(mContext).isEmpty()) {
            return;
        }
        LogUtils.logString(AppConfigs.class, "xx");
        config = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setFetchTimeoutInSeconds(3600)
                .build();
        //Setting chế độ debug
        config.setConfigSettingsAsync(configSettings);

        // set default
        config.setDefaultsAsync(mRemoteDefault);
        //Vì chúng ta đang trong debug mode nên cần config được fetch và active ngay lập tức sau khi thay đổi trên console
        long expireTime = AdUnit.TEST ? 1 : CONFIG_EXPIRE_SECOND;
        //Mỗi lần khởi chạy app sẽ fetch config về và nếu thành công thì sẽ active config vừa lấy về
        config.fetch(expireTime)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        LogUtils.logString(AppConfigs.class, "Fuckkk");
                        if (task.isSuccessful()) {
                            config.fetchAndActivate();
                        }
                    }
                });
    }
    public static String getString(String key) {
        if (INSTANCE == null) {
            return "";
        }
        return INSTANCE.getConfig().getString(key);
    }

    public static Long getLong(String key) {
        if (INSTANCE == null) {
            return 0L;
        }
        return INSTANCE.getConfig().getLong(key);
    }

    public static Integer getInt(String key) {
        if (INSTANCE == null) {
            return 0;
        }
        return Integer.valueOf(INSTANCE.getConfig().getString(key));
    }

    public static boolean getBoolean(String key) {
        if (INSTANCE == null) {
            return false;
        }
        return INSTANCE.getConfig().getBoolean(key);
    }
}
