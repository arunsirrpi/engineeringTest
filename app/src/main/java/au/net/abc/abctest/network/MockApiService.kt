package au.net.abc.abctest.network

import android.os.Looper
import au.net.abc.abctest.domain.OnArticlesLoadListener
import au.net.abc.abctest.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors

private val jobExecutor = Executors.newFixedThreadPool(5)

private var listener: OnArticlesLoadListener? = null

private fun getContents(): List<Article> =
    Gson().fromJson(
        Resource().getResponse(),
        object : TypeToken<List<Article?>?>() {}.type
    )

fun loadArticles(listener1: OnArticlesLoadListener) {
    listener = listener1
    jobExecutor.execute {
        val articles: List<Article> = getContents()
        Thread.sleep(3000)

        listener?.onArticleLoaded(articles)

    }

}