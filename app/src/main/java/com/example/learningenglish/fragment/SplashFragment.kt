package com.example.learningenglish.fragment

import android.os.Handler
import android.os.Looper
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentSplashBinding
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    companion object {
        internal val TAG = SplashFragment::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {
        viewModel.stateSplash.observe(viewLifecycleOwner){
            if(it){
                Handler(Looper.getMainLooper()).postDelayed({
                    gotoMainScreen()
                }, 2000)
            }
        }
    }

    private fun gotoMainScreen() {
        callBack?.showFrg(TAG, HomeFragment.TAG, false)
    }

    override fun getLayoutId() = R.layout.fragment_splash
}