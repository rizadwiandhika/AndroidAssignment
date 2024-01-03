package com.mandiri.learnandroid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mandiri.learnandroid.databinding.FragmentHomeBinding
import com.mandiri.learnandroid.databinding.FragmentPromotionBinding

class PromotionFragment : Fragment() {

	private var _binding: FragmentPromotionBinding? = null
	private val binding get() = _binding!!


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentPromotionBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onDestroyView() {
		super.onDestroyView()

		// To avoid memory leaks, nullify the binding object when destroy
		_binding = null
	}
}
