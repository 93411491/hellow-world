package com.example.likedouyin.activity

import com.example.likedouyin.adapter.CommonAdapter
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.likedouyin.R
import com.google.android.material.tabs.TabLayout
import com.example.likedouyin.fragment.MainFragment
import com.example.likedouyin.fragment.PersonalHomeFragment
import com.example.likedouyin.myUtils.Util

/*
* 主页activity，内部包含私人fragment与主页fragment
* */
class DouyinActivity : BaseActivity() {
    companion object {
        private const val TAG = "DouyinActivity"
        private const val EXIT_TIME = 2000
    }

    private var lastExitTime : Long = 0

    private lateinit var mViewPager: ViewPager
    private lateinit var mMainFragment: Fragment
    private lateinit var mPersonalFragment: Fragment

    private lateinit var mMainTabLayout: TabLayout
    private var mDefaultPosition = 0
    private var list: ArrayList<Fragment> = ArrayList()

    override fun setLayoutID(): Int {
        return R.layout.avtivity_douyin
    }

    override fun init() {
        mViewPager = findViewById(R.id.lr_view_pager)

        initFirstPageContent()
    }

    private fun initFirstPageContent() {
        mMainFragment = MainFragment()
        mPersonalFragment = PersonalHomeFragment()
        list.add(mMainFragment)
        list.add(mPersonalFragment)

        if (mViewPager.adapter == null) {
            mViewPager.adapter = CommonAdapter(supportFragmentManager, list, arrayOf("", ""))
        }

    }

    override fun onBackPressed() {

        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastExitTime > EXIT_TIME) {
            if (mViewPager.currentItem == 1) {
                mViewPager.currentItem = 0
            } else {
                Util.showToastTips(this, "再按一次")
                lastExitTime = currentClickTime
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewPager.currentItem = 0
        mDefaultPosition = 0
    }
}