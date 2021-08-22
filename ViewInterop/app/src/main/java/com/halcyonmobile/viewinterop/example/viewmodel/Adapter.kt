package com.halcyonmobile.viewinterop.example.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return PageFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_INDEX, position + 1)
            }
        }
    }
}
