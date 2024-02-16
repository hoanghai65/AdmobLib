package com.haihd1.abmoblibrary.abstract_factory.factory_method;


import com.haihd1.abmoblibrary.abstract_factory.AdmobHelper;
import com.haihd1.abmoblibrary.abstract_factory.factory_method.model.TYPE;

public abstract class AdmobFactory {
    public abstract AdmobHelper providerAdmob(TYPE type);
}

