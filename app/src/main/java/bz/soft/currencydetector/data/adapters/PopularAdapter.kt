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

sealed class PCurrenciesElement {
    data class PCurrencyItem(val currency: Currencies) : PCurrenciesElement()
}

class PCurrenciesItemHolder(
    v: View,
    val onClick: (Currencies) -> Unit
) : BaseHolder<PCurrenciesElement>(v) {

    override fun bindModel(item: PCurrenciesElement) {
        super.bindModel(item)
        val binding = CellPopularItemBinding.bind(itemView)
        when (item) {
            is PCurrenciesElement.PCurrencyItem -> itemView.apply {
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

class PCurrenciesItemDelegate(
    private val onClick: (Currencies) -> Unit
) : AdapterDelegateInterface<PCurrenciesElement> {

    override fun isForViewType(items: List<PCurrenciesElement>, position: Int): Boolean =
        items[position] is PCurrenciesElement.PCurrencyItem

    override fun createViewHolder(parent: ViewGroup): BaseHolder<PCurrenciesElement> =
        PCurrenciesItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_popular_item, parent, false),
            onClick
        )

    override fun bindViewHolder(
        items: List<PCurrenciesElement>,
        position: Int,
        holder: BaseHolder<PCurrenciesElement>
    ) {
        holder.bindModel(items[position])
    }
}
