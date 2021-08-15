package adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class CommonAdapter(
    fm: FragmentManager, private var mItem: ArrayList<Fragment>,
    private var mTitle: Array<String>
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "wzr->getItem: $position")
        return mItem[position]
    }

    override fun getCount(): Int {
        Log.d(TAG, "wzr->getCount: ${mItem.size}")
        return mItem.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.d(TAG, "wzr->getPageTitle: ${mTitle[position]}")
        return mTitle[position]
    }

    companion object {
        private const val TAG = "wzr->CommonAdapter"
    }
}