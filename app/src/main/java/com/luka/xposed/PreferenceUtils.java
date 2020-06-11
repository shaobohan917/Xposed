package com.luka.xposed;

import de.robv.android.xposed.XSharedPreferences;

public class PreferenceUtils {

    public static final String AUTO_ALL ="auto_all";
    private static XSharedPreferences intance = null;

    public static XSharedPreferences getIntance(){
        if (intance == null){
            intance = new XSharedPreferences("com.luka.xposed","config");
            intance.makeWorldReadable();
        }else {
            intance.reload();
        }
        return intance;
    }

    public static String getNumber(){
        return getIntance().getString(AUTO_ALL,"0.0");
    }
}
