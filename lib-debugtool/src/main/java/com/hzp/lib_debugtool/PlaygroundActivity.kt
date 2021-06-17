package com.hzp.lib_debugtool

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_playground.*
//import com.hzp.lib_common.rn.HiRNActivity
//import com.hzp.lib_common.rn.HiRNCacheManager
import com.hzp.lib_common.ui.component.HiBaseActivity
import com.hzp.lib_library.util.HiStatusBar

@Route(path = "/playground/main")
class PlaygroundActivity : HiBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiStatusBar.setStatusBar(this, true, translucent = false)
        setContentView(R.layout.activity_playground)
        initUI()
    }

    private fun initUI() {
        action_back.setOnClickListener {
            onBackPressed()
        }
        native_widget.setOnClickListener {
            ARouter.getInstance().build("/flutter/main").withString("moduleName", "nativeView")
                .navigation()
        }
        go_rn.setOnClickListener {
//            ARouter.getInstance().build("/rn/main").withString(HiRNActivity.HI_RN_BUNDLE, HiRNCacheManager.MODULE_NAME_BRIDGE_DEMO).navigation()
        }
    }
}