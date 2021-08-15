package fragment

import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.likedouyin.R
import com.google.android.material.tabs.TabLayout

/*
* 主fragment
* */

class MainFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.fragment_main
    }

    private var mFragmentContainerContent: Int = 0

    private lateinit var mMainTabLayout: TabLayout
    private var mMainTabFragmentList: ArrayList<Fragment?> = ArrayList(4)
    private var mHomeFragment: Fragment? = null
    private var mFriendFragment: Fragment? = null
    private var mMessageFragment: Fragment? = null
    private var mMyselfFragment: Fragment? = null

    private var mCurrentTitleTabSelect = 1

    override fun init() {
        Log.d(TAG, "wzr->init: MainFragment")

        with(view!!) {
            mMainTabLayout = findViewById(R.id.main_tab_title)
        }

        mMainTabFragmentList.let {
            it.add(mHomeFragment)
            it.add(mFriendFragment)
            it.add(mMessageFragment)
            it.add(mMyselfFragment)
        }

        setCurrentFragment(0)
        setMainMenuTab()
    }

    private fun setCurrentFragment(position: Int) {
        mFragmentContainerContent = position

        val transaction = childFragmentManager.beginTransaction()
        if (mMainTabFragmentList[position] == null) {
            when (position) {
                0 -> mMainTabFragmentList[position] = HomeFragment()
                1 -> mMainTabFragmentList[position] = FriendFragment()
                2 -> mMainTabFragmentList[position] = MessageFragment()
                3 -> mMainTabFragmentList[position] = MyselfFragment()
            }
            transaction.add(R.id.fragment_container, mMainTabFragmentList[position]!!)
                .addToBackStack(null)
        }

        hideFragment(transaction)
        showFragment(transaction, position)

        transaction.commitAllowingStateLoss()
    }

    private fun showFragment(transaction: FragmentTransaction, position: Int) {
        transaction.show(mMainTabFragmentList[position]!!)
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        for (index in mMainTabFragmentList) {
            index?.let { transaction.hide(it) }
        }
    }

    private fun setMainMenuTab() {
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText("首页"))
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText("发现"))

        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(""))//给加号图片留出位置

        mMainTabLayout.addTab(mMainTabLayout.newTab().setText("收件箱"))
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText("我"))

        mMainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "wzr->onTabSelected: ${tab!!.position}")
                when (val position = tab.position) {
                    0 -> {
                        if (!judgeContentSame(position)) {
                            setCurrentFragment(0)
                        }
                    }
                    1 -> {
                        if (!judgeContentSame(position)) {
                            setCurrentFragment(1)
                        }
                    }
                    2 -> {
                        if (!judgeContentSame(position)) {
                            setCurrentFragment(2)
                        }
                    }
                    3 -> {
                        if (!judgeContentSame(position)) {
                            setCurrentFragment(3)
                        }
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun judgeContentSame(position: Int): Boolean {
        Log.d(
            TAG, "wzr->judgeContentSame: " +
                    "mFragmentContainerContent=$mFragmentContainerContent" +
                    ", position=$position"
        )

        if (mFragmentContainerContent == position) {
            return true
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mCurrentTitleTabSelect = 1;
        mMainTabFragmentList.removeAll(mMainTabFragmentList)
    }

    companion object {
        private const val TAG = "wzr->MainFragment"
    }
}