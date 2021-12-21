package bz.soft.currencydetector.di

import bz.soft.currencydetector.ui.favourite.FavouriteVM
import bz.soft.currencydetector.ui.popular.PopularVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel {
        PopularVM(
            repository = get(),
            dbRepository = get()
        )
    }
    viewModel {
        FavouriteVM(
            repository = get(),
            dbRepository = get()
        )
    }
}