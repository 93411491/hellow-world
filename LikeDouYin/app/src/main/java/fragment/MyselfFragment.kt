package fragment

import android.util.Log
import com.example.likedouyin.R

class MyselfFragment : BaseFragment(){
    override fun setContentView(): Int {
        return R.layout.fragment_my_self
    }

    override fun init() {
        Log.d(TAG, "wzr->init: MyselfFragment")
    }

    companion object {
        private const val TAG = "MyselfFragment"
    }
}