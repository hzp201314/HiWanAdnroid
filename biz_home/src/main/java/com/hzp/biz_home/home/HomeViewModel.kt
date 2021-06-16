package com.hzp.biz_home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hzp.biz_home.api.HomeApi
import com.hzp.biz_home.model.*
import com.hzp.lib_common.http.ApiFactory
import com.hzp.lib_library.restful.HiCallback
import com.hzp.lib_library.restful.HiResponse
import com.hzp.lib_library.restful.annotation.CacheStrategy

class HomeViewModel(private val savedState: SavedStateHandle) : ViewModel() {
    /**
     * 获取轮播图数据
     */
    fun queryHomeBannerList(cacheStrategy: Int): LiveData<HomeBannerList?> {
        val liveData = MutableLiveData<HomeBannerList?>()

        //内存缓存加载
        val memCache = savedState.get<HomeBannerList?>("bannerList")
        //只有是第一次加载时  才需要从内存中取
        if (memCache != null && cacheStrategy == CacheStrategy.CACHE_FIRST) {
            liveData.postValue(memCache)
            return liveData
        }
        //网络加载
        ApiFactory.create(HomeApi::class.java)
            .queryHomeBannerList(cacheStrategy)
            .enqueue(object : HiCallback<HomeBannerList> {
                override fun onSuccess(response: HiResponse<HomeBannerList>) {
                    val data = response.data;
                    if (response.successful() && data != null) {
                        //一次缓存数据，一次接口数据
                        liveData.value = data
                        //只有在刷新的时候，且不是本地缓存的数据 才存储到内容中
                        if (cacheStrategy != CacheStrategy.NET_ONLY && response.code == HiResponse.SUCCESS) {
                            savedState.set("bannerList", data)
                        }
                    } else {
                        liveData.postValue(null)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    liveData.postValue(null)
                }

            })
        return liveData
    }

    /**
     * 获取首页文章数据
     */
    fun queryHomeArticleList(cacheStrategy: Int, pageIndex: Int): LiveData<ArticleList?> {
        val liveData = MutableLiveData<ArticleList?>()

        //内存缓存加载
        val memCache = savedState.get<ArticleList?>("articleList")
        //只有是第一次加载时  才需要从内存中取
        if (memCache != null && cacheStrategy == CacheStrategy.CACHE_FIRST) {
            liveData.postValue(memCache)
            return liveData
        }
        //网络加载
        ApiFactory.create(HomeApi::class.java)
            .queryHomeArticleList(cacheStrategy,pageIndex)
            .enqueue(object : HiCallback<ArticleList> {
                override fun onSuccess(response: HiResponse<ArticleList>) {
                    val data = response.data;
                    if (response.successful() && data != null) {
                        //一次缓存数据，一次接口数据
                        liveData.value = data
                        //只有在刷新的时候，且不是本地缓存的数据 才存储到内容中
                        if (cacheStrategy != CacheStrategy.NET_ONLY && response.code == HiResponse.SUCCESS) {
                            savedState.set("articleList", data)
                        }
                    } else {
                        liveData.postValue(null)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    liveData.postValue(null)
                }

            })
        return liveData
    }

    /**
     * 获取首页置顶文章数据
     */
    fun queryHomeTopArticleList(cacheStrategy: Int): LiveData<TopArticleList?> {
        val liveData = MutableLiveData<TopArticleList?>()

        //内存缓存加载
        val memCache = savedState.get<TopArticleList?>("topArticleList")
        //只有是第一次加载时  才需要从内存中取
        if (memCache != null && cacheStrategy == CacheStrategy.CACHE_FIRST) {
            liveData.postValue(memCache)
            return liveData
        }
        //网络加载
        ApiFactory.create(HomeApi::class.java)
            .queryHomeTopArticleList(cacheStrategy)
            .enqueue(object : HiCallback<TopArticleList> {
                override fun onSuccess(response: HiResponse<TopArticleList>) {
                    val data = response.data;
                    if (response.successful() && data != null) {
                        //一次缓存数据，一次接口数据
                        liveData.value = data
                        //只有在刷新的时候，且不是本地缓存的数据 才存储到内容中
                        if (cacheStrategy != CacheStrategy.NET_ONLY && response.code == HiResponse.SUCCESS) {
                            savedState.set("topArticleList", data)
                        }
                    } else {
                        liveData.postValue(null)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    liveData.postValue(null)
                }

            })
        return liveData
    }


}