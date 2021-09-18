package com.rent.donanmhabertask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rent.donanmhabertask.ui.favourites.view.FavouritesFragment
import com.rent.donanmhabertask.ui.news.view.NewsFragment
import com.rent.donanmhabertask.ui.viewpager.MyViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
    }

    private fun setAdapter() {
        val viewPagerAdapter = MyViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(NewsFragment(),resources.getString(R.string.news))
        viewPagerAdapter.addFragment(FavouritesFragment(),resources.getString(R.string.favorites))
        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}