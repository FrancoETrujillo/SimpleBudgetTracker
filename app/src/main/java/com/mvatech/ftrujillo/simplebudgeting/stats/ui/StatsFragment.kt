package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.StatsViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
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

        val chart = statsPieChart

        val colors = ArrayList<Int>()

        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.RED)
        colors.add(Color.BLUE)

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(18.5f, "Green"))
        entries.add(PieEntry(26.7f, "Yellow"))
        entries.add(PieEntry(24.0f, "Red"))
        entries.add(PieEntry(30.8f, "Blue"))

        val set = PieDataSet(entries,"")
        set.setColors(colors)

        val data = PieData(set)
        chart.data = data

        viewModel.statsList.observe(viewLifecycleOwner, Observer {
            categoryStats.clear()
            categoryStats.addAll(it)
        })

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

}
