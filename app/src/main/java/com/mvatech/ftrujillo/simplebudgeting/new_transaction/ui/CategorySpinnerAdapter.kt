package com.mvatech.ftrujillo.simplebudgeting.new_transaction.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category

class CategorySpinnerAdapter(context: Context?, categoryList: List<Category>): ArrayAdapter<Category>(context, 0, categoryList){

    fun updateCategories(categoryList: List<Category>){
        clear()
        addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup):View{
        val convertedView = convertView?: LayoutInflater.from(context)
            .inflate(R.layout.category_spinner_item, parent, false)

        val colorView = convertedView.findViewById<ImageView>(R.id.categoryColor)
        val categoryName = convertedView.findViewById<TextView>(R.id.categoryName)
        val currentItem = getItem(position)
        colorView.setColorFilter(currentItem.color)
        categoryName.text = currentItem.name

        return convertedView
    }

}