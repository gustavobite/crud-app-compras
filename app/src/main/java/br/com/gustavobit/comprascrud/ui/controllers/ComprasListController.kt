package br.com.gustavobit.comprascrud.ui.controllers

import com.airbnb.epoxy.TypedEpoxyController
import br.com.gustavobit.comprascrud.ui.fragments.shoppingViewHolder
import br.com.gustavobit.comprascrud.ui.viewModels.ComprasViewData

class ComprasListController : TypedEpoxyController<List<ComprasViewData>>() {

    private var listener: OnClickListener? = null

    override fun buildModels(data: List<ComprasViewData>?) {
        data?.forEach {
            shoppingViewHolder {
                id(it.id)
                name(it.nome)
                amount(it.quantidade)
                clickListener { _, _, _, _ ->
                    listener?.onClickListener(it)
                }
                deleteListener { _, _, _, _ ->
                    listener?.onDeleteListener(it)
                }
            }
        }
    }

    fun setListener(listener: OnClickListener) {
        this.listener = listener
    }

    interface OnClickListener {
        fun onClickListener(comprasItem: ComprasViewData)
        fun onDeleteListener(comprasItem: ComprasViewData)
    }

}