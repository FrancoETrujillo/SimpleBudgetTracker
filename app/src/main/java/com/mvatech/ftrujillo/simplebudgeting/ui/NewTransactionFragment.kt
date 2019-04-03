package com.mvatech.ftrujillo.simplebudgeting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.view_model.NewTransactionViewModel


class NewTransactionFragment : Fragment() {

    companion object {
        fun newInstance() = NewTransactionFragment()
    }

    private lateinit var viewModel: NewTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_transaction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewTransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
