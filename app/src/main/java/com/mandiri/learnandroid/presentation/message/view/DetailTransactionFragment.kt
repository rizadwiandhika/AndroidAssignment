package com.mandiri.learnandroid.presentation.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mandiri.learnandroid.databinding.FragmentDetailHistoryTransactionBinding

class DetailTransactionFragment(private val transaction: String) : Fragment() {
    private var _binding: FragmentDetailHistoryTransactionBinding? = null
    private val binding get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHistoryTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
    }

    private fun render() {
        binding.tvTransferDetailLabel.text = transaction
    }
}