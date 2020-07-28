package br.com.gustavobit.comprascrud.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavobit.comprascrud.common.State
import br.com.gustavobit.comprascrud.domain.usecases.ExcluirCompra
import br.com.gustavobit.comprascrud.domain.usecases.BuscarTodasCompras
import br.com.gustavobit.comprascrud.ui.mappers.toViewData
import kotlinx.coroutines.launch

class ComprasListViewModel(
    private val buscarTodasComprasUseCase: BuscarTodasCompras,
    private val excluirCompra: ExcluirCompra
) : ViewModel() {

    private val _comprasList: MutableList<ComprasViewData> = mutableListOf()
    val comprasList: List<ComprasViewData> get() = _comprasList

    private val _fetchShoppingListState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val fetchShoppingListState: LiveData<State<Unit>> get() = _fetchShoppingListState

    private val _deleteShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val deleteShoppingState: LiveData<State<Unit>> get() = _deleteShoppingState

    fun fetchShopping() {
        viewModelScope.launch {
            _fetchShoppingListState.value = State.Loading
            buscarTodasComprasUseCase.invoke().fold(
                failed = {
                    _fetchShoppingListState.value = State.Error
                },
                succeeded = { response ->
                    _comprasList.run {
                        clear()
                        addAll(response.map { it.toViewData() })
                    }
                    _fetchShoppingListState.value = State.Success(Unit)
                }
            )

        }
    }

    fun deleteShopping(id: String) {
        viewModelScope.launch {
            _deleteShoppingState.value = State.Loading
            excluirCompra.invoke(id = id).fold(
                failed = {
                    _deleteShoppingState.value = State.Error
                },
                succeeded = {
                    _deleteShoppingState.value = State.Success(Unit)
                }
            )

        }
    }
}