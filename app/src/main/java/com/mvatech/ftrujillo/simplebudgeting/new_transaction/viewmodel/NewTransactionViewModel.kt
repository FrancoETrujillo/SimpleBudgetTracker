package com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.SpendingGoal
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import timber.log.Timber
import java.math.BigDecimal


class NewTransactionViewModel(private val repository: Repository) : ViewModel() {

    val categoryList: LiveData<List<Category>> get() = repository.getCategoryList()


    val currentGoalInformation: LiveData<SpendingGoal>
        get() {
            return repository.currentGoalInformation
        }

    fun saveNewTransaction(cost: BigDecimal, category: Category, date: LocalDateTime, title: String) {
        val transaction = Transaction(cost, date, title, category.color)
        GlobalScope.launch(Dispatchers.IO) {
            repository.saveNewTransaction(transaction)
        }
    }

    fun generatePieChartData(spendingGoal: SpendingGoal): PieData {

        spendingGoal.currentGoal
        spendingGoal.currentRemaining
        val colors = ArrayList<Int>()
        val entries = ArrayList<PieEntry>()

        colors.add(Color.GREEN)
        colors.add(Color.RED)
        entries.add(PieEntry(spendingGoal.currentRemaining.toFloat(), "Remaining"))
        entries.add(PieEntry(spendingGoal.currentSpent.toFloat(), "spent"))

        val set = PieDataSet(entries, "")
        set.colors = colors

        return PieData(set)
    }


}
