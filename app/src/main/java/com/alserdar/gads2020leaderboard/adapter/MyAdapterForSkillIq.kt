package com.alserdar.gads2020leaderboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alserdar.gads2020leaderboard.R
import com.alserdar.gads2020leaderboard.model.SkillIqItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*


class MyAdapterForSkillIq(private val context: Context, private val skillList: MutableList<SkillIqItem>):
    RecyclerView.Adapter<MyAdapterForSkillIq.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return skillList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(skillList[position].badgeUrl).into(holder.image)
        holder.txt_name.text = skillList[position].name
        holder.txt_team.text = skillList[position].country
        holder.txt_createdby.text = skillList[position].score.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var txt_name : TextView
        var txt_team : TextView
        var txt_createdby : TextView

        init {
            image = itemView.image_item
            txt_name = itemView.name_item
            txt_team = itemView.country_item
            txt_createdby = itemView.score_hours_item
        }
    }
}