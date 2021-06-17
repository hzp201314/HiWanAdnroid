package com.hzp.lib_common.http

import com.hzp.lib_common.utils.SPUtil
import com.hzp.lib_library.restful.HiRestful

object ApiFactory {
    val KEY_DEGRADE_HTTP = "degrade_http"
    val HTTPS_BASE_URL = "https://www.wanandroid.com"
    val HTTP_BASE_URL = "http://www.wanandroid.com"
    val degrade2Http = SPUtil.getBoolean(KEY_DEGRADE_HTTP)
    val baseUrl = if (degrade2Http) HTTP_BASE_URL else HTTPS_BASE_URL
    private val hiRestful: HiRestful = HiRestful(baseUrl, RetrofitCallFactory(baseUrl))

    init {
        hiRestful.addInterceptor(BizInterceptor())
        hiRestful.addInterceptor(HttpCodeInterceptor())
        hiRestful.addInterceptor(HiConfigInterceptor())

        SPUtil.putBoolean(KEY_DEGRADE_HTTP, false)
    }

    fun <T> create(service: Class<T>): T {
        return hiRestful.create(service)
}
}