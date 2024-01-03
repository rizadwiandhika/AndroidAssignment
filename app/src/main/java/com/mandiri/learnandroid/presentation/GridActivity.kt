package com.mandiri.learnandroid.presentation

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.learnandroid.R

class GridActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val root = LinearLayout(this)
		root.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
		root.requestLayout()
		root.orientation = LinearLayout.VERTICAL

		val rv = RecyclerView(this)
		rv.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
		rv.requestLayout()
		rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
		rv.adapter = ImageAdapter(this)

		root.addView(rv)


		setContentView(root)
	}

	inner class ImageAdapter(private val context: Context) :
		RecyclerView.Adapter<ImageViewHolder>() {

		private val data =
			listOf<Int>(R.drawable.ic_transfer, R.drawable.ic_transfer, R.drawable.ic_transfer)

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
			val iv = ImageView(context)

			iv.layoutParams = ViewGroup.LayoutParams(360, (240 until 720).random())
			iv.scaleType = ImageView.ScaleType.CENTER_CROP

			return ImageViewHolder(iv)
		}

		override fun getItemCount(): Int {
			return data.size
		}

		override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
			val iv = holder.getImageView()
			val img = data[position]

			iv.setImageResource(img)
		}

	}

	inner class ImageViewHolder(private val itemView: ImageView) :
		RecyclerView.ViewHolder(itemView) {

		fun getImageView(): ImageView = itemView as ImageView
	}

}
