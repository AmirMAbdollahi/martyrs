package com.example.martyrs.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.common.implementSpringAnimationTrait
import com.example.martyrs.common.reversDate
import com.example.martyrs.data.Data
import com.example.martyrs.services.ImageLoadingService
import com.example.martyrs.view.MartyrImageView

class MartyrListAdapter(val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<MartyrListAdapter.ViewHolder>() {

    var martyrOnClickListener: MartyrOnClickListener? = null

    var martyrs = ArrayList<Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName = itemView.findViewById<TextView>(R.id.fullName)
        val fatherName = itemView.findViewById<TextView>(R.id.fatherName)
        val birthDate = itemView.findViewById<TextView>(R.id.birthDate)
        val martyrDate = itemView.findViewById<TextView>(R.id.martyrDate)
        val martyrImage: MartyrImageView = itemView.findViewById(R.id.martyrsImage)
        fun bind(data: Data) {
            fullName.text = data.fullName
            fatherName.text = data.fatherFirstName
            // TODO: revers data.birthDate data.birthDate?.let { reversDate(it) }
            birthDate.text = data.birthDate
            martyrDate.text = data.martyrDate
            imageLoadingService.loadImage(martyrImage, data.photoUrl)
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                martyrOnClickListener?.martyrClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_martyr, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(martyrs[position])

    override fun getItemCount(): Int = martyrs.size

    interface MartyrOnClickListener {
        fun martyrClick(data: Data)
    }

}

