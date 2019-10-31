package com.example.projectavi001


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_lend_and_borrow.*
import kotlin.properties.Delegates

/**
 * Created by abhilashgupta on Sep, 2019
 */
class LendAndBorrow : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: LendAndBorrowViewModel
    private var leadAndBorrow by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lend_and_borrow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lend_borrow_drop_down,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sp_lend_and_borrow.adapter = adapter
        }
        bt_add_transaction.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (view?.id) {
            bt_add_transaction.id -> {
                viewModel.insert(
                    LendAndBorrowDetail(
                        name = ed_name.editText?.text.toString(),
                        money = (ed_add_money.editText?.text.toString()).toDouble(),
                        lendOrBorrow = leadAndBorrow
                    )
                )
            }
            else -> {
                Toast.makeText(context, "something", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        leadAndBorrow = parent?.getItemAtPosition(1) as Int
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        leadAndBorrow = parent.getItemAtPosition(pos) as Int
    }
}
