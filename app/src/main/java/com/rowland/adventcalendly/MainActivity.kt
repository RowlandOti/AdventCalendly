package com.rowland.adventcalendly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val args = Bundle()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_frag, CalendarFragment.newInstance(args)).commit()
        }
    }
}
