package com.example.likedouyin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.gyf.immersionbar.ImmersionBar


abstract class BaseActivity : AppCompatActivity() {

    protected var mBind: Unbinder? = null

    companion object {
        private const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutID())

        mBind = ButterKnife.bind(this)

        init()
    }

    fun onClickDy(view: View) {

    }

    fun onClickMp(view: View) {
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        mBind!!.unbind()
    }

    fun setFullScreen() {
        ImmersionBar.with(this).init()
    }

    abstract fun setLayoutID(): Int

    abstract fun init()
}
