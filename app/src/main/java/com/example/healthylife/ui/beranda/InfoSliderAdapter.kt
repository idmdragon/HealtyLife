package com.example.healthylife.ui.beranda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthylife.R

class InfoSliderAdapter(private val context: Context, private val infoSlide: List<InfoSlide>) :
    RecyclerView.Adapter<InfoSliderAdapter.InfoSlideViewHolder>() {

    inner class InfoSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textKonten = view.findViewById<TextView>(R.id.tv_konten)

        fun bind(infoSlide: InfoSlide) {
            textKonten.text = infoSlide.konten
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoSlideViewHolder {
        return InfoSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_pager, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return infoSlide.size
    }

    override fun onBindViewHolder(holder: InfoSlideViewHolder, position: Int) {
        holder.bind(infoSlide[position])
    }
}