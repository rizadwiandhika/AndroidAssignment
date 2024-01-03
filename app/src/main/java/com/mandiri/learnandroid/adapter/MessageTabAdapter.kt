package com.mandiri.learnandroid.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// We build fragment inside the fragment
class MessageTabAdapter(fragment: Fragment) :
	FragmentStateAdapter(fragment.childFragmentManager, fragment.lifecycle) {

	private val fragmentContent = arrayListOf<Fragment>()

	fun addFragment(fragment: Fragment) {
		fragmentContent.add(fragment)
	}

	override fun getItemCount(): Int {
		return fragmentContent.size
	}

	override fun createFragment(position: Int): Fragment {
		return fragmentContent[position]
	}

}
