package fragment

import android.util.Log
import com.example.likedouyin.R

class FriendFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_friend
    }

    override fun init() {
        Log.d(TAG, "wzr->init: FriendFragment")
    }

    companion object {
        private const val TAG = "FriendFragment"
    }
}