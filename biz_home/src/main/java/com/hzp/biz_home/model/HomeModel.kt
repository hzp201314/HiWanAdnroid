package com.hzp.biz_home.model

import java.io.Serializable

data class ArticleList(
    val `data`: ArticleData,
    val errorCode: Int,
    val errorMsg: String
): Serializable

data class ArticleData(
    val curPage: Int,
    val datas: List<ArticleDataList>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
): Serializable

data class ArticleDataList(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
): Serializable

data class Tag(
    val name: String,
    val url: String
): Serializable

data class TopArticleList(
    val `data`: List<ArticleDataList>,
    val errorCode: Int,
    val errorMsg: String
): Serializable


data class HomeBannerList(
    val `data`: List<HomeBannerData>,
    val errorCode: Int,
    val errorMsg: String
): Serializable

data class HomeBannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
): Serializable


