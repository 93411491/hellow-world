package com.example.likedouyin.fragment

import android.graphics.Color
import android.os.CountDownTimer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.likedouyin.R
import com.example.likedouyin.adapter.FeedViewAdapter
import com.example.likedouyin.view.ViewPagerLayoutManager
import java.util.ArrayList

class RecommandFragment : BaseFragment() {

    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mRefreshLayout : SwipeRefreshLayout
    private lateinit var mViewPagerLayoutManager: ViewPagerLayoutManager

    override fun setContentView(): Int {
        return R.layout.fragment_recommand
    }

    override fun init() {

        var list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        mRecyclerView = view!!.findViewById(R.id.view_recycleView)
        mRefreshLayout = view!!.findViewById(R.id.refresh_layout)

        mViewPagerLayoutManager = ViewPagerLayoutManager(context)
        mViewPagerLayoutManager.orientation = RecyclerView.VERTICAL
        mRecyclerView.layoutManager = mViewPagerLayoutManager

        val feedViewAdapter = FeedViewAdapter(list as ArrayList<Int>?, activity)
        mRecyclerView.adapter =feedViewAdapter

        setRefreshEvent()
    }

    private fun setRefreshEvent() {
        mRefreshLayout.setColorSchemeColors(Color.BLUE)
        mRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
//                        为什么这里要setRefresh false
                        mRefreshLayout.isRefreshing = false
                    }
                }.start()
            }
        })
    }
}