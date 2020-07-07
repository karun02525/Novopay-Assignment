package com.novopay.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.novopay.model.Sources
import com.novopay.ui.DynamicFragment

@SuppressLint("WrongConstant")
class PlansPagerAdapter(fm: FragmentManager?, var mNumOfTabs: Int, private val items: ArrayList<Sources>) :
    FragmentStatePagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = DynamicFragment.newInstance(items[position])

    override fun getCount(): Int =mNumOfTabs

}