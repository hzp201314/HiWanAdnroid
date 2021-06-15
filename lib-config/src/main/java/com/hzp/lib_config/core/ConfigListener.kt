package com.hzp.lib_config.core

interface ConfigListener {
    fun onConfigUpdate(configMap: Map<String, Any>)
}