package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.databinding.ItemSavingDepositBinding
import com.mandiri.learnandroid.model.SavingDepositModel

class SavingDepositAdapter(private var isBalanceDisplayed: Boolean): RecyclerView.Adapter<SavingDepositAdapter.ViewHolder>() {
    private var listSavingDeposit: MutableList<SavingDepositModel> = mutableListOf()
    private var isShowAll: Boolean = false

    fun setListSavingDeposit(data: MutableList<SavingDepositModel>) {
        listSavingDeposit = data
        notifyDataSetChanged()
    }

    fun setBalanceVisibility(value: Boolean) {
        isBalanceDisplayed = value
        notifyDataSetChanged()
    }

    fun toggleViewAllSaving() {
        isShowAll = !isShowAll
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSavingDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false));
    }

    override fun getItemCount(): Int {
        if (isShowAll) {
            return listSavingDeposit.size
        }
        return MAX_ITEM
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val saving = listSavingDeposit[position]
        holder.bind { itemBinding ->
            itemBinding.tvSavingName.text = saving.savingName
            itemBinding.tvAccountNumber.text = saving.accountNumber
            itemBinding.ivSavingImg.setImageResource(saving.image)

            if (isBalanceDisplayed) {
                itemBinding.tvBalance.visibility = View.VISIBLE
            } else {
                itemBinding.tvBalance.visibility = View.INVISIBLE
            }

        }
    }

    inner class ViewHolder(private val itemBinding: ItemSavingDepositBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(callback: (ItemSavingDepositBinding) -> Unit) {
            callback.invoke(itemBinding)
        }
    }

    companion object {
        const val MAX_ITEM = 2
    }
}