package com.example.projectavi001

/**
 * Created by abhilashgupta on Sep, 2019
 */

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.add_payment_detail_fragment.*


class AddPaymentDetailFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: AddPaymentDetailViewModel
    private var addOrUpdate = "add"
    private var updateId: Int? = -1

    companion object {
        private const val ENTRY = "ENTRY"
        private const val ACTION = "action"
        fun newInstance(paymentDetail: PaymentDetail, action: String) = AddPaymentDetailFragment().withArgs {
            putParcelable(ENTRY, paymentDetail)
            putString(ACTION, action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_payment_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[AddPaymentDetailViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {args ->
            val paymentDetail = args.getParcelable<PaymentDetail>(ENTRY)
            ed_your_reason.editText?.setText(paymentDetail?.reason)
            ed_add_price.editText?.setText(paymentDetail?.price.toString())
            addOrUpdate = args.getString(ACTION).toString()
            updateId = paymentDetail?.id
        }
        bt_add_payment_detail.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            bt_add_payment_detail.id -> {
                when (addOrUpdate) {
                    "update" -> viewModel.update(
                        PaymentDetail(
                            id = updateId,
                            date = viewModel.byDate.value ?: dateIsNull(),
                            reason = ed_your_reason.editText?.text.toString(),
                            price = (ed_add_price.editText?.text.toString()).toDouble()
                        )
                    )
                    "add" -> viewModel.insert(
                        PaymentDetail(
                            date = viewModel.byDate.value ?: dateIsNull(),
                            reason = ed_your_reason.editText?.text.toString(),
                            price = (ed_add_price.editText?.text.toString()).toDouble()
                        )

                    )
                    else -> Toast.makeText(context, "neither add or update", Toast.LENGTH_LONG).show()
                }
                hideKeyboard(activity, context)
                ed_add_price.editText?.text?.clear()
                ed_add_price.clearFocus()
                ed_your_reason.editText?.text?.clear()
                ed_your_reason.clearFocus()
            }
        }
    }

    private fun dateIsNull(): String {
        Toast.makeText(
            context,
            "error in selecting date showing result for today",
            Toast.LENGTH_SHORT
        ).show()
        return getCurrentDate()
    }

}