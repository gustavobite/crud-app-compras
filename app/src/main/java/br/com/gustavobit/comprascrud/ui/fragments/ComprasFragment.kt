package br.com.gustavobit.comprascrud.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.gustavobit.comprascrud.R
import br.com.gustavobit.comprascrud.common.State
import br.com.gustavobit.comprascrud.databinding.ShoppingFragmentBinding
import br.com.gustavobit.comprascrud.ui.viewModels.CompraViewModel
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasViewData
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComprasFragment : Fragment() {
    private val args: ComprasFragmentArgs by navArgs()

    private val viewModel: CompraViewModel by viewModel()
    private lateinit var binding: ShoppingFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.id?.let { viewModel.fetchShopping(id = it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoppingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showOrHideId(!viewModel.compras.id.isNullOrEmpty())

        binding.buttonSave.setOnClickListener {
            viewModel.saveShopping(
                compras =
                ComprasViewData(
                    id = args.id,
                    nome = binding.editName.text.toString(),
                    quantidade = binding.editAmount.text.toString().toInt(),
                    marca = binding.editBrand.text.toString(),
                    validade = binding.editShelfLife.text.toString()
                )
            )
        }
    }

    private fun subscribeObservers() {
        viewModel.fetchShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showError()
                is State.Success -> {
                    showLoading(isVisible = false)
                    loadShopping(viewModel.compras)
                }
            }
        })

        viewModel.saveShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showError()
                is State.Success -> {
                    showLoading(isVisible = false)
                    findNavController().navigateUp()
                }
            }
        })
    }

    private fun showError() {
        showLoading(isVisible = false)
        Snackbar
            .make(binding.root, R.string.error_msg_save_shopping, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun loadShopping(compras: ComprasViewData) {
        showOrHideId(!compras.id.isNullOrEmpty())

        binding.editId.setText(compras.id ?: "")
        binding.editName.setText(compras.nome)
        binding.editBrand.setText(compras.marca)
        binding.editAmount.setText(compras.quantidade.toString())
        binding.editShelfLife.setText(compras.validade)
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showOrHideId(isVisible: Boolean) {
        binding.textInputLayoutId.isVisible = isVisible
    }

}
