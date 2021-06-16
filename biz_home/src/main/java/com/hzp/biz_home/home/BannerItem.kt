package com.hzp.biz_home.home

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.core.os.TraceCompat
import androidx.recyclerview.widget.RecyclerView
import com.hzp.biz_home.R
import com.hzp.biz_home.model.HomeBanner
import com.hzp.lib_ability.HiAbility
import com.hzp.lib_common.ext.loadUrl
import com.hzp.lib_library.util.HiDisplayUtil
import com.hzp.lib_library.util.HiRes
import com.hzp.lib_ui.banner.HiBanner
import com.hzp.lib_ui.banner.core.HiBannerMo
import com.hzp.lib_ui.item.HiDataItem
import com.hzp.lib_ui.item.HiViewHolder

class BannerItem(val list: List<HomeBanner>) : HiDataItem<List<HomeBanner>, HiViewHolder>(list) {
    override fun onBindData(holder: HiViewHolder, position: Int) {
        TraceCompat.beginSection("BannerItem")
        val context = holder.itemView.context
        val banner = holder.itemView as HiBanner

        banner.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                TraceCompat.endSection()
                banner.viewTreeObserver.removeOnPreDrawListener(this)
                return false
            }
        })

        val models = mutableListOf<HiBannerMo>()
        list.forEachIndexed { index, homeBanner ->
            val bannerMo = object : HiBannerMo() {}
            bannerMo.url = homeBanner.url
            models.add(bannerMo)
        }

        banner.setOnBannerClickListener { viewHolder, bannerMo, position ->
            val homeBanner = list[position]


            HiAbility.traceEvent(
                "home_page_click",
                mapOf<String, Any>(Pair("index", position), Pair("card_type", "banner"))
            )
        }
        banner.setBannerData(models)
        banner.setBindAdapter { viewHolder, mo, position ->
            ((viewHolder.rootView) as ImageView).loadUrl(mo.url)
        }

        HiAbility.traceEvent(
            "home_page_expose",
            mapOf(Pair("index", position), Pair("card_type", "banner"))
        )
    }

    override fun getItemView(parent: ViewGroup): View? {
        val context = parent.context
        val banner = HiBanner(context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(160f)
        )
        params.bottomMargin = HiDisplayUtil.dp2px(10f)
        banner.layoutParams = params
        banner.setBackgroundColor(HiRes.getColor(R.color.color_white))
        return banner
    }

    override fun onViewAttachedToWindow(holder: HiViewHolder) {
        super.onViewAttachedToWindow(holder)
        val itemView = holder.itemView
        val layoutParams = itemView.layoutParams
        layoutParams.height = (HiDisplayUtil.getDisplayHeightInPx(itemView.context) / 2.6).toInt()
        itemView.layoutParams = layoutParams
    }

}