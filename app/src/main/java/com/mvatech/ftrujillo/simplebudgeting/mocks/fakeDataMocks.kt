package com.mvatech.ftrujillo.simplebudgeting.mocks

import android.graphics.Color
import com.mvatech.ftrujillo.simplebudgeting.data.domain.Category
import com.mvatech.ftrujillo.simplebudgeting.data.domain.CategoryStats
import java.math.BigDecimal

fun getMockedCategoryList(): List<Category>{
    val categoryList = mutableListOf<Category>()
    categoryList.add(Category("Food", Color.BLUE))
    categoryList.add(Category("Drinks", Color.RED))
    categoryList.add(Category("School", Color.YELLOW))
    categoryList.add(Category("Transportation", Color.GREEN))

    return categoryList
}

fun getMockedCurrentGoal() = 500

fun getMockedCurrentRemaining() = 150

fun getMockedCategoryStats(): List<CategoryStats>{
    val categoryStatsList = mutableListOf<CategoryStats>()
    categoryStatsList.add(CategoryStats(BigDecimal(50.0), "Food", Color.YELLOW))
    categoryStatsList.add(CategoryStats(BigDecimal(25.0), "Drinks", Color.RED))
    categoryStatsList.add(CategoryStats(BigDecimal(10.0), "School", Color.GREEN))
    return categoryStatsList
}