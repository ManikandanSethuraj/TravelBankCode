package com.manny.travelbank

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manny.travelbank.repository.ExpenseRepository

class ExpenseViewModelProviderFactory(
    val repository: ExpenseRepository,
    val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpensesViewModel(application, repository) as T
    }
}