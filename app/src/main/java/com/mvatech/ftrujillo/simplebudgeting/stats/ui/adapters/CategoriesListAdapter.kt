package com.mvatech.ftrujillo.simplebudgeting.stats.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvatech.ftrujillo.simplebudgeting.utils.DiffUtilImpl
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.stats.ui.StatsFragmentDirections
import com.mvatech.ftrujillo.simplebudgeting.utils.inflate
import com.mvatech.ftrujillo.simplebudgeting.utils.toast
import kotlinx.android.synthetic.main.category_stat_item.view.*

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
        return Holder(parent.inflate(R.layout.category_stat_item,false))
    }

    override fun getItemCount() = categoryStats.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoryStats[position])
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var stat: CategoryStats

        fun bind(stat: CategoryStats){
            this.stat = stat
            val totalSpentText =  "$" + stat.totalSpent
            itemView.totalSpent.text =totalSpentText
            itemView.categoryName.text = stat.categoryName
            itemView.categoryColor.setColorFilter(stat.categoryColor)
        }

        override fun onClick(v: View) {
            val action =
                StatsFragmentDirections.statsActionDetail(stat.categoryColor)
            Navigation.findNavController(itemView).navigate(action)
        }
    }

}

