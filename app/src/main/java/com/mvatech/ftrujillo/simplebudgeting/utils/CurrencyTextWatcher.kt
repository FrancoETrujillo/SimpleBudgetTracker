package com.mvatech.ftrujillo.simplebudgeting.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.math.BigDecimal
import java.text.NumberFormat


class CurrencyTextWatcher(private val textField: EditText):TextWatcher {


    override fun afterTextChanged(editable: Editable?) {
        val text = editable.toString()

        if (text.isEmpty()) return
        textField.removeTextChangedListener(this)
        val cleanString = text.replace("[$,.]".toRegex(), "")
        val parsed =
            BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
        val formatted = NumberFormat.getCurrencyInstance().format(parsed)
        textField.setText(formatted)
        textField.setSelection(formatted.length)
        textField.addTextChangedListener(this)    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    companion object {
        fun cleanCurrencyText(value: String): BigDecimal{
            val cleanString = value.replace("[$,.]".toRegex(), "")
            return BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
        }
    }

}