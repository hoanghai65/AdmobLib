package com.haihd1.abmoblibrary.abstract_factory;

import android.app.Activity;
import android.view.ViewGroup;

import com.haihd1.abmoblibrary.utils.callback.ActionCallBack;
import com.haihd1.abmoblibrary.utils.callback.AdmobCallBack;

import java.util.List;

public interface AdmobHelper {
    void initId(String id);
    void initListId(List<String> ids);
    void initLayout(ViewGroup viewGroup);
    void loadAdmob(Activity activity);
    void reloadAdmob();
    void setAdmobCallBack(AdmobCallBack admobCallBack);
    void setActionCallBack(ActionCallBack admobCallBack);
}
