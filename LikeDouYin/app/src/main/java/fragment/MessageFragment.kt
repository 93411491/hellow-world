package fragment

import android.util.Log
import com.example.likedouyin.R

class MessageFragment : BaseFragment(){
    override fun setContentView(): Int {
        return R.layout.fragment_message
    }

    override fun init() {
        Log.d(TAG, "wzr->init:MessageFragment ")
    }

    companion object {
        private const val TAG = "MessageFragment"
    }
}