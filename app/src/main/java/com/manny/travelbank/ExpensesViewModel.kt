package com.manny.travelbank

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manny.travelbank.models.Expense
import com.manny.travelbank.repository.ExpenseRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ExpensesViewModel(
    application: Application,
    val expenseRepository: ExpenseRepository
   ) : AndroidViewModel(application) {

  val expenses : MutableLiveData<Expense> = MutableLiveData()
  var localExpenses : Expense? = null


    init {
        getExpenses()
    }

    fun getExpenses() = viewModelScope.launch {
        val response = expenseRepository.getExpensesList()
        Log.d("ResponseValues::", response.body().toString())

        expenses.postValue(checkResponse(response))
    }

    fun checkResponse(response: Response<Expense>) : Expense? {
        if (response.isSuccessful){
            Log.d("ResponseSuccess", response.body().toString())
            response.body()?.let {
                Log.d("CheckResponse", "checkResponse: " + it)
                if (localExpenses == null){
                localExpenses = it
                }
                return localExpenses
            }
        }
        return null
    }


}