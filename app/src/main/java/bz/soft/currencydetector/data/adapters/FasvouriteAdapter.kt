package bz.soft.currencydetector.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bz.soft.currencydetector.R
import bz.soft.currencydetector.data.db.entity.Currencies
import bz.soft.currencydetector.databinding.CellPopularItemBinding
import bz.soft.currencydetector.root.delegated.AdapterDelegateInterface
import bz.soft.currencydetector.root.delegated.BaseHolder
import bz.soft.currencydetector.root.extensions.color

sealed class FCurrenciesElement {
    data class FCurrencyItem(val currency: Currencies) : FCurrenciesElement()
}

class FCurrenciesItemHolder(
    v: View,
    val onClick: (Currencies) -> Unit
) : BaseHolder<FCurrenciesElement>(v) {

    override fun bindModel(item: FCurrenciesElement) {
        super.bindModel(item)
        val binding = CellPopularItemBinding.bind(itemView)
        when (item) {
            is FCurrenciesElement.FCurrencyItem -> itemView.apply {
                binding.apply {
                    currencyTitleTV.text = item.currency.currencyName
                    currencyValueTV.text = item.currency.currencyValue.toString()
                    favouriteIV.setColorFilter(
                        context.color(if (item.currency.isFavourite) R.color.bzRed else R.color.bzGrey)
                    )

                    setOnClickListener { onClick(item.currency) }
                }
            }
        }
    }
}

class FCurrenciesItemDelegate(
    private val onClick: (Currencies) -> Unit
) : AdapterDelegateInterface<FCurrenciesElement> {

    override fun isForViewType(items: List<FCurrenciesElement>, position: Int): Boolean =
        items[position] is FCurrenciesElement.FCurrencyItem

    override fun createViewHolder(parent: ViewGroup): BaseHolder<FCurrenciesElement> =
        FCurrenciesItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_popular_item, parent, false),
            onClick
        )

    override fun bindViewHolder(
        items: List<FCurrenciesElement>,
        position: Int,
        holder: BaseHolder<FCurrenciesElement>
    ) {
        holder.bindModel(items[position])
    }
}
