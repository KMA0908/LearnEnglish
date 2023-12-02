package com.example.learningenglish.dialog

import android.view.View
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseDialog
import com.example.learningenglish.databinding.DialogProgressBlurBinding

class InProgressBlurDialog: BaseDialog<DialogProgressBlurBinding>() {
    companion object {
        internal val TAG = InProgressBlurDialog::class.java.name
    }

    override fun initAfterViewCreated(rootView: View) {

    }

    override fun getLayoutId(): Int = R.layout.dialog_progress_blur

    override fun marginHorizon(): Float = 0f

    override fun initViews() {
        dialog?.setCanceledOnTouchOutside(false)
    }
}