package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.R
import com.mandiri.learnandroid.databinding.ItemHistoryTransactionBinding
import com.mandiri.learnandroid.model.HistoryTransactionModel
import com.mandiri.learnandroid.model.StatusTransaction.FAILED
import com.mandiri.learnandroid.model.StatusTransaction.PENDING
import com.mandiri.learnandroid.model.StatusTransaction.SUCCESS

class HistoryTransactionAdapter(private val data: List<HistoryTransactionModel>) :
    RecyclerView.Adapter<HistoryTransactionAdapter.ViewHolder>() {

    private lateinit var onClickHandler: (HistoryTransactionModel) -> Unit

    fun setOnClickHandler(handler: (HistoryTransactionModel) -> Unit) {
        onClickHandler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding()
        val transaction = data[position]

        with(binding) {
            when (transaction.title) {
                "QR Payment" -> {
                    ivIcon.setImageResource(R.drawable.ic_qr)
                }

                "Transfer" -> {
                    ivIcon.setImageResource(R.drawable.ic_logout)
                }
            }


            container.setOnClickListener {
                onClickHandler.invoke(transaction)
            }

            tvDate.text = transaction.date
            tvTitle.text = transaction.title
            tvDescription.text = transaction.description
            tvAmount.text = transaction.amount

            when (transaction.status) {
                SUCCESS.value -> {
                    tvStatus.text = "Success"
                    tvStatus.setTextColor(root.context.getColor(R.color.green))
                }

                PENDING.value -> {
                    tvStatus.text = "Pending"
                    tvStatus.setTextColor(root.context.getColor(R.color.yellow))
                }

                FAILED.value -> {
                    tvStatus.text = "Failed"
                    tvStatus.setTextColor(root.context.getColor(R.color.red))
                }
            }


        }
    }

    inner class ViewHolder(private val binding: ItemHistoryTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBinding() = binding
    }


}