package com.example.likedouyin

import adapter.CommonAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import fr.castorflex.android.verticalviewpager.VerticalViewPager
import fragment.MainFragment
import fragment.PersonalHomeFragment

class DouyinActivity : BaseActivity() {
    companion object {
        private const val TAG = "DouyinActivity"
    }

    private lateinit var mViewPager: ViewPager
    private lateinit var mMainFragment: Fragment
    private lateinit var mPersonalFragment: Fragment

    private var list: ArrayList<Fragment> = ArrayList()

    override fun setLayoutID(): Int {
        return R.layout.avtivity_douyin
    }

    override fun init() {
        mViewPager = findViewById(R.id.lr_view_pager)

        mMainFragment = MainFragment()
        mPersonalFragment = PersonalHomeFragment()

        list!!.add(mPersonalFragment)
        list!!.add(mMainFragment)

        mViewPager.adapter = CommonAdapter(supportFragmentManager, list!!, arrayOf("", ""))

    }

}