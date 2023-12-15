package com.example.bonusapp.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.example.bonusapp.R
import com.example.bonusapp.entity.Bonus

class BonusAdapter( var listener: IBonusListener):BaseQuickAdapter<Bonus,QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: Bonus?) {
        holder.setText(R.id.tv_title,item?.title)
        holder.setText(R.id.tv_des,item?.description)
        holder.setText(R.id.tv_time,item?.time)
        holder.getView<RelativeLayout>(R.id.rl_delete).setOnClickListener {
            if (listener!=null){
                listener.onDeleteBonus(item!!)
            }
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item,parent)
    }
}