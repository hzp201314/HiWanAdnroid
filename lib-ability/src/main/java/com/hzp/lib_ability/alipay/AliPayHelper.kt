package com.hzp.lib_ability.alipay

import android.app.Activity
import androidx.lifecycle.Observer
import com.alipay.sdk.app.PayTask
import com.hzp.lib_library.executor.HiExecutor

object AliPayHelper {
    fun pay(activity: Activity, orderInfo: String, observer: Observer<PayResult>) {
        HiExecutor.execute(runnable = Runnable {
            val aliPayTask = PayTask(activity)

            val resultMap = aliPayTask.payV2(orderInfo, true)

            val payResult = PayResult(resultMap)

            observer.onChanged(payResult)
        })
    }
}