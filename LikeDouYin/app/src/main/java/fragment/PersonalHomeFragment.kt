package fragment

import android.util.Log
import com.example.likedouyin.R

/*
* 私人页fragment
*
* */

class PersonalHomeFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_personal_home
    }

    override fun init() {
        Log.d(TAG, "wzr->init: PersonalHomeFragment")
    }

    companion object {
        private const val TAG = "PersonalHomeFragment"
    }
}