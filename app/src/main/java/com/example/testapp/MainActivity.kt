package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.fragment.FragmentOne
import com.example.testapp.fragment.FragmentThree
import com.example.testapp.fragment.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val fragmentList : ArrayList<Fragment> =
            arrayListOf(FragmentOne.newInstance(), FragmentTwo.newInstance(),
                FragmentThree.newInstance())

        val pagerAdapter = ScreenSlidePagerAdapter(this,fragmentList)
        viewPager2.adapter = pagerAdapter


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("=> ","View pager selected position")
            }
        })
    }

    override fun onBackPressed() {
        if (viewPager2.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa : FragmentActivity, private val fragments:ArrayList<Fragment>)
        : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

}
