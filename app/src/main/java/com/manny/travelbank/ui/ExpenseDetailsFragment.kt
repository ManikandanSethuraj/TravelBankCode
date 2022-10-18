package com.manny.travelbank.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.manny.travelbank.R
import com.manny.travelbank.models.ExpenseItem
import com.manny.travelbank.util.Utility
import kotlinx.android.synthetic.main.fragment_expense_details.*
import kotlinx.android.synthetic.main.item_expense_article.view.*
import java.text.SimpleDateFormat
import java.util.*

class ExpenseDetailsFragment(expenseItem: ExpenseItem, callBackClose: CallBackClose ) : Fragment(R.layout.fragment_expense_details){

    val expenseItemLocal = expenseItem
    val callBackCloseLocal = callBackClose

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(expenseItemLocal.expenseVenueTitle)

        fedEdtMerchant.setText(expenseItemLocal.expenseVenueTitle)
        fedEdtCategory.setText(expenseItemLocal.tripBudgetCategory)

        val total = ("$ ").plus(Utility.roundOffDecimal(expenseItemLocal.amount))
        fedEdtTotal.text = (total)
        expenseItemLocal.description.let {
            if (it != "")
                fed_description.text = it else fed_description.text = resources.getText(R.string.description)

        }

        fedEdtDate.text = (Utility.formatDate(expenseItemLocal.date))
        fedCurrencyType.text = expenseItemLocal.currencyCode
        val image : String = expenseItemLocal?.attachments?.get(0)?.thumbnails?.full ?: ""
        image.let {
            if (it != ""){
                Glide.with(this).load(image).error(resources.getDrawable(R.drawable.ic_baseline_image_24)).into(fedImage)
            }else{
                Glide.with(this).load(resources.getDrawable(R.drawable.ic_baseline_image_24)).into(fedImage)
            }
        }
      //  println(Utility.formatDate(expenseItemLocal.date))


        closeFragment.setOnClickListener(View.OnClickListener {
            callBackCloseLocal.callBackClose()
        })

    }


    interface CallBackClose{
        fun callBackClose()
    }

}