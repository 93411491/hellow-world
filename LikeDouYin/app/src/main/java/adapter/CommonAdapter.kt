package adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class CommonAdapter(
    fm: FragmentManager, private var mItem: ArrayList<Fragment>,
    private var mTitle: Array<String>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mItem[position]
    }

    override fun getCount(): Int {
        return mItem.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitle[position]
    }
}