package com.example.a7minuteworkout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.model.Exercise
import kotlinx.android.synthetic.main.item_history_row.view.*

class HistoryAdapter(val context: Context, val items: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val historyMainItem = view.history_item_main
        val itemTxt = view.itemTxt
        val positionTxt = view.positionTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_row, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)

        holder.positionTxt.text = (position + 1).toString()
        holder.itemTxt.text = date

        if(position % 2 == 0){
            holder.historyMainItem.setBackgroundColor(Color.parseColor("#EBEBEB"))
        }else{
            holder.historyMainItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }
}