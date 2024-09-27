package com.example.workoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items: ArrayList<String> ):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    class ViewHolder(binding: ItemHistoryRowBinding)
        :RecyclerView.ViewHolder(binding.root){

            val llHistoryItemMain = binding.llHistoryItemMain
            val tvItem = binding.tvItem
            val tvPosition = binding.tvPosition
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val date:String = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if(position%2==0){
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(context,R.color.lightgrey))
        }
        else{
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(context,R.color.white))

        }
    }
}