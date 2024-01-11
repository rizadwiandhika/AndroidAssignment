package com.mandiri.learnandroid.presentation.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mandiri.learnandroid.adapter.MessageTabAdapter
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.databinding.FragmentMessageBinding
import com.mandiri.learnandroid.presentation.message.view.HistoryTransactionFragment
import com.mandiri.learnandroid.presentation.message.view.NotificationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment(private val fragmentReplacer: (Fragment) -> Unit) :
    BaseFragment<FragmentMessageBinding>() {

    private var _adapterMessage: MessageTabAdapter? = null
    private val adapterMessage get() = _adapterMessage!!


    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupTabView()
    }


    private fun setupTabView() {
        val tabLayout = binding.tabMessage
        val viewPager = binding.vpMessage
        _adapterMessage = MessageTabAdapter(this)

        adapterMessage.addFragment(NotificationFragment())
        adapterMessage.addFragment(HistoryTransactionFragment(fragmentReplacer))

        viewPager.adapter = adapterMessage
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Notification"
                1 -> tab.text = "History"
            }
        }.attach()
        viewPager.setCurrentItem(1, false)
    }
}
