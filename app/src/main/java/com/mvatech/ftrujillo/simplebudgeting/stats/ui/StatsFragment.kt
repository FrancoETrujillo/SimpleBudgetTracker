package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.StatsViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.CategoryStats
import kotlinx.android.synthetic.main.stats_fragment.*
import java.math.BigDecimal


class StatsFragment : Fragment() {

    companion object {
        fun newInstance() = StatsFragment()
        val categoryStats = mutableListOf<CategoryStats>()

    }

    private lateinit var viewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatsViewModel::class.java)


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

//        chart.invalidate() // refresh
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
