package bz.soft.currencydetector.ui.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.data.db.entity.CurrencyFavouriteUpdate
import bz.soft.currencydetector.data.http.Left
import bz.soft.currencydetector.data.http.Right
import bz.soft.currencydetector.data.models.CurrencyModel
import bz.soft.currencydetector.data.repository.DataBaseRepository
import bz.soft.currencydetector.data.repository.Repository
import bz.soft.currencydetector.root.Constants.EMPTY_STRING
import bz.soft.currencydetector.root.extensions.serializeToMap
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PopularVM(
    private val repository: Repository,
    private val dbRepository: DataBaseRepository
) : ViewModel() {

    val progress: Flow<Int>
        get() = _progress
    val errors: Flow<Exception>
        get() = _errors

    val watchCurrencies: Flow<List<Currencies>>
        get() = _currencies

    private val _currencies = MutableStateFlow<List<Currencies>>(emptyList())

    private val _errors = MutableSharedFlow<Exception>()
    private val _progress = MutableSharedFlow<Int>()
    private var counter = 0

    init {
        viewModelScope.launch {
            launch {
                dbRepository.watchCurrencies()
                    .distinctUntilChanged()
                    .onEach { if (it.isEmpty()) onFetchRates() }
                    .collect { _currencies.emit(it) }
            }
        }
    }

    /**
     * For SwipeRefresh
     */
    fun onRefresh() {
        viewModelScope.launch { onFetchRates() }
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

    /**
     * Fetch rates from API
     */
    private fun onFetchRates() {
        viewModelScope.launch {
            counter += 1
            _progress.emit(counter)

            when (val r = repository.getRates()) {
                is Left -> _errors.emit(r.value)
                is Right -> doUpdateRates(r.value)
            }

            counter -= 1
            _progress.emit(counter)
        }
    }

    private suspend fun doUpdateRates(model: CurrencyModel) {
        model.rates.serializeToMap().map { currency ->
            Log.v("VM !!", "currency => $currency")
            dbRepository.insertCurrencies(
                Currencies(
                    timestamp = model.timestamp ?: EMPTY_STRING,
                    base = model.base,
                    date = model.date,
                    currencyName = currency.key,
                    currencyValue = currency.value,
                    isFavourite = false
                )
            )
        }
    }
}