package com.haihd1.admoblib.test;

import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.open_resume.AdsApplication;
import com.haihd1.abmoblibrary.adparam.AdUnit;

public class MyApplication extends AdsApplication {

    @Override
    protected String initAppOpenResume() {
        return AdUnit.getAdmobOpenId();
    }
}
