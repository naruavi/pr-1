package com.example.projectavi001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_event.*


class EventActivity : AppCompatActivity() {

    private lateinit var adapter: EventTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        adapter = EventTabAdapter(supportFragmentManager)
        adapter.addFragment(AddPaymentDetailFragment(), "Payment Transaction")
        adapter.addFragment(LendAndBorrow(), "Lend And Borrow Money")
        vp_event_view_pager.adapter = adapter
        tb_event_tab?.setupWithViewPager(vp_event_view_pager)
    }
}
