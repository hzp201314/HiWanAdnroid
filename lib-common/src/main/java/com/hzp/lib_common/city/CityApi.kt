package com.hzp.lib_common.city

import com.hzp.lib_library.restful.HiCall
import com.hzp.lib_library.restful.annotation.GET


internal interface CityApi {
    @GET("cities")
    fun listCities(): HiCall<CityModel>
}
