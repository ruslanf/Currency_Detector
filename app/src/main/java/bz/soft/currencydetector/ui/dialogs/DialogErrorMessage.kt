package bz.soft.currencydetector.ui.dialogs

import android.view.View
import bz.soft.currencydetector.R
import bz.soft.currencydetector.databinding.DialogErrorMessageBinding
import bz.soft.currencydetector.ui.base.BaseDialogFragment

class DialogErrorMessage(
    private val title: String,
    private val message: String
) : BaseDialogFragment(R.layout.dialog_error_message) {

    override val blackoutBackground: Boolean = true
    override val dialogPositionBottom: Boolean = true

    override fun setupUI(v: View) {
        DialogErrorMessageBinding.bind(v).apply {
            titleTV.text = title
            messageTV.text = message
            okButtonTV.setOnClickListener {
                dialog?.cancel()
            }
        }
    }
}