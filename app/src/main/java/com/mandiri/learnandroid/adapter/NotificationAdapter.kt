package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.databinding.ItemNotificationBinding
import com.mandiri.learnandroid.model.NotificationModel

class NotificationAdapter(private var notifications: List<NotificationModel>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val binding get() = itemBinding
    }

    fun setNotifications(data: List<NotificationModel>) {
        notifications = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]

        with(holder.binding) {
            tvDate.text = notification.date.toString()
            tvTitleNotif.text = notification.title
            tvContentNotif.text = notification.content
        }
    }

}
