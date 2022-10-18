package com.manny.travelbank.api

import com.manny.travelbank.models.Expense
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ExpenseAPI {

    @GET("v3/178cbbee-c634-4a51-afb8-dcd75c190d29")
    suspend fun getExpenses() : Response<Expense>

}