package com.hzp.lib_debugtool

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class HiDebug(val name: String, val desc: String)