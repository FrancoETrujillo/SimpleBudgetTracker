package com.mvatech.ftrujillo.simplebudgeting.stats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import com.mvatech.ftrujillo.simplebudgeting.data.repository.Repository

class StatsViewModel(repository: Repository) : ViewModel() {

    val statsList:LiveData<List<CategoryStats>> = repository.categoryStatsList

}
