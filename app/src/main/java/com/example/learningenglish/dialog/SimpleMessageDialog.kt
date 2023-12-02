package com.example.learningenglish.dialog

import android.view.View
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseDialog
import com.example.learningenglish.databinding.DialogSimpleMessageBinding

class SimpleMessageDialog(var message: String, var onClick: () -> Unit = {}): BaseDialog<DialogSimpleMessageBinding>() {
    companion object {
        internal val TAG = SimpleMessageDialog::class.java.name
    }

    override fun initAfterViewCreated(rootView: View) {
        binding.btConfirm.setOnClickListener {
            dismiss()
            onClick.invoke()
        }
        binding.tvContent.text = message
    }

    override fun getLayoutId(): Int = R.layout.dialog_simple_message

    override fun marginHorizon(): Float = 24f


}