package com.rent.donanmhabertask.ui.news.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.data.model.NewsResponse
import com.rent.donanmhabertask.data.service.NewsAPI
import com.rent.donanmhabertask.data.service.NewsDao
import com.rent.donanmhabertask.data.service.NewsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val api: NewsAPI,
    private val dao: NewsDao
) : ViewModel(), CoroutineScope {
    private val job = Job()
    private val disposable = CompositeDisposable()
    var news = MutableLiveData<NewsResponse>()
    var currentPage = MutableLiveData<Int>()
    var error = MutableLiveData<Throwable>()
    var loading = MutableLiveData<Boolean>()

    fun getNews(pageIndex: Int, pageSize: Int) {
        loading.value = true
        disposable.add(
            api.getResponse(pageIndex, pageSize)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                    override fun onSuccess(t: NewsResponse) {
                        news.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = e
                        loading.value = false
                    }

                })
        )
    }

     fun saveNews(data: Data, context: Context) {
        launch {
            var fetchedData : Data? = dao.getNews(data.id)
            if (fetchedData == null)
            dao.insertNews(data)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}