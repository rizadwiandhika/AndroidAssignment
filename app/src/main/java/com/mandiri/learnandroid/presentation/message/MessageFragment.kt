package com.mandiri.learnandroid.presentation.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mandiri.learnandroid.adapter.MessageTabAdapter
import com.mandiri.learnandroid.databinding.FragmentMessageBinding
import com.mandiri.learnandroid.presentation.message.view.HistoryTransactionFragment
import com.mandiri.learnandroid.presentation.message.view.NotificationFragment

class MessageFragment(private val fragmentReplacer: (Fragment) -> Unit) : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private var _adapterMessage: MessageTabAdapter? = null

    private val binding get() = _binding!!
    private val adapterMessage get() = _adapterMessage!!

//	override fun onCreateView(
//		inflater: LayoutInflater,
//		container: ViewGroup?,
//		savedInstanceState: Bundle?
//	): View? {
//		return super.onCreateView(inflater, container, savedInstanceState)
//	}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabView()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // To avoid memory leaks, nullify the binding object when destroy
        _binding = null
    }

    private fun setupTabView() {
        val tabLayout = binding.tabMessage
        val viewPager = binding.vpMessage
//        tabLayout.visibility = View.GONE
        _adapterMessage = MessageTabAdapter(this)

        adapterMessage.addFragment(NotificationFragment())
        adapterMessage.addFragment(HistoryTransactionFragment(fragmentReplacer))

        viewPager.adapter = adapterMessage
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Notification"
                }

                1 -> {
                    tab.text = "History"
                }
            }
        }.attach()
        viewPager.setCurrentItem(1, false)
    }
}
