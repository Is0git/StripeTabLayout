package com.android.multistreamtablayout.MultiStreamTabLayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.multistreamtablayout.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = 3
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }
}