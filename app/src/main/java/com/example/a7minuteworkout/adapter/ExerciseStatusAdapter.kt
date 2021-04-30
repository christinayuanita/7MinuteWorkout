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
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val items: ArrayList<Exercise>, val context: Context) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise: Exercise = items[position]

        holder.itemTxt.text = exercise.getId().toString()

        if(exercise.getIsSelected()){
            holder.itemTxt.background = ContextCompat.getDrawable(context, R.drawable.item_circular_thin_color_accent_border)
            holder.itemTxt.setTextColor(Color.parseColor("#212121"))
        }else if(exercise.getIsCompleted()){
            holder.itemTxt.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
            holder.itemTxt.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.itemTxt.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
            holder.itemTxt.setTextColor(Color.parseColor("#212121"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTxt = view.itemTxt
    }
}