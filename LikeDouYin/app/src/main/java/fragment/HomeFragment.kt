package fragment

import adapter.CommonAdapter
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.likedouyin.R
import com.google.android.material.tabs.TabLayout

/*
* 首页Fragment
* */

class HomeFragment : BaseFragment() {

    private val mFragmentList = ArrayList<Fragment>()
    private lateinit var mTitleTablayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun setContentView(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        Log.d(Companion.TAG, "wzr->init: HomeFragment")
        mTitleTablayout = view!!.findViewById(R.id.tab_title)
        mViewPager = view!!.findViewById(R.id.home_view_pager)
        setSlideFragment()
    }

    private fun setSlideFragment() {
        mFragmentList.add(LocationFragment())
        mFragmentList.add(RecommandFragment())

        mTitleTablayout.addTab(mTitleTablayout.newTab().setText("定位"));
        mTitleTablayout.addTab(mTitleTablayout.newTab().setText("推荐"))

        val commonAdapter = CommonAdapter(childFragmentManager,mFragmentList, arrayOf("定位","推荐"))
        mViewPager.adapter = commonAdapter

        mTitleTablayout.setupWithViewPager(mViewPager)
        mTitleTablayout.getTabAt(1)!!.select()
        mTitleTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}