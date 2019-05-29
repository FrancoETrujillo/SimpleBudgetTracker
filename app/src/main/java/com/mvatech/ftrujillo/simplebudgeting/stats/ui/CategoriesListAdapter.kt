package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvatech.ftrujillo.simplebudgeting.utils.DiffUtilImpl
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import java.math.BigDecimal

class CategoriesListAdapter(private val categoryStats: MutableList<CategoryStats>) : RecyclerView.Adapter<CategoriesListAdapter.Holder>() {

    fun updateList(newList:List<CategoryStats>){
        val result = DiffUtil.calculateDiff(
            DiffUtilImpl(
                categoryStats,
                newList
            )
        )
        categoryStats.clear()
        categoryStats.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_stat_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = categoryStats.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoryStats[position])
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val totalSpent:TextView = itemView.findViewById(R.id.totalSpent)
        private val categoryName:TextView = itemView.findViewById(R.id.categoryName)
        private val categoryColor:ImageView = itemView.findViewById(R.id.categoryColor)


        fun bind(stat: CategoryStats){
            val totalSpentText =  "$" + stat.totalSpent
            totalSpent.text =totalSpentText

            categoryName.text = stat.categoryName

            categoryColor.setColorFilter(stat.categoryColor)
            itemView.setOnClickListener{
                Toast.makeText(it.context, "${stat.categoryName} was clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

