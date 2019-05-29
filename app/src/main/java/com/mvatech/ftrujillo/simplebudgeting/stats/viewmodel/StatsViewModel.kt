package com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import timber.log.Timber

class StatsViewModel(private val repository: Repository) : ViewModel() {

    val statsList:LiveData<List<CategoryStats>> = repository.categoryStatsList
    val currentSpent = repository.getCurrentSpent()
    val currentGoal = repository.getCurrentGoal()
    fun generatePieChartData(categoryStatList: List<CategoryStats>):PieData{

        val colors = ArrayList<Int>()
        val entries = ArrayList<PieEntry>()

        categoryStatList.forEach{categoryStat ->
            colors.add(categoryStat.categoryColor)
            entries.add(PieEntry(categoryStat.totalSpent.toFloat(), categoryStat.categoryName))
        }
        colors.add(Color.BLACK)

        entries.add(PieEntry(repository.getCurrentRemainingBlocking().toFloat().also {
            Timber.d("Franco remaining $it") }, "Remaining"))
        val set = PieDataSet(entries," CATEGORIES")
        set.colors = colors

        return PieData(set)
    }

}
