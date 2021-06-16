package com.hzp.lib_common.ui.component

import android.app.Application
import com.google.gson.Gson
import com.hzp.lib_config.HiConfig
import com.hzp.lib_config.core.JsonParser
import com.hzp.lib_library.log.HiConsolePrinter
import com.hzp.lib_library.log.HiFilePrinter
import com.hzp.lib_library.log.HiLogConfig
import com.hzp.lib_library.log.HiLogManager
import com.hzp.lib_library.util.ActivityManager


open class HiBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
        initConfig()
        //应用初始化时线程空闲时预加载FlutterEngine
//        HiFlutterCacheManager.instance?.preLoad(this)
//        HiFlutterCacheManager.instance?.preLoadDartVM(this)

    }

    private fun initConfig() {
        HiConfig.instance.init(object : JsonParser {
            override fun <T> fromJson(json: String, clazz: Class<T>): T? {
                return Gson().fromJson(json, clazz)
            }
        }, this)
    }

    private fun initLog() {
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src: Any? -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }
        }, HiConsolePrinter(), HiFilePrinter.getInstance(cacheDir.absolutePath, 0))
        ActivityManager.instance.init(this)
    }
}