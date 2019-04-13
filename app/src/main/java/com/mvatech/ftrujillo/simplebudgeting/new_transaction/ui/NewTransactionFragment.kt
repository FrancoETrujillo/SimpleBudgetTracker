package com.mvatech.ftrujillo.simplebudgeting.new_transaction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.Category
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
import com.mvatech.ftrujillo.simplebudgeting.stats.ui.CategorySpinnerAdapter
import kotlinx.android.synthetic.main.new_transaction_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewTransactionFragment : Fragment() {

    private val viewModel by viewModel<NewTransactionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_transaction_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInitialState()
        fillCategories()
        bindListeners()
    }

    private fun initInitialState() {
        setVisibility(titleCheckBox.isChecked, titleEditText)
        setVisibility(todayCheckBox.isChecked, dateEditText)
    }

    private fun fillCategories() {
        categorySpinner.adapter = this.context?.let { CategorySpinnerAdapter(it, getMockedCategoryList()) }
    }


    private fun bindListeners() {
        addFavButton.setOnClickListener(onFavButtonClick)
        titleCheckBox.setOnCheckedChangeListener(onUntitledCheckBoxChanged)
        todayCheckBox.setOnCheckedChangeListener(onTodayCheckBoxChanged)
        viewModel.categoryList.observe(viewLifecycleOwner, Observer(this::categoryListChanged))
        viewModel.currentRemaining.observe(viewLifecycleOwner, Observer(this::currentSpentChanged))
        viewModel.currentGoal.observe(viewLifecycleOwner, Observer(this::currentGoalChanged))
    }

    private fun setVisibility(isChecked: Boolean, view: View){
        if(isChecked){
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
    private fun categoryListChanged(categoryList: List<Category>){
        Toast.makeText(this.context, categoryList.toString(), Toast.LENGTH_LONG).show()
    }
    private fun currentGoalChanged(currentGoal:Int){
        goalStatus.maxSpeed = currentGoal.toFloat()
    }
    private fun currentSpentChanged(currentRemaining:Int){
        goalStatus.setSpeedAt(currentRemaining.toFloat())
    }


    private val onFavButtonClick = View.OnClickListener {
        val cost = costEditText.text.toString().toBigDecimal()
        val category = categorySpinner.selectedItem as Category
        val date = dateEditText.text.toString()
        val title = titleEditText.text.toString()
        viewModel.saveNewTransaction(cost,category,date,title)
        viewModel.sync() }
    private val onTodayCheckBoxChanged = CompoundButton.OnCheckedChangeListener{ _, isChecked ->
        setVisibility(isChecked, dateEditText)
    }
    private val onUntitledCheckBoxChanged = CompoundButton.OnCheckedChangeListener{_, isChecked ->
        setVisibility(isChecked, titleEditText)
    }

}
