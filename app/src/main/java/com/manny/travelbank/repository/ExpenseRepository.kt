package com.manny.travelbank.repository

import com.manny.travelbank.api.RetrofitInstance

class ExpenseRepository() {

    suspend fun getExpensesList() =
        RetrofitInstance.api.getExpenses()

}