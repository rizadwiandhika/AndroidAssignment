package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.databinding.ItemEwalletCardBinding
import com.mandiri.learnandroid.model.EWalletModel

class EWalletAdapter : RecyclerView.Adapter<EWalletAdapter.ViewHolder>() {

    private var listEWallet: MutableList<EWalletModel> = mutableListOf()
    private var onButtonClick: (EWalletModel) -> Unit = { (w) ->  }

    fun setDataEWallet(data: MutableList<EWalletModel>) {
        listEWallet = data
        notifyDataSetChanged()
    }

    fun setOnButtonClick(cb: (EWalletModel) -> Unit) {
        onButtonClick = cb
    }

    override fun getItemCount(): Int {
        return listEWallet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEwalletCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallet = listEWallet[position]
        holder.bind(wallet, onButtonClick)
    }

    inner class ViewHolder(private val itemBinding: ItemEwalletCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(eWallet: EWalletModel, onClickEWallet: (EWalletModel) -> Unit) {
            itemBinding.ivEWallet.setImageResource(eWallet.image)

            if (eWallet.isConnected) {
                itemBinding.tvConnect.text = eWallet.balance.toString()
            } else {
                itemBinding.tvConnect.text = "+ Connect"
            }

            itemBinding.layoutConnect.setOnClickListener {
                onButtonClick.invoke(eWallet)
            }
        }
    }

}