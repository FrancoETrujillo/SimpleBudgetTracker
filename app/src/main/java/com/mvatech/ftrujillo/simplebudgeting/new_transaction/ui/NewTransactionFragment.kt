package com.mvatech.ftrujillo.simplebudgeting.new_transaction.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.mocks.getMockedCategoryList
import com.mvatech.ftrujillo.simplebudgeting.new_transaction.viewmodel.NewTransactionViewModel
import com.mvatech.ftrujillo.simplebudgeting.stats.ui.CategorySpinnerAdapter
import kotlinx.android.synthetic.main.new_transaction_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter


class NewTransactionFragment : Fragment() {


    private val viewModel by viewModel<NewTransactionViewModel>()
    companion object{
        lateinit var date: LocalDateTime
    }

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
        setDateEditText()
    }

    private fun fillCategories() {
        categorySpinner.adapter = this.context?.let { CategorySpinnerAdapter(it, getMockedCategoryList()) }
    }

    private fun bindListeners() {
        addFavButton.setOnClickListener(onFavButtonClick)
        titleCheckBox.setOnCheckedChangeListener(onUntitledCheckBoxChanged)
        todayCheckBox.setOnCheckedChangeListener(onTodayCheckBoxChanged)
        dateEditText.onFocusChangeListener = onDateEditTextFocusChangedListener
        dateEditText.setOnClickListener(onDateEditTextClickListener)
        viewModel.categoryList.observe(viewLifecycleOwner, Observer(this::categoryListChanged))
        viewModel.currentRemaining.observe(viewLifecycleOwner, Observer(this::currentSpentChanged))
        viewModel.currentGoal.observe(viewLifecycleOwner, Observer(this::currentGoalChanged))
    }

    private fun launchDatePicker(){
        val year = date.year
        val month = date.monthValue - 1
        val day = date.dayOfMonth

        val datePickerDialog = this.context?.let {
            DatePickerDialog(it, onDateChangedListener, year, month, day)}
        datePickerDialog?.show()
    }
    private fun setVisibility(isChecked: Boolean, view: View){
        if(isChecked){
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    private fun categoryListChanged(categoryList: List<Category>){
        //category List changed
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
        val title = titleEditText.text.toString()
        val titleChecked = if(titleCheckBox.isChecked) "Untitled" else title
        viewModel.saveNewTransaction(cost,category,date,titleChecked)
    }

    private val onDateEditTextFocusChangedListener = View.OnFocusChangeListener {_, hasFocus: Boolean ->
        if(hasFocus){
           launchDatePicker()
        }
    }

    private val onDateEditTextClickListener = View.OnClickListener{
        launchDatePicker()
    }

    private val onTodayCheckBoxChanged = CompoundButton.OnCheckedChangeListener{ _, isChecked ->
        setVisibility(isChecked, dateEditText)
        setDateEditText()
    }

    private fun setDateEditText() {
        if(!todayCheckBox.isChecked) {
            dateEditText.showSoftInputOnFocus = false
            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
            val dateString = date.format(formatter)
            dateEditText.setText(dateString)
        } else {
            date = LocalDateTime.now().with(LocalTime.of(0,0))
        }
    }

    private val onUntitledCheckBoxChanged = CompoundButton.OnCheckedChangeListener{_, isChecked ->
        setVisibility(isChecked, titleEditText)
    }

    private val onDateChangedListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        val newDate = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0, 0)
        date = newDate
        setDateEditText()
    }
}
