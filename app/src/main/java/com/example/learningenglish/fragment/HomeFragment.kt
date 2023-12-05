package com.example.learningenglish.fragment

import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.learningenglish.R
import com.example.learningenglish.adapter.HomePagerAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentHomeBinding
import com.example.learningenglish.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var homePagerAdapter: HomePagerAdapter
    private val viewModel: HomeViewModel by sharedViewModel()

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
                when (position) {
                    0 -> binding.layoutBottomBar.menu.findItem(R.id.menu_folder).isChecked = true
                    1 -> binding.layoutBottomBar.menu.findItem(R.id.menu_topic).isChecked = true
                    2 -> binding.layoutBottomBar.menu.findItem(R.id.menu_public).isChecked = true
                    3 -> binding.layoutBottomBar.menu.findItem(R.id.menu_profile).isChecked = true
                }
            }
        })

        binding.layoutBottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_folder -> binding.vpHomeGps.currentItem = 0
                R.id.menu_topic -> binding.vpHomeGps.currentItem = 1
                R.id.menu_public -> binding.vpHomeGps.currentItem = 2
                R.id.menu_profile -> binding.vpHomeGps.currentItem = 3
            }
            true
        }
    }

    override fun getLayoutId() = R.layout.fragment_home
}