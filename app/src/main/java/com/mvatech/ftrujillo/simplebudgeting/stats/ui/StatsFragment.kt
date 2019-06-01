package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.stats.ui.adapters.CategoriesListAdapter
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.StatsViewModel
import kotlinx.android.synthetic.main.stats_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatsFragment : Fragment() {

    companion object {
        val categoryStats = mutableListOf<CategoryStats>()
    }

    private val viewModel by viewModel<StatsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.statsList.observe(viewLifecycleOwner, Observer(this::statsListChanged))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCategoryList()

    }
    private fun bindCategoryList() {
        categoryStatsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CategoriesListAdapter(categoryStats)
        }
    }

    private fun statsListChanged(list :List<CategoryStats>) {
        categoryStats.clear()
        categoryStats.addAll(list)
        updatePieChart(list)
    }

    private fun updatePieChart(list: List<CategoryStats>) {
        val chart = statsPieChart
        chart.data = viewModel.generatePieChartData(list)
        chart.description.text = ""
        chart.centerText = "$${viewModel.currentSpent} / $${viewModel.currentGoal}"
    }


}
