package com.luka.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMethod implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("loadPackageParam:" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.jurong.carok")) {
//        if (loadPackageParam.packageName.equals("com.android.dazhihui")) {
            XposedBridge.log("has Hooked!");
            Class clazz = loadPackageParam.classLoader.loadClass("com.jurong.carok.fragment.MyFragment");
//            Class clazz = loadPackageParam.classLoader.loadClass("com.tencent.im.fragment.DistSnatchFragment");
            XposedHelpers.findAndHookMethod(clazz, "getBalance", new XC_MethodHook() {
//            XposedHelpers.findAndHookMethod(clazz, "getMoney", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    String number = PreferenceUtils.getNumber();
                    param.setResult(number);
                    XposedBridge.log("已成功设置值：" + number);
                }
            });
        }
    }
}