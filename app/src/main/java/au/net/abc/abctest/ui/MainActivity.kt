package au.net.abc.abctest.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.net.abc.abctest.R
import au.net.abc.abctest.domain.OnArticlesLoadListener
import au.net.abc.abctest.model.Article
import au.net.abc.abctest.network.loadArticles
import au.net.abc.abctest.updateCount
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnArticlesLoadListener {

    private lateinit var mainAdapter: MainAdapter
    private var list: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.list)
        mainAdapter = MainAdapter(::onItemClickListener)
        list?.layoutManager = LinearLayoutManager(this)
        list?.adapter = mainAdapter
        findViewById<TextView>(R.id.articleCount).text = getString(R.string.article_count, 0)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        loadArticles(this)
    }

    private fun onItemClickListener(article: Article) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(article.href)
        }.also {
            startActivity(it)
        }
    }

    override fun onArticleLoaded(articles: List<Article>) {
        updateCount(articleCount, getString(R.string.article_count, articles.size))
        mainAdapter.submitList(articles)
    }

}
