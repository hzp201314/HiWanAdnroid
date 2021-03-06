package com.hzp.lib_ability

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.umeng.commonsdk.statistics.common.DeviceConfig
import com.hzp.lib_ability.analyse.AnalyseUtil
import com.hzp.lib_ability.alipay.AliPayHelper
import com.hzp.lib_ability.alipay.PayResult
import com.hzp.lib_ability.push.IPushMessageHandler
import com.hzp.lib_ability.push.PushInitialization
import com.hzp.lib_ability.scan.ScanActivity
import com.hzp.lib_ability.share.ShareBundle
import com.hzp.lib_ability.share.ShareManager
import com.hzp.lib_library.util.AppGlobals
import com.hzp.lib_library.util.HiViewUtil

object HiAbility {
    private val scanResultLiveData = MutableLiveData<String>()
    fun init(
        application: Application, channel: String,
        iPushMessageHandler: IPushMessageHandler? = null
    ) {
        if (HiViewUtil.inMainProcess(application)) {
            PushInitialization.init(application, channel, iPushMessageHandler)
            AnalyseUtil.init(application, channel)
        }
    }


    /**
     * 唤起分享面板
     */
    fun share(context: Context, shareBundle: ShareBundle) {
        ShareManager.share(context, shareBundle)
    }

    /**
     * 打开扫码页,结果通过observer来接收
     */
    fun openScanActivity(activity: Activity, observer: Observer<String>) {
        if (activity is LifecycleOwner) {
            scanResultLiveData.observe(activity, observer)
        }
        activity.startActivity(Intent(activity, ScanActivity::class.java))
    }

    internal fun onScanResult(scanResult: String) {
        scanResultLiveData.postValue(scanResult)
        scanResultLiveData.value = null
    }

    fun traceEvent(eventId: String, values: Map<String, Any>) {
        AnalyseUtil.traceEvent(AppGlobals.get()!!, eventId, values)
    }

    /**
     * 是用来获取设备的唯一ID 和mac
     */
    fun getTestDeviceInfo(context: Context?): Array<String?>? {
        val deviceInfo = arrayOfNulls<String>(2)
        try {
            if (context != null) {
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context)
                deviceInfo[1] = DeviceConfig.getMac(context)
            }
        } catch (e: Exception) {
        }
        return deviceInfo
    }

    fun aliPay(activity: Activity, orderInfo: String, observer: Observer<PayResult>) {
        AliPayHelper.pay(activity, orderInfo, observer)
    }
}