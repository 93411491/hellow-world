package com.example.likedouyin

import android.content.Intent
import android.os.CountDownTimer
import utils.Utils

class StartActivity : BaseActivity() {
    override fun setLayoutID(): Int {
        return R.layout.activity_start
    }

    override fun init() {
        setFullScreen()

        //定时器，显示2s后启动 DouyinActivity
        object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                Utils.showToastTips(this@StartActivity, millisUntilFinished.toString())
            }

            override fun onFinish() {
                startActivity(Intent(this@StartActivity,DouyinActivity::class.java))
                finish()
            }
        }.start()
    }
}