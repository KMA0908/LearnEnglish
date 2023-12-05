package com.example.learningenglish.fragment

import androidx.lifecycle.ViewModel
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentStudyBinding


class StudyFragment : BaseFragment<FragmentStudyBinding>() {

    companion object {
        internal val TAG = StudyFragment::class.java.name
    }

    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }


    override fun initViews() {

    }

    override fun getLayoutId() = R.layout.fragment_study
}