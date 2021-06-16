package com.hzp.biz_home.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.hzp.biz_home.R
import com.hzp.biz_home.model.*
import com.hzp.lib_ability.HiAbility
import com.hzp.lib_common.route.HiRoute
import com.hzp.lib_common.ui.component.HiAbsListFragment
import com.hzp.lib_common.ui.component.HiBaseFragment
import com.hzp.lib_library.restful.annotation.CacheStrategy
import com.hzp.lib_ui.icfont.IconFontTextView
import com.hzp.lib_ui.tab.bottom.HiTabBottomLayout
import com.hzp.lib_ui.tab.common.IHiTabLayout
import com.hzp.lib_ui.tab.top.HiTabTopInfo
import java.lang.Exception

class HomePageFragment : HiAbsListFragment() {
    private lateinit var viewModel: HomeViewModel

    private var search_container: IconFontTextView? = null

    companion object {
        fun newInstance(): Fragment {
            val args = Bundle()
            val fragment = HomePageFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HiRoute.inject(this)
        //        HiTabBottomLayout.clipBottomPadding()
        search_container = view.findViewById<IconFontTextView>(R.id.search_container)
        search_container?.setOnClickListener {
            HiRoute.startActivity(
                context,
                null,
                "/search/main"
            )
        }

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.queryHomeBannerList(CacheStrategy.CACHE_FIRST).observe(viewLifecycleOwner,
            Observer { it?.let { updateUIBannerData(it) } })

        viewModel.queryHomeTopArticleList(CacheStrategy.CACHE_FIRST).observe(viewLifecycleOwner,
            Observer {
                it?.let { updateUITopArticleData(it) }
            })

        enableLoadMore { loadData() }
        loadData()

    }

    override fun onRefresh() {
        super.onRefresh()
        loadData()
    }

    private fun loadData() {
        viewModel.queryHomeArticleList(CacheStrategy.CACHE_FIRST, pageIndex).observe(viewLifecycleOwner,
            Observer {
                it?.let { updateUIArticleData(it) }
                if (it==null)finishRefresh(null)
            })
    }


    override fun getPageName(): String {
        return "home_page"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    private fun updateUIBannerData(data: HomeBannerList) {

    }

    private fun updateUITopArticleData(data: TopArticleList) {
        val dataItems = mutableListOf<ArticleItem>()
        data
    }

    private fun updateUIArticleData(data: ArticleList) {
        val dataItems = mutableListOf<ArticleItem>()
        for (item in data.data.datas) {
            val articleItem = ArticleItem(item)
            dataItems.add(articleItem)
        }
        finishRefresh(dataItems)
    }


}