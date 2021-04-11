package au.net.abc.abctest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import au.net.abc.abctest.R
import au.net.abc.abctest.model.Article
import kotlinx.android.synthetic.main.item_article_row.view.*

class MainAdapter(private val clickListener: ((Article) -> Unit)?=null) :
    ListAdapter<Article, MainAdapter.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_article_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article, clickListener: ((Article) -> Unit)?) {
            with(itemView) {
                title.text = article.title
                synopsis.text = article.synopsis
                itemView.setOnClickListener {
                    clickListener?.run {
                        this(article)
                    }
                }
            }
        }
    }
}
