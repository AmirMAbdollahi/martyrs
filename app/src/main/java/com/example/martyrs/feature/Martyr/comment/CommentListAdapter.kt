package com.example.martyrs.feature.Martyr.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.martyrs.R
import com.example.martyrs.data.Comment
import com.example.martyrs.data.DataComment
import com.example.martyrs.data.ResultComment

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    var commentList = ArrayList<DataComment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.date)
        val content: TextView = itemView.findViewById(R.id.content)
        fun bind(dataComment: DataComment) {
            name.text = dataComment.fullName
            date.text = dataComment.creationDate
            content.text = dataComment.text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size

}