package bz.soft.currencydetector.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import bz.soft.currencydetector.R
import bz.soft.currencydetector.root.Constants.EMPTY_STRING
import bz.soft.currencydetector.ui.dialogs.DialogErrorMessage
import bz.soft.currencydetector.ui.main.MainActivity

abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    abstract val inflate: Inflate<VB>

    protected val errorMessageDialog by lazy {
        DialogErrorMessage(
            title = errorDialogTitle,
            message = errorMessage
        )
    }
    protected var errorMessage: String = EMPTY_STRING
    protected var errorDialogTitle: String = EMPTY_STRING

    protected var binding: VB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorDialogTitle = getString(R.string.error_dialog_title)
        initUI(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun initUI(v: View)

    /**
     * Navigation
     */
    fun navigateUp() {
        navigateController()?.navigateUp()
    }

    fun navigateTo(@IdRes id: Int) {
        if (id != 0) navigateController()?.navigate(id)
    }

    fun navigateTo(@IdRes id: Int, bundle: Bundle) {
        if (id != 0) navigateController()?.navigate(id, bundle)
    }

    fun navigateToPopUp(@IdRes id: Int, bundle: Bundle) {
        if (id != 0) navigateController()?.navigate(
            id,
            bundle,
            NavOptions.Builder().setPopUpTo(id, true).build()
        )
    }

    fun navigateBackTo(@IdRes id: Int, inclusive: Boolean) {
        if (id != 0) navigateController()?.popBackStack(id, inclusive)
    }

    private fun currentDestination(): Int = navigateController()?.currentDestination?.id ?: 0

    private fun navigateController(): NavController? = activity?.let { fa ->
        return@let if (fa is MainActivity) Navigation.findNavController(fa, R.id.nav_host_fragment)
        else view?.findNavController()
    }
}