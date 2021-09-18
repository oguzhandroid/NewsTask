package com.rent.donanmhabertask.ui.favourites.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.data.service.NewsDao
import com.rent.donanmhabertask.data.service.NewsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class FavouritesViewModel@Inject constructor(
    private val dao: NewsDao
) : ViewModel(), CoroutineScope {
    private val job = Job()

    var favouriteNews = MutableLiveData<List<Data>>()

    fun getFavouriteNews(context: Context) {
        launch {
            favouriteNews.value = dao.getAllNews()
        }
    }

    fun deleteFavouriteNews(context: Context,id: Int) {
        launch {
            dao.deleteNews(id)
            favouriteNews.value = dao.getAllNews()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}