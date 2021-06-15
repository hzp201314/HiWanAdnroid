package com.hzp.lib_library.restful.annotation

/**
 * @BaseUrl("https://api.devio.org/as/")
 *fun test(@Filed("province") int provinceId)
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Filed(val value: String)