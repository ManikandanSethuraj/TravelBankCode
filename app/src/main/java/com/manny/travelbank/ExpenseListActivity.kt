package com.manny.travelbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.manny.travelbank.adapters.ExpenseAdapter
import com.manny.travelbank.models.ExpenseItem
import com.manny.travelbank.repository.ExpenseRepository
import com.manny.travelbank.ui.ExpenseDetailsFragment
import com.manny.travelbank.util.Utility
import kotlinx.android.synthetic.main.activity_expense_list.*

class ExpenseListActivity : AppCompatActivity(), ExpenseAdapter.CallBackInterface, ExpenseDetailsFragment.CallBackClose {

    lateinit var viewModel: ExpensesViewModel

    lateinit var expenseAdapter:  ExpenseAdapter

    lateinit var expenseFragment : ExpenseDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)
        setupRecyclerView()
        val expenseRepository = ExpenseRepository()
        val viewModelProviderFactory = ExpenseViewModelProviderFactory( expenseRepository, application)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ExpensesViewModel::class.java)

        viewModel.expenses.observe(this, Observer {
            it?.let {  expenseValue ->
                val values = expenseValue.toList()
                val totalValue = values.sumOf {
                    it.amount
                }
                aelTotalExpenses.text = ("Total: $ ").plus(Utility.roundOffDecimal(totalValue))
                println("The size of the value is ${values.size}")
                expenseAdapter.differ.submitList(values)
                println("Size of adapter:: ${expenseAdapter.itemCount} ")
            }
        })



    }

    private fun setupRecyclerView() {
        expenseAdapter = ExpenseAdapter(this)
        recyclerViewExpenses.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(this@ExpenseListActivity)
        }
    }



    override fun callBack(expenseItem: ExpenseItem) {
        println("The Clicked Value ${expenseItem.amount}")
        expenseFragment = ExpenseDetailsFragment(expenseItem, this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, expenseFragment)
       // transaction.disallowAddToBackStack()
        transaction.commit()
    }

    override fun callBackClose() {
        supportFragmentManager.beginTransaction().remove(expenseFragment).commit()
    }

    override fun onBackPressed() {
        if (expenseFragment.isVisible)
            callBackClose()
        else
            super.onBackPressed()
    }
}