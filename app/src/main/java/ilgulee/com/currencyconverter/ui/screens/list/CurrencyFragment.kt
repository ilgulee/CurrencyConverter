package ilgulee.com.currencyconverter.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ilgulee.com.currencyconverter.R
import ilgulee.com.currencyconverter.databinding.CurrencyFragmentBinding


class CurrencyFragment : Fragment() {

    private val viewModel: CurrencyViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(
            this,
            CurrencyViewModel.Factory(
                activity.application
            )
        )
            .get(CurrencyViewModel::class.java)
    }

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = CurrencyAdapter(CurrencyListener {
            Toast.makeText(context, "You changed source standard.", Toast.LENGTH_LONG).show()
            viewModel.changeSourceByClick(it)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.originalCurrencyList.observe(this, Observer {
            it?.let {
                viewModel.setDataInsideViewModel(it)
            }
        })

        viewModel.adapterData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.input.observe(this, Observer {
            it?.let {
                if (!it.isBlank() && it.toDouble() > 0.0) {
                    viewModel.calculateCurrenciesList(it.toDouble())
                }
            }
        })

        return binding.root
    }

}
