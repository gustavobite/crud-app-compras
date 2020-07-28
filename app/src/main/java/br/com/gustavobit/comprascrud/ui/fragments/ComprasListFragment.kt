package br.com.gustavobit.comprascrud.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.gustavobit.comprascrud.R
import br.com.gustavobit.comprascrud.common.State
import br.com.gustavobit.comprascrud.common.extensions.showDialog
import br.com.gustavobit.comprascrud.databinding.ShoppingListFragmentBinding
import br.com.gustavobit.comprascrud.ui.controllers.ComprasListController
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasListViewModel
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasViewData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComprasListFragment : Fragment(), ComprasListController.OnClickListener {

    private val viewModel: ComprasListViewModel by viewModel()
    private lateinit var binding: ShoppingListFragmentBinding

    private val controller: ComprasListController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.setListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoppingListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.fetchShoppingListState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showLoading(isVisible = false)
                is State.Success -> {
                    showLoading(isVisible = false)
                    loadShopping(viewModel.comprasList)
                }
            }
        })
        viewModel.deleteShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Success -> {
                    viewModel.fetchShopping()
                }
            }
        })
    }

    private fun loadShopping(compras: List<ComprasViewData>) {
        controller.setData(compras)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.fabAddItem.setOnClickListener {
            navigateToShoppingFragment(id = null)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = controller.adapter
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchShopping()
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun onClickListener(comprasItem: ComprasViewData) {
        navigateToShoppingFragment(id = comprasItem.id)
    }

    override fun onDeleteListener(comprasItem: ComprasViewData) {
        comprasItem.id?.let { id ->
            showDeleteDialog {
                viewModel.deleteShopping(id = id)
            }
        }
    }

    private fun navigateToShoppingFragment(id: String?) {
        findNavController().navigate(
            ComprasListFragmentDirections
                .actionShoppingListFragmentToShoppingFragment(id)
        )
    }

    private fun showDeleteDialog(onConfirmed: () -> Unit) {
        requireContext().showDialog(
            title = R.string.title_delete_shopping,
            message = R.string.message_delete_shopping,
            positiveButton = R.string.action_confirm,
            negativeButton = R.string.action_cancel,
            onPositiveButtonClick = {
                onConfirmed.invoke()
            }
        )
    }

}
