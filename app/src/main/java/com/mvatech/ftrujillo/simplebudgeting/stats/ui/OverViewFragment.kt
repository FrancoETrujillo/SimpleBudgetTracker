package com.mvatech.ftrujillo.simplebudgeting.stats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mvatech.ftrujillo.simplebudgeting.R
import com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel.OverViewViewModel


class OverViewFragment : Fragment() {

    companion object {
        fun newInstance() = OverViewFragment()
    }

    private lateinit var viewModel: OverViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.over_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OverViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
