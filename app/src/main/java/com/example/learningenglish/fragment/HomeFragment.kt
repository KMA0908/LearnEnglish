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

    override fun getVM(): ViewModel = viewModel

    override fun initViews() {
        val layoutBottomBar = binding.layoutBottomBar
        homePagerAdapter = HomePagerAdapter(requireActivity())
        binding.vpHomeGps.adapter = homePagerAdapter
        binding.vpHomeGps.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


                when (position) {
                    0 -> layoutBottomBar.menu.findItem(R.id.folderFragment).isChecked = true
                    1 -> layoutBottomBar.menu.findItem(R.id.studyFragment).isChecked = true
                    2 -> layoutBottomBar.menu.findItem(R.id.profileFragment).isChecked = true
                }
                viewModel.currentPageIndex = position
            }
        })



        layoutBottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.folderFragment -> binding.vpHomeGps.currentItem = 0
                R.id.studyFragment -> binding.vpHomeGps.currentItem = 1
                R.id.profileFragment -> binding.vpHomeGps.currentItem = 2
            }
            true
        }
        binding.vpHomeGps.setCurrentItem(viewModel.currentPageIndex, false)

        viewModel.enableViewPager =
            {
                binding.vpHomeGps.isUserInputEnabled = true
            }
    }
    override fun getLayoutId() = R.layout.fragment_home
}