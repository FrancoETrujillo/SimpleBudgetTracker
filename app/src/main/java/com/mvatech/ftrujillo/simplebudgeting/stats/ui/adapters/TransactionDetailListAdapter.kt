package com.mvatech.ftrujillo.simplebudgeting.stats.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Transaction
import com.mvatech.ftrujillo.simplebudgeting.utils.DiffUtilImpl
import com.mvatech.ftrujillo.simplebudgeting.utils.inflate
import com.mvatech.ftrujillo.simplebudgeting.utils.toast
import kotlinx.android.synthetic.main.transaction_detail_item.view.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class TransactionDetailListAdapter:
    RecyclerView.Adapter<TransactionDetailListAdapter.TransactionDetailViewHolder>(){
    private val transactionList= mutableListOf<Transaction>( )

    fun update(newList: List<Transaction>){
        val result = DiffUtil.calculateDiff(
            DiffUtilImpl(
                transactionList,
                newList
            )
        )
        transactionList.clear()
        transactionList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailViewHolder {
        return TransactionDetailViewHolder(parent.inflate(R.layout.transaction_detail_item, false))
    }

    override fun getItemCount(): Int {
        return transactionList.count()
    }

    override fun onBindViewHolder(holder: TransactionDetailViewHolder, position: Int) {
        holder.bind(transactionList[position])
    }

    inner class TransactionDetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(transaction: Transaction){
            val priceString =  "$" + transaction.price

            itemView.transactionPrice.text = priceString
            itemView.transactionDate.text = transaction.time.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            itemView.transactionNote.text = transaction.label
        }
        override fun onClick(v: View) {
            "hello".toast(v.context)
            //TODO: Implement if needed
        }
    }
}