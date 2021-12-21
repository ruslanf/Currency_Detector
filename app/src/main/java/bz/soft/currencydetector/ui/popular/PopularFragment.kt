package bz.soft.currencydetector.ui.popular

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import bz.soft.currencydetector.BuildConfig
import bz.soft.currencydetector.R
import bz.soft.currencydetector.data.adapters.PCurrenciesElement
import bz.soft.currencydetector.data.adapters.PCurrenciesItemDelegate
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.databinding.FragmentPopularBinding
import bz.soft.currencydetector.root.Constants.DIALOG_MENU_TAG
import bz.soft.currencydetector.root.delegated.DelegateAdapter
import bz.soft.currencydetector.root.extensions.setRecyclerView
import bz.soft.currencydetector.root.extensions.showProgressBar
import bz.soft.currencydetector.ui.base.BaseFragment
import bz.soft.currencydetector.ui.base.Inflate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PopularFragment : BaseFragment<FragmentPopularBinding>() {

    private val viewModel by inject<PopularVM>()

    private val popularAdapter = DelegateAdapter(PCurrenciesItemDelegate { popular ->
        viewModel.setFavourite(popular, !popular.isFavourite)
    })

    override val inflate: Inflate<FragmentPopularBinding> = FragmentPopularBinding::inflate

    override fun initUI(v: View) {
        binding?.apply {

            swipeRefreshPopular.setOnRefreshListener { refreshListener() }

            popularRV.setRecyclerView(root, popularAdapter)

            lifecycleScope.launch {
                launch { viewModel.progress.collect { progressBar.showProgressBar(it) } }
                launch {
                    viewModel.errors.collect {
                        renderErrorDialog(it.message ?: getString(R.string.fragment_error_dialog_title))
                        if (BuildConfig.DEBUG) Log.v(TAG, "Errors => $it")
                    }
                }
                launch { viewModel.watchCurrencies.collect { renderPopular(it) } }
            }
        }
    }

    private fun renderPopular(popular: List<Currencies>) {
        popularAdapter.apply {
            items.clear()
            items.addAll(
                popular.map { p ->
                    PCurrenciesElement.PCurrencyItem(p)
                }
            )
            notifyDataSetChanged()
        }
    }

    private fun refreshListener() {
        binding?.apply {
            viewModel.onRefresh()
            swipeRefreshPopular.isRefreshing = false
        }
    }

    private fun renderErrorDialog(message: String) {
        errorMessage = message
        errorDialogTitle = getString(R.string.fragment_popular_error_dialog_title)
        errorMessageDialog.show(requireActivity().supportFragmentManager, DIALOG_MENU_TAG)
    }

    companion object {
        private val TAG = PopularFragment::class.simpleName
    }
}