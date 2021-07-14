package android.esraagad.com.fixer.ui.activity

import android.esraagad.com.fixer.R
import android.esraagad.com.fixer.ui.adapter.RatesAdapter
import android.esraagad.com.fixer.view_model.HomeViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import java.time.Duration

class HomeActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val ratesAdapter by lazy { RatesAdapter(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setRecyclerView()
        observeDataArrival()
        observeError()
    }

    private fun setRecyclerView() {
        rates_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rates_recycler_view.adapter = ratesAdapter
    }

    private fun observeDataArrival() {
        viewModel.response.observe(this, {
            ratesAdapter.setData(it.rates)
        })
    }

    private fun observeError() {
        viewModel.error.observe(this, {
            Toast.makeText(this, it, LENGTH_LONG).show()
        })
    }
}