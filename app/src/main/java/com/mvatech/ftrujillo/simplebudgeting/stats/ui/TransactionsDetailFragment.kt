package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.stats.ui.adapters.TransactionDetailListAdapter
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.TransactionsDetailViewModel
import com.mvatech.ftrujillo.simplebudgeting.utils.toast
import kotlinx.android.synthetic.main.transaction_stat_item.*
import kotlinx.android.synthetic.main.transactions_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionsDetailFragment : Fragment() {

    private val viewModel by viewModel<TransactionsDetailViewModel>()
    private val transactionDetailAdapter = TransactionDetailListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transactions_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { TransactionsDetailFragmentArgs.fromBundle(it) }
        val categoryId = safeArgs?.categoryInfo ?: throw IllegalArgumentException("No Arguments Passed to Fragment")

        transactionDetailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = transactionDetailAdapter
        }

        viewModel.getTransactionsFromCategory(categoryId).observe(viewLifecycleOwner, Observer(this::transactionsObserver))
        categoryId.toast(this.context)
    }

    private fun transactionsObserver(transactionList: List<Transaction>){
        transactionDetailAdapter.update(transactionList)
    }

}
