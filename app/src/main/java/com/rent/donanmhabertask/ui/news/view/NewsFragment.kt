package com.rent.donanmhabertask.ui.news.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rent.donanmhabertask.R
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.data.model.NewsResponse
import com.rent.donanmhabertask.ui.adapters.NewsAdapter
import com.rent.donanmhabertask.ui.news.viewmodel.NewsViewModel
import com.rent.donanmhabertask.util.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*


@AndroidEntryPoint
class NewsFragment : Fragment(), NewsAdapter.NewsClickListener {

    private val newsVM: NewsViewModel by viewModels()

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    lateinit var rv: RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var layoutManager: LinearLayoutManager

    lateinit var tvPageNo: TextView
    lateinit var progressBar: ProgressBar

    private var currentPage = 0

    lateinit var newsList: NewsResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI(view)
        newsVM.getNews(0, 15)
        initRecycler()
        observeLiveData()
    }

    private fun initUI(view: View) {
        rv = view.findViewById(R.id.recyclerView)
        tvPageNo = view.findViewById(R.id.tv_page_no)
        progressBar = view.findViewById(R.id.progress_contents)

        newsVM.currentPage.value = 1
    }

    private fun initRecycler() {
        adapter = NewsAdapter(arrayListOf(), this,false)
        recyclerView.adapter = adapter

        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            newsVM.currentPage.value =
                layoutManager.findFirstVisibleItemPosition() / 15 + 1
        }

        recyclerView?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreNews()
            }

        })
    }

    private fun observeLiveData() {
        newsVM.news.observe(viewLifecycleOwner, {
            it?.let {
                newsList = it
                adapter.addData(it.data as ArrayList<Data>)
                adapter.notifyDataSetChanged()
                isLoading = false
            }
        })

        newsVM.loading.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        newsVM.currentPage.observe(viewLifecycleOwner, {
            tvPageNo.text = it.toString()
        })
    }

    private fun getMoreNews() {
        currentPage++
        newsVM.getNews(currentPage, 15)
    }

    override fun onNewsClickListener(data: Data) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
        startActivity(browserIntent)
    }

    override fun onNewsLongClickListener(data: Data): Boolean {
        newsVM.saveNews(data, requireContext())
        return true
    }
}