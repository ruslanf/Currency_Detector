package bz.soft.currencydetector.ui.favourite

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import bz.soft.currencydetector.BuildConfig
import bz.soft.currencydetector.R
import bz.soft.currencydetector.data.adapters.FCurrenciesElement
import bz.soft.currencydetector.data.adapters.FCurrenciesItemDelegate
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.databinding.FragmentFavouriteBinding
import bz.soft.currencydetector.root.Constants.DIALOG_MENU_TAG
import bz.soft.currencydetector.root.delegated.DelegateAdapter
import bz.soft.currencydetector.root.extensions.setRecyclerView
import bz.soft.currencydetector.root.extensions.showProgressBar
import bz.soft.currencydetector.ui.base.BaseFragment
import bz.soft.currencydetector.ui.base.Inflate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    private val viewModel by inject<FavouriteVM>()

    private val favouriteAdapter = DelegateAdapter(FCurrenciesItemDelegate { favourite ->
        viewModel.setFavourite(favourite, !favourite.isFavourite)
    })

    override val inflate: Inflate<FragmentFavouriteBinding> = FragmentFavouriteBinding::inflate

    override fun initUI(v: View) {
        binding?.apply {
            swipeRefreshFavourite.setOnRefreshListener { refreshListener() }

            favouriteRV.setRecyclerView(root, favouriteAdapter)

            lifecycleScope.launch {
                launch { viewModel.progress.collect { progressBar.showProgressBar(it) } }
                launch {
                    viewModel.errors.collect {
                        renderErrorDialog(it.message ?: getString(R.string.fragment_error_dialog_title))
                        if (BuildConfig.DEBUG) Log.v(TAG, "Errors => $it")
                    }
                }
                launch { viewModel.watchCurrencies.collect { renderFavourites(it) } }
            }
        }
    }

    private fun renderFavourites(favourite: List<Currencies>) {
        favouriteAdapter.apply {
            items.clear()
            items.addAll(
                favourite.map { f ->
                    FCurrenciesElement.FCurrencyItem(f)
                }
            )
            notifyDataSetChanged()
        }
    }

    private fun refreshListener() {
        binding?.apply {
            viewModel.onRefresh()
            swipeRefreshFavourite.isRefreshing = false
        }
    }

    private fun renderErrorDialog(message: String) {
        errorMessage = message
        errorDialogTitle = getString(R.string.fragment_favourite_error_dialog_title)
        errorMessageDialog.show(requireActivity().supportFragmentManager, DIALOG_MENU_TAG)
    }

    companion object {
        private val TAG = FavouriteFragment::class.simpleName
    }
}