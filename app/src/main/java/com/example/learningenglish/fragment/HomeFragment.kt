package com.example.learningenglish.fragment

import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.learningenglish.R
import com.example.learningenglish.adapter.HomePagerAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var homePagerAdapter: HomePagerAdapter
    companion object {
        internal val TAG = HomeFragment::class.java.name
    }

    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        homePagerAdapter = HomePagerAdapter(requireActivity())
        binding.vpHomeGps.adapter = homePagerAdapter
        binding.vpHomeGps.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
        binding.layoutBottomBar.setOnItemSelectedListener { item ->

            true
        }
    }

    override fun getLayoutId() = R.layout.fragment_home
}