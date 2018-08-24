package com.layoutxml.applistmanagerlibrary.objects;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by LayoutXML on 22/08/2018
 */
public class AppData {

    private static final String TAG = "AppData";

    private String appName;
    private Drawable appIcon;
    private Integer flags;
    @NonNull
    private String appPackageName = "";

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @NonNull
    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(@NonNull String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof AppData){
            AppData p = (AppData) o;
            return this.getAppPackageName().equals(p.getAppPackageName());
        } else
            return false;
    }
}
