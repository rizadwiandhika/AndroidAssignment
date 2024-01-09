package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.mandiri.learnandroid.adapter.NotificationAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.databinding.FragmentNotificationBinding
import com.mandiri.learnandroid.model.NotificationModel
import java.util.Date


class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {

    private var _notificationAdapter: NotificationAdapter? = null

    private val notificationAdapter get() = _notificationAdapter!!


    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        renderNotificationList()
    }

    private fun renderNotificationList() {
        _notificationAdapter = NotificationAdapter(getNotificationData())
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayout.VERTICAL
        )


        binding.rvNotification.adapter = notificationAdapter
        binding.rvNotification.addItemDecoration(dividerItemDecoration)
    }

    private fun getNotificationData(): List<NotificationModel> {
        return listOf(
            NotificationModel(
                Date(2018, 1, 12),
                "Special offer for you!",
                "Here goes some very long content of the notification, Here goes some very long content of the notification......"
            ),
            NotificationModel(
                Date(2019, 2, 12),
                "Special offer for you!",
                "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
            ),
            NotificationModel(
                Date(2020, 3, 12),
                "Special offer for you!",
                "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
            ),
            NotificationModel(
                Date(2021, 4, 12),
                "Special offer for you!",
                "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
            ),
            NotificationModel(
                Date(2022, 5, 12),
                "Special offer for you!",
                "\"Here goes some very long content of the notification, Here goes some very long content of the notification......"
            ),
        )
    }
}
