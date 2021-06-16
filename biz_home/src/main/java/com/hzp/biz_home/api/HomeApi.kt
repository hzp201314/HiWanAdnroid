package com.hzp.biz_home.api

import com.hzp.biz_home.model.*
import com.hzp.lib_library.restful.HiCall
import com.hzp.lib_library.restful.annotation.CacheStrategy
import com.hzp.lib_library.restful.annotation.GET
import com.hzp.lib_library.restful.annotation.Path

interface HomeApi {

    @CacheStrategy(CacheStrategy.CACHE_FIRST)
    @GET("banner/json")
    fun queryHomeBannerList(@CacheStrategy cacheStrategy: Int): HiCall<HomeBannerList>

    @CacheStrategy(CacheStrategy.CACHE_FIRST)
    @GET("/article/list/{pageIndex}/json")
    fun queryHomeArticleList(
        @CacheStrategy cacheStrategy: Int,
        @Path("pageIndex") pageIndex: Int
    ): HiCall<ArticleList>

    @CacheStrategy(CacheStrategy.CACHE_FIRST)
    @GET("/article/top/json")
    fun queryHomeTopArticleList(
        @CacheStrategy cacheStrategy: Int
    ): HiCall<TopArticleList>
}