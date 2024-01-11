package com.mandiri.learnandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.databinding.ItemMenuHomeBinding
import com.mandiri.learnandroid.model.MenuModel

class MenuHomeAdapter : RecyclerView.Adapter<MenuHomeAdapter.ViewHolder>() {

    private lateinit var onMenuClick: (ItemMenuHomeBinding) -> Unit
    private var listMenu: MutableList<MenuModel> = mutableListOf()

    inner class ViewHolder(private val binding: ItemMenuHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBinding(): ItemMenuHomeBinding {
            return binding;
        }
    }

    fun setOnMenuClickHandler(handler: (ItemMenuHomeBinding) -> Unit) {
        onMenuClick = handler
    }

    fun setListMenu(menu: MutableList<MenuModel>) {
        listMenu = menu
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val height = (360 until 1080).random()

        val binding = holder.getBinding()
        val menu = listMenu[position]

        with(binding) {
            tvMenuTitle.text = "${menu.name} (h: $height px)"
            ivMenuHome.setImageResource(menu.image)

            clContainer.layoutParams.height = height
            clContainer.requestLayout()

            clContainer.setOnClickListener { onMenuClick.invoke(this) }
        }
    }

}