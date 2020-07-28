package br.com.gustavobit.comprascrud.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavobit.comprascrud.common.State
import br.com.gustavobit.comprascrud.domain.usecases.BuscaCompra
import br.com.gustavobit.comprascrud.domain.usecases.SalvarCompra
import br.com.gustavobit.comprascrud.ui.mappers.toModel
import br.com.gustavobit.comprascrud.ui.mappers.toViewData
import kotlinx.coroutines.launch

class CompraViewModel(
    private val buscaCompra: BuscaCompra,
    private val salvarCompra: SalvarCompra
) : ViewModel() {

    private var _compras: ComprasViewData =
        ComprasViewData(
            id = null,
            nome = "",
            quantidade = 0,
            marca = "",
            validade = ""
        )
    val compras: ComprasViewData get() = _compras

    private val _fetchShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val fetchShoppingState: LiveData<State<Unit>> get() = _fetchShoppingState

    private val _saveShoppingState: MutableLiveData<State<Unit>> by lazy {
        MutableLiveData<State<Unit>>()
    }
    val saveShoppingState: LiveData<State<Unit>> get() = _saveShoppingState

    fun saveShopping(compras: ComprasViewData) {
        viewModelScope.launch {
            _saveShoppingState.value = State.Loading
            salvarCompra.invoke(compra = compras.toModel()).fold(
                failed = {
                    _saveShoppingState.value = State.Error
                },
                succeeded = { _ ->
                    _saveShoppingState.value = State.Success(Unit)
                }
            )
        }
    }

    fun fetchShopping(id: String) {
        viewModelScope.launch {
            _fetchShoppingState.value = State.Loading
            buscaCompra.invoke(id = id).fold(
                failed = {
                    _fetchShoppingState.value = State.Error
                },
                succeeded = { response ->
                    response?.let {
                        _compras = it.toViewData()
                    }
                    _fetchShoppingState.value = State.Success(Unit)
                }
            )
        }
    }
}
