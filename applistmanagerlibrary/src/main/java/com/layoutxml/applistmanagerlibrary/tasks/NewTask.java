package com.layoutxml.applistmanagerlibrary.tasks;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.layoutxml.applistmanagerlibrary.interfaces.NewListener;
import com.layoutxml.applistmanagerlibrary.objects.AppData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LayoutXML on 23/08/2018
 */
public class NewTask extends AsyncTask<Void,Void,List<AppData>>{

    private static final String TAG = "NewTask";

    private final WeakReference<NewListener> allNewAppsListener;
    private PackageManager packageManager;
    private List<ApplicationInfo> applicationInfoList;
    private List<AppData> receivedAppList;
    private Integer flags;
    private Boolean match;

    public NewTask(PackageManager packageManager, List<ApplicationInfo> applicationInfoList, List<AppData> receivedAppList, Integer flags, Boolean match, WeakReference<NewListener> newListener) {
        this.packageManager = packageManager;
        this.applicationInfoList = applicationInfoList;
        this.allNewAppsListener = newListener;
        this.receivedAppList = receivedAppList;
        this.flags = flags;
        this.match = match;
    }

    @Override
    protected final List<AppData> doInBackground(Void... voids){
        List<AppData> appDataList = new ArrayList<>();
        for (ApplicationInfo applicationInfo:applicationInfoList) {
            AppData app = new AppData();
            app.setAppName(applicationInfo.loadLabel(packageManager).toString());
            app.setAppPackageName(applicationInfo.packageName);
            app.setAppIcon(applicationInfo.loadIcon(packageManager));
            app.setFlags(applicationInfo.flags);
            if (receivedAppList!=null) {
                if (match) {
                    if ((flags == null) || ((applicationInfo.flags & flags) != 0)) {
                        if (!receivedAppList.contains(app))
                            appDataList.add(app);
                    }
                } else {
                    if ((flags == null) || ((applicationInfo.flags & flags) == 0)) {
                        if (!receivedAppList.contains(app))
                            appDataList.add(app);
                    }
                }
            }
            if (isCancelled())
                break;
        }
        return appDataList;
    }

    @Override
    protected void onPostExecute(List<AppData> appDataList) {
        final NewListener listener = allNewAppsListener.get();
        if (listener!=null) {
            listener.newListener(appDataList, flags, match, false);
        }
    }

}
