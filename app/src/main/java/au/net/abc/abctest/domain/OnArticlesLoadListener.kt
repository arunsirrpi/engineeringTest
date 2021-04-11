package au.net.abc.abctest.domain

import au.net.abc.abctest.model.Article

interface OnArticlesLoadListener {
    fun onArticleLoaded(articles: List<Article>)
}