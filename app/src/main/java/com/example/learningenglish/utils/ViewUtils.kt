package com.example.learningenglish.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.example.learningenglish.dialog.InProgressBlurDialog
import com.example.learningenglish.dialog.SimpleMessageDialog

object ViewUtils {
    @SuppressLint("StaticFieldLeak")
    private var blurDialog: InProgressBlurDialog? = null

    fun showBlur(support: FragmentManager) {
        blurDialog = InProgressBlurDialog()
        blurDialog?.show(support, InProgressBlurDialog.TAG)
    }

    fun dismissBlur() {
        blurDialog?.dismiss()
    }

    fun showMessageDialog(context: Context, support: FragmentManager, @StringRes msg: Int, onCLick: () -> Unit = {}) {
        val str = context.getString(msg)
        val messageDialog = SimpleMessageDialog(str, onCLick)
        messageDialog.show(support, SimpleMessageDialog.TAG)
    }

    fun showMessageDialog(support: FragmentManager, msg: String, onCLick: () -> Unit = {}) {
        val messageDialog = SimpleMessageDialog(msg, onCLick)
        messageDialog.show(support, SimpleMessageDialog.TAG)
    }
}