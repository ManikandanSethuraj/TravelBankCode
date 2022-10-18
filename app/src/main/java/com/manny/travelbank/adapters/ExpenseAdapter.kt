package com.manny.travelbank.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manny.travelbank.R
import com.manny.travelbank.models.Expense
import com.manny.travelbank.models.ExpenseItem
import com.manny.travelbank.util.Utility
import com.manny.travelbank.util.Utility.formatDateAdapter
import kotlinx.android.synthetic.main.fragment_expense_details.*
import kotlinx.android.synthetic.main.fragment_expense_details.view.*
import kotlinx.android.synthetic.main.item_expense_article.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ExpenseAdapter (private val callbackInterface:CallBackInterface) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    private val differCallback = object : DiffUtil.ItemCallback<ExpenseItem>() {
        override fun areItemsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExpenseItem, newItem: ExpenseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_expense_article,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }





    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expenseItem = differ.currentList[position]
        holder.itemView.apply {
           val image : String = expenseItem?.attachments?.get(0)?.thumbnails?.full ?: ""
            image.let {
                if (it != "")
                Glide.with(this)
                    .load(image)
                    .error(resources.getDrawable(R.drawable.ic_baseline_image_24))
                    .into(ieImage)

                else
                    Glide.with(this).load(resources.getDrawable(R.drawable.ic_baseline_image_24)).into(ieImage)
            }
           iePersonName.text = expenseItem.expenseVenueTitle
           val amount  = ("$ ").plus(Utility.roundOffDecimal(expenseItem.amount))
            println("AmountValue::$amount")
            ieAmount.text = amount
            ieType.text = expenseItem.tripBudgetCategory

            println(expenseItem.date)

            ieCurrency.text = expenseItem.currencyCode
            ieDate.text = formatDateAdapter(expenseItem.date)

        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            callbackInterface.callBack(expenseItem)
        })

    }

//    private fun Date.dateToString(): String {
//        val dateFormatter = SimpleDateFormat("MMM dd", Locale.getDefault())
//        return dateFormatter.format(this)
//    }
//    private fun formatDate(dateToFormat : String) : String{
//        val format = SimpleDateFormat("MM-dd")
//        val date: Date = format.parse(dateToFormat)
//        return date.dateToString()
//    }

    interface CallBackInterface{
        fun callBack(expenseItem: ExpenseItem)
    }
}