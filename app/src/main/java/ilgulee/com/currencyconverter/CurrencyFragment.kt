package ilgulee.com.currencyconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ilgulee.com.currencyconverter.databinding.CurrencyFragmentBinding


class CurrencyFragment : Fragment() {

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<CurrencyFragmentBinding>(
            inflater,
            R.layout.currency_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        return binding.root
    }

}
