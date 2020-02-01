package com.android.multistreamtablayout.MultiStreamTabLayout

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewpager2.widget.ViewPager2
import com.android.multistreamtablayout.R
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StripeTabLayout: ConstraintLayout {
    lateinit var typedArray: TypedArray
    var showStripes: Boolean = true
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    {
        init()
    }

    lateinit var tabLayout: TabLayout

    fun init() {
            typedArray = context.obtainStyledAttributes(R.styleable.StripeTabLayout).apply {
                showStripes = getBoolean(R.styleable.StripeTabLayout_showStripes, true)
                recycle()
            }


            tabLayout = TabLayout(context, null, R.style.tabCustomStyle).apply {
                id = R.id.tabLayout
                tabMode = TabLayout.MODE_FIXED
                tabGravity = TabLayout.GRAVITY_FILL
                this.isInlineLabel = true
                this.isTabIndicatorFullWidth = true
            }

            tabLayout.addTab(tabLayout.newTab().setText("SDwqe"))
            tabLayout.addTab(tabLayout.newTab().setText("SDwqe"))

        setConstraints()
            addView(tabLayout)


    }


    fun setConstraints() {
        val constraintSet = ConstraintSet().apply {
            connect(tabLayout.id, ConstraintSet.BOTTOM, id, ConstraintSet.BOTTOM)
            connect(tabLayout.id, ConstraintSet.END, id, ConstraintSet.END)
        }

        constraintSet.applyTo(this)
    }

    fun setupWithViewPager(viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setCustomView(R.layout.default_tab)
        }
    }
}