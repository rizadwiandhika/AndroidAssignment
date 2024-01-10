package com.mandiri.learnandroid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mandiri.learnandroid.base.BaseFragment
import com.mandiri.learnandroid.databinding.FragmentPromotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding>() {
    override fun setupView(view: View, savedInstanceState: Bundle?) {
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPromotionBinding {
        return FragmentPromotionBinding.inflate(inflater, container, false)
    }


}
