package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.TransactionsDetailViewModel


class TransactionsDetailFragment : Fragment() {

    private lateinit var viewModel: TransactionsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TransactionsDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
