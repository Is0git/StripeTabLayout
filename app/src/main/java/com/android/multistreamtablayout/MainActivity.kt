package com.android.multistreamtablayout

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.android.multistreamtablayout.MultiStreamTabLayout.StripeTabLayout
import com.android.multistreamtablayout.MultiStreamTabLayout.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var stripeTabLayout: StripeTabLayout
    lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stripeTabLayout = findViewById(R.id.stripeTabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
        viewPager2.adapter = ViewPagerAdapter()
        stripeTabLayout.setupWithViewPager(viewPager2) { tab, i ->
            val id = when (i) {
                0 -> R.drawable.youtube
                1 -> R.drawable.twitch
                2 -> R.drawable.mixer
                else -> return@setupWithViewPager
            }

            tab.setCustomView(R.layout.default_tab)
            tab.customView?.findViewById<ImageView>(R.id.icon)?.setImageDrawable(getDrawable(id))
        }
    }
}
