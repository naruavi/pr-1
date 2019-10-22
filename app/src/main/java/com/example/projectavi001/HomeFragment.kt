package com.example.projectavi001


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by abhilashgupta on Sep, 2019
 */

class HomeFragment : Fragment(), View.OnClickListener, CalendarView.OnDateChangeListener, PaymentDetailOnClickListener {
    override fun removePaymentDettail(paymentDetail: PaymentDetail) {
        viewModel.delete(paymentDetail)
    }

    override fun updatePaymentDetail(paymentDetail: PaymentDetail) {
        viewModel.updatePaymentDetail.value = paymentDetail
    }

    private lateinit var viewModel: AddPaymentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[AddPaymentDetailViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        viewModel.repoCall
            .observe(this, Observer<Boolean> {
                if (it) {
                    Toast.makeText(context, "successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "failure", Toast.LENGTH_LONG).show()
                }
            }
            )
        getPaymentList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_payment_detail.layoutManager = linearLayoutManager
        rv_payment_detail.adapter = PaymentDetailAdapter(context = context, listener = this)
        fb_add_payment.setOnClickListener(this)
        cv_calender.setOnDateChangeListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            fb_add_payment.id -> {
                viewModel.addPaymentBtn.value = true
            }
            else -> {
                Toast.makeText(context, "something", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            viewModel.byDate.value?.let {
                cv_calender.date = getCurrentDateInMillis(it)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun getPaymentList(){
        viewModel.byDate.observe(this, Observer { date ->
            viewModel.getAllPaymentsByDate(date)
                .observe(this@HomeFragment, Observer {
                    (rv_payment_detail.adapter as PaymentDetailAdapter).setPaymentDetails(it)
                    var sum = 0.0
                    for (i in it){
                        sum += i.price
                    }
                    tv_total_price.text = sum.toString()
                })
        })
    }

    override fun onSelectedDayChange(
        calenderView: CalendarView,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        viewModel.byDate.value = formDate(year, month, dayOfMonth)
    }

}
