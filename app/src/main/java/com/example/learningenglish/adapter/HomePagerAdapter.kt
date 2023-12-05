package com.example.learningenglish.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learningenglish.fragment.FolderFragment
import com.example.learningenglish.fragment.ProfileFragment
import com.example.learningenglish.fragment.PublicFragment
import com.example.learningenglish.fragment.TopicFragment

class HomePagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FolderFragment()
            1 -> TopicFragment()
            2 -> PublicFragment()
            3 -> ProfileFragment()
            else -> FolderFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}