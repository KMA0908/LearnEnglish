package com.example.learningenglish.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learningenglish.fragment.FolderFragment
import com.example.learningenglish.fragment.HomeFragment
import com.example.learningenglish.fragment.ProfileFragment
import com.example.learningenglish.fragment.StudyFragment

class HomePagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        Log.d("HomePagerAdapter", "createFragment called with position: $position")
        when (position) {
            0 -> return FolderFragment()
            1 -> return StudyFragment()
            2 -> return ProfileFragment()
        }
        return FolderFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }
}