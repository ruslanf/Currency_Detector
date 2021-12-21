package bz.soft.currencydetector.ui.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import bz.soft.currencydetector.R

abstract class BaseDialogFragment(@LayoutRes layout: Int) : DialogFragment(layout) {

    abstract val blackoutBackground: Boolean
    abstract val dialogPositionBottom: Boolean

    override fun onStart() {
        super.onStart()
        dialog?.window?.also { window ->
            if (dialogPositionBottom) window.setGravity(Gravity.BOTTOM)
            window.attributes?.also { attributes ->
                attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT
                // Если нужно убрать затемнение
                if (!blackoutBackground) attributes.dimAmount = 0f
                window.attributes = attributes
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_parent_background)
        setupUI(view)
    }

    abstract fun setupUI(v: View)
}