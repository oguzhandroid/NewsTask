package com.rent.donanmhabertask.ui.favourites.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rent.donanmhabertask.R
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.ui.adapters.NewsAdapter
import com.rent.donanmhabertask.ui.favourites.viewmodel.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favourites.*

@AndroidEntryPoint
class FavouritesFragment : Fragment(),NewsAdapter.NewsClickListener {

    private val favouritesVM: FavouritesViewModel by viewModels()

    lateinit var adapter: NewsAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeLiveData()
    }

    private fun initRecycler() {
        adapter = NewsAdapter(arrayListOf(),this,true)
        recyclerView2.adapter = adapter

        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView2.layoutManager = layoutManager
    }

    private fun observeLiveData() {
        favouritesVM.favouriteNews.observe(viewLifecycleOwner,{
            adapter.changeData(it as ArrayList<Data>)
            adapter.notifyDataSetChanged()
        })
    }



    override fun onResume() {
        super.onResume()
        favouritesVM.getFavouriteNews(requireContext())
    }

    override fun onNewsClickListener(data: Data) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
        startActivity(browserIntent)
    }

    override fun onNewsLongClickListener(data: Data): Boolean {
        favouritesVM.deleteFavouriteNews(requireContext(),data.id)
        return true
    }
}