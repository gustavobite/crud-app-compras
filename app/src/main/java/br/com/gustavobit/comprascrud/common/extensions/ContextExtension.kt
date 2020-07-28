package br.com.gustavobit.comprascrud.common.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import br.com.gustavobit.comprascrud.R

fun Context.showDialog(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes positiveButton: Int,
    @StringRes negativeButton: Int? = null,
    onPositiveButtonClick: (() -> Unit)? = null,
    onNegativeButtonClick: (() -> Unit)? = null
) {
    showDialog(
        title = getString(title),
        message = getString(message),
        positiveButton = getString(positiveButton),
        negativeButton = if (negativeButton != null) getString(negativeButton) else "",
        onPositiveButtonClick = { onPositiveButtonClick?.invoke() },
        onNegativeButtonClick = { onNegativeButtonClick?.invoke() }
    )
}


fun Context.showDialog(
    title: String,
    message: String,
    positiveButton: String,
    negativeButton: String? = null,
    onPositiveButtonClick: (() -> Unit)? = null,
    onNegativeButtonClick: (() -> Unit)? = null
) {
    val dialogBuilder =
        MaterialAlertDialogBuilder(this, R.style.Theme_MaterialComponents_Dialog_Alert)
    dialogBuilder.apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(positiveButton) { dialog, _ ->
            dialog.dismiss()
            onPositiveButtonClick?.invoke()
        }
        setNegativeButton(negativeButton) { dialog, _ ->
            dialog.dismiss()
            onNegativeButtonClick?.invoke()
        }
    }
    dialogBuilder.show()
}
