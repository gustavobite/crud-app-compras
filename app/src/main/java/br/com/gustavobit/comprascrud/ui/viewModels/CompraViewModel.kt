package br.com.gustavobit.comprascrud.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavobit.comprascrud.common.State
import br.com.gustavobit.comprascrud.domain.usecases.GetShopping
import br.com.gustavobit.comprascrud.domain.usecases.SaveShopping
import br.com.gustavobit.comprascrud.ui.mappers.toModel
import br.com.gustavobit.comprascrud.ui.mappers.toViewData
import kotlinx.coroutines.launch

class CompraViewModel(
    private val getShopping: GetShopping,
    private val saveShopping: SaveShopping
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
            saveShopping.invoke(compra = compras.toModel()).fold(
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
            getShopping.invoke(id = id).fold(
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
