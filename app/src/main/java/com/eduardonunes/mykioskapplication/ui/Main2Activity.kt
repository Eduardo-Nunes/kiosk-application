package com.eduardonunes.mykioskapplication.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eduardonunes.mykioskapplication.R
import com.eduardonunes.mykioskapplication.extensions.setWindowsImmersiveFocus
import org.jetbrains.anko.intentFor

class Main2Activity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) {
            context.startActivity(
                context.intentFor<Main2Activity>()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}
