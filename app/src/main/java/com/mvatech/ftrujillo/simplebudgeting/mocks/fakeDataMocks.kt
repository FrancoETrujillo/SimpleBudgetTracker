package com.mvatech.ftrujillo.simplebudgeting.mocks

import android.graphics.Color
import com.mvatech.ftrujillo.simplebudgeting.data.Category
import com.mvatech.ftrujillo.simplebudgeting.data.CategoryStats
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