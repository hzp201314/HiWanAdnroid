package com.hzp.lib_config.core

interface JsonParser {
    fun <T> fromJson(json: String, clazz: Class<T>): T?
}