package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.mandiri.learnandroid.adapter.NotificationAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.databinding.FragmentNotificationBinding
import com.mandiri.learnandroid.presentation.viewmodel.NotificationViewModel
import com.mandiri.learnandroid.utils.Status


class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {

    private var _notificationAdapter: NotificationAdapter? = null

    private val notificationAdapter get() = _notificationAdapter!!

    private val notificationViewModel: NotificationViewModel by viewModels()


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
        _notificationAdapter = NotificationAdapter(listOf())
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayout.VERTICAL
        )

        binding.rvNotification.adapter = notificationAdapter
        binding.rvNotification.addItemDecoration(dividerItemDecoration)

        notificationViewModel.fetchNotification()
        notificationViewModel.notificationData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvMessage.visibility = View.GONE
                    binding.rvNotification.visibility = View.VISIBLE
                    notificationAdapter.setNotifications(it.data!!)
                }

                Status.LOADING -> {
                    binding.tvMessage.apply {
                        text = "Loading..."
                        visibility = View.VISIBLE
                    }
                    binding.rvNotification.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.tvMessage.apply {
                        text = it.error?.message
                        visibility = View.VISIBLE
                    }
                    binding.rvNotification.visibility = View.GONE
                }
            }
        }
    }

}
