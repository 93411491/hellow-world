package com.example.likedouyin

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import fr.castorflex.android.verticalviewpager.VerticalViewPager

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"

        const val videPath_1 = "/sdcard/DCIM/Camera/4b91e9cce7725178d444ce30b32b5fa3.mp4"
        const val videPath_2 = "/sdcard/DCIM/Camera/bc437e8a26a408f3b1bcde87f7b76c1c.mp4"
        const val videPath_3 = "/sdcard/DCIM/Camera/f50ad0eef795516d8905de12101caa94.mp4"
        const val videPath_4 = "/sdcard/DCIM/Camera/f8d2e1e03d2538b1a68bdc9637b9791d.mp4"

    }

    private var mViewPager: VerticalViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        mViewPager?.adapter = MyPagerAdapter()
        mViewPager?.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {

            }

        })
    }

    private fun initView() {
        mViewPager = findViewById(R.id.view_pager)
    }

    class MyPagerAdapter : PagerAdapter(){

        override fun getCount(): Int {
            // 表示当前可翻页的数量
            return 4
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            //
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            提前加载下一个页面内容
//            Log.d(TAG, ":(wzr)-> $position");
            val view = LayoutInflater.from(container.context).inflate(R.layout.view_item, null)
            view.id = position
            container.addView(view)
            return view

        }
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView((`object` as View))
        }
    }
}