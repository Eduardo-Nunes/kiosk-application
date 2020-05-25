package com.eduardonunes.mykioskapplication.ui

import android.os.Bundle
import com.eduardonunes.mykioskapplication.R
import com.eduardonunes.mykioskapplication.base.KioskActivity
import com.eduardonunes.mykioskapplication.extensions.setAwaysFullScreenMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KioskActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainTextButton.setOnClickListener {
            Main2Activity.createIntent(this)
        }
    }

    override fun onResume() {
        super.onResume()
        setAwaysFullScreenMode()
    }

    override fun onBackPressed() = Unit
}
