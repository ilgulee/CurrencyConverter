package ilgulee.com.currencyconverter.ui.screens.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ilgulee.com.currencyconverter.databinding.ListItemBinding
import ilgulee.com.currencyconverter.domain.Currency

class CurrencyAdapter(val currencyListener: CurrencyListener) :
    ListAdapter<Currency, CurrencyAdapter.ViewHolder>(CurrencyDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, currencyListener)
    }

    class ViewHolder private constructor(var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Currency,
            currencyListener: CurrencyListener
        ) {
            binding.currency = item
            binding.clickListener = currencyListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CurrencyDiffUtilCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}

class CurrencyListener(val clickListener: (currency: Currency) -> Unit) {
    fun onClick(currency: Currency) = clickListener(currency)
}