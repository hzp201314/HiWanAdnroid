package com.hzp.hiwanandroid;

import android.content.Context;
import android.util.Log;

import androidx.core.os.TraceCompat;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hzp.lib_ability.HiAbility;
import com.hzp.lib_common.BuildConfig;
import com.hzp.lib_common.ui.component.HiBaseApplication;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;


import dagger.hilt.android.HiltAndroidApp;

//@HiltAndroidApp
public class HiApplication extends HiBaseApplication {
    @Override
    public void onCreate() {
        TraceCompat.beginSection("HiApplication_onCreate");
        super.onCreate();
        initArouter();
        initBugly();

        // CrashMgr.INSTANCE.init();
        // com.taobao.util.Alog.
//        HiAbility.INSTANCE.init(this, "Umeng", null);

//        String[] deviceInfo = HiAbility.INSTANCE.getTestDeviceInfo(this);
//        Log.e("HiApplication",deviceInfo[0]+"---"+deviceInfo[1]);
        TraceCompat.endSection();

        //TaskStartUp.start();
    }

    private void initBugly() {
        Bugly.init(this, "10d86971eb", true);
        Bugly.setIsDevelopmentDevice(this, true);
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        TraceCompat.beginSection("HiApplication_attachBaseContext");
        super.attachBaseContext(base);
        MultiDex.install(base);
        Beta.installTinker();
        TraceCompat.endSection();


    }

}
