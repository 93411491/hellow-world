package com.example.likedouyin.activity

import android.content.Intent
import android.os.CountDownTimer
import com.example.likedouyin.R
import com.example.likedouyin.myUtils.Util

class StartActivity : BaseActivity() {
    override fun setLayoutID(): Int {
        return R.layout.activity_start
    }

    override fun init() {
        setFullScreen()

        //定时器，显示2s后启动 DouyinActivity
        object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                Util.showToastTips(this@StartActivity, millisUntilFinished.toString())
            }

            override fun onFinish() {
                startActivity(Intent(this@StartActivity, DouyinActivity::class.java))
                finish()
            }
        }.start()
    }
}