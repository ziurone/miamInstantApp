package com.example.miaminstantapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_ticket_article.*

class ArticlesAdapter:
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>()
{
    private var articles: List<ShopArticleEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun setData(articlesList: List<ShopArticleEntity>) {
        articles = articlesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(article: ShopArticleEntity) {
            articleName.text = article.articleName
        }
    }

}