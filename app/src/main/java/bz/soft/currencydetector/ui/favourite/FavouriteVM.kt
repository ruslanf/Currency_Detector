package bz.soft.currencydetector.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import bz.soft.currencydetector.data.http.Left
import bz.soft.currencydetector.data.http.Right
import bz.soft.currencydetector.data.repository.DataBaseRepository
import bz.soft.currencydetector.data.repository.Repository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavouriteVM(
    private val repository: Repository,
    private val dbRepository: DataBaseRepository
) : ViewModel() {

    val progress: Flow<Int>
        get() = _progress
    val errors: Flow<Exception>
        get() = _errors

    val watchCurrencies: Flow<List<Currencies>>
        get() = _currencies.filterNotNull()

    private val _currencies = MutableSharedFlow<List<Currencies>>()

    private val _errors = MutableSharedFlow<Exception>()
    private val _progress = MutableSharedFlow<Int>()
    private var counter = 0

    init {
        viewModelScope.launch {
            launch {
                dbRepository.watchCurrencies()
                    .distinctUntilChanged()
                    .collect { _currencies.emit(it) }
            }
        }
    }

    /**
     * For SwipeRefresh
     */
    fun onRefresh() {
        viewModelScope.launch {  }
    }

    fun setFavourite(currency: Currencies, isSelected: Boolean) {
        viewModelScope.launch {
            counter += 1
            _progress.emit(counter)

            when (val r = dbRepository.updateFavouriteCurrency(
                CurrencyFavouriteUpdate(
                    timestamp = currency.timestamp,
                    isFavourite = isSelected
                )
            )) {
                is Left -> _errors.emit(r.value)
                is Right -> Unit
            }

            counter -= 1
            _progress.emit(counter)
        }
    }
}