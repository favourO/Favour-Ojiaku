package com.example.favourojiaku.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.favourojiaku.R

class NavAdapter(
    private val items: Array<Int>,
    private val icons: Array<Int>,
    private val onSelect: (strRes: Int) -> Unit
) : RecyclerView.Adapter<NavAdapter.NavHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return NavHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NavHolder, position: Int) {
        holder.bind(items[position], icons[position])
    }

    inner class NavHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val menuIcon = itemView.findViewById<TextView>(R.id.menu_icon)
        private val menuLabel = itemView.findViewById<TextView>(R.id.menu_label)
        fun bind(text: Int, icon: Int) {
            val iconFont =  Typeface.createFromAsset(itemView.context.assets, "fonts/unicons.ttf")
            menuIcon.text = itemView.context.getString(icon)
            menuIcon.typeface = iconFont
            menuLabel.text = itemView.context.getString(text)
            itemView.setOnClickListener {
                onSelect(text)
            }
        }
    }
}