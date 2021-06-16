package com.hzp.biz_home.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hzp.biz_home.R
import com.hzp.biz_home.model.ArticleDataList
import com.hzp.lib_common.route.HiRoute
import com.hzp.lib_ui.item.HiDataItem
import com.hzp.lib_ui.item.HiViewHolder

class ArticleItem(private val item: ArticleDataList) :
    HiDataItem<ArticleDataList, HiViewHolder>(item) {
    @SuppressLint("SetTextI18n")
    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.findViewById<TextView>(R.id.item_home_author)?.text = item.author
        holder.findViewById<TextView>(R.id.item_home_top)?.visibility =
            if (item.type == 1) View.VISIBLE else View.GONE
        holder.findViewById<TextView>(R.id.item_home_new)?.visibility =
            if (item.fresh) View.VISIBLE else View.GONE
        holder.findViewById<TextView>(R.id.item_home_type1)?.visibility =
            if (item.tags.isNotEmpty()) View.VISIBLE else View.GONE
        holder.findViewById<TextView>(R.id.item_home_type1)?.text = item.tags[0].name
        holder.findViewById<TextView>(R.id.item_home_content)?.text = item.title
        holder.findViewById<TextView>(R.id.item_home_type2)?.text =
            item.superChapterName + "·" + item.chapterName

        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("articleId", item.id)
            //TODO 文章详情页 未实现
            HiRoute.startActivity(context, bundle, "/detail/main")
        }
    }


    override fun getItemLayoutRes(): Int {
        return R.layout.layout_home_article_list_item
    }

    override fun onCreateViewHolder(parent: ViewGroup): HiViewHolder? {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(getItemLayoutRes(),parent,false)
        return HiViewHolder(view)
    }

}