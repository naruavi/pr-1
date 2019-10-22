package com.example.projectavi001

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_budget_item.view.*
import java.lang.ref.WeakReference

/**
 * Created by abhilashgupta on Sep, 2019
 */

class PaymentDetailAdapter(
    private val context: Context?,
    private val listener: PaymentDetailOnClickListener
) : RecyclerView.Adapter<PaymentDetailViewHolder>() {

    private var paymentDetailList: MutableList<PaymentDetail> = ArrayList<PaymentDetail>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentDetailViewHolder {
        return PaymentDetailViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recyclerview_budget_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return paymentDetailList.size
    }

    override fun onBindViewHolder(holder: PaymentDetailViewHolder, position: Int) {
        holder.tv_Price.text = paymentDetailList[position].price.toString()
        holder.tv_reason.text = paymentDetailList[position].reason
        holder.bt_remove_payment_detail.setOnClickListener {
            listener.removePaymentDettail(paymentDetailList[position])
        }
        holder.bt_update_payment_detail.setOnClickListener {
            listener.updatePaymentDetail(paymentDetailList[position])
        }
    }

    fun setPaymentDetails(paymentDetailList: List<PaymentDetail>) {
        paymentDetailList.let {
            this.paymentDetailList.clear()
            this.paymentDetailList.addAll(paymentDetailList)
            notifyDataSetChanged()
        }
    }
}

interface PaymentDetailOnClickListener {
    fun updatePaymentDetail(paymentDetail: PaymentDetail)
    fun removePaymentDettail(paymentDetail: PaymentDetail)
}

class PaymentDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_Price = itemView.tv_price
    val tv_reason = itemView.tv_reason
    val bt_update_payment_detail= itemView.bt_update_payment_detail
    val bt_remove_payment_detail= itemView.bt_remove_payment_detail
}