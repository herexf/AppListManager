package com.layoutxml.applistmanagerlibrary.interfaces;

import com.layoutxml.applistmanagerlibrary.objects.AppData;

import java.util.List;

/**
 * Created by LayoutXML on 23/08/2018
 */
public interface NewAppListener {
    void newAppListener(List<AppData> appDataList, Integer applicationFlags, Boolean applicationFlagsMatch, Boolean fromReceiver, Integer uniqueIdentifier);
}
