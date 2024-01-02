package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mandiri.learnandroid.databinding.ItemMenuHomeBinding
import com.mandiri.learnandroid.model.MenuModel

class MenuHomeAdapter(
    private val listMenu: List<MenuModel>
) : BaseAdapter() {
    override fun getCount(): Int = listMenu.size

    override fun getItem(p0: Int): Any? = null

    override fun getItemId(p0: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var holder: ViewHolderMenu
//        val itemView: View
//
//        if (convertView == null) {
//            val binding =
//                ItemMenuHomeBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
//
////            itemView = binding.root
////            itemView.tag = holder
//
//            holder = ViewHolderMenu(binding)
//            itemView = binding.root
//            itemView.tag = holder
//        } else {
//            holder = convertView.tag as ViewHolderMenu
//            itemView = convertView
//        }
//
//        holder.binding.tvMenuTitle.text = listMenu[position].name
//        return itemView

        var view: View
        var binding: ItemMenuHomeBinding

        if (convertView == null) {
                binding = ItemMenuHomeBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
                view = binding.root
                view.tag = binding
        } else {
            view = convertView
            binding = convertView.tag as ItemMenuHomeBinding
        }

        binding.tvMenuTitle.text = listMenu[position].name
        return view
    }

}