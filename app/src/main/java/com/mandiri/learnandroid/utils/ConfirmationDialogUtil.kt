package com.mandiri.learnandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.databinding.DialogLayoutBinding

class ConfirmationDialogUtil private constructor(private val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ConfirmationDialogUtil? = null

        fun getInstance(context: Context): ConfirmationDialogUtil {
            return instance ?: synchronized(this) {
                instance ?: ConfirmationDialogUtil(context).also {
                    instance = it
                }
            }
        }
    }

    private fun createDialogView(
        title: String,
        text: String,
        buttonText: String,
        onConfirm: OnClickListener,
        onCancel: OnClickListener
    ): View {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        val binding = DialogLayoutBinding.bind(dialogView)

        with(binding) {
            tvTitle.text = title
            tvMessage.text = text
            btnYes.text = buttonText
            btnYes.setOnClickListener(onConfirm)
            btnNo.setOnClickListener(onCancel)
        }

        return dialogView
    }

    private fun createDialog(
        title: String,
        text: String,
        buttonText: String,
        onConfirm: () -> Unit,
        onCancel: (() -> Unit) = { -> }
    ): BottomSheetDialog {
//        val dialog = AlertDialog.Builder(context).create()
        val dialog = BottomSheetDialog(context)
        val dialogView = createDialogView(
            title,
            text,
            buttonText,
            { onConfirm.invoke(); dialog.dismiss() },
            { onCancel.invoke(); dialog.dismiss() },
        )

//        dialog.setView(dialogView)
        dialog.setContentView(dialogView)
        return dialog
    }

    fun show(
        title: String,
        description: String,
        buttonText: String,
        onConfirm: () -> Unit,
        onCancel: () -> Unit
    ) {
        val dialog = createDialog(title, description, buttonText, onConfirm, onCancel)
        dialog.show()
    }

    fun show(title: String, description: String, buttonText: String, onConfirm: () -> Unit) {
        val dialog = createDialog(title, description, buttonText, onConfirm)
        dialog.show()
    }
}
