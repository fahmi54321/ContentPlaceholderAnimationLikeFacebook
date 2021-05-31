package com.android.a104contentplaceholderanimationlikefacebook.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.a104contentplaceholderanimationlikefacebook.Model.Item
import com.android.a104contentplaceholderanimationlikefacebook.R
import com.squareup.picasso.Picasso

class MyAdapter(
    internal var context: Context,
    internal var itemList: List<Item>?
):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        internal var  thumbnail:ImageView
        internal var  txt_title:TextView
        internal var  txt_url:TextView

        init {
            thumbnail = itemView.findViewById(R.id.thumbnail)
            txt_title = itemView.findViewById(R.id.title)
            txt_url = itemView.findViewById(R.id.url)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(itemList?.get(position)?.thumbnailUrl).into(holder.thumbnail)
        holder.txt_title.text = itemList?.get(position)?.title
        holder.txt_url.text = itemList?.get(position)?.url
    }

    override fun getItemCount(): Int {
        return itemList?.size?:0
    }
}