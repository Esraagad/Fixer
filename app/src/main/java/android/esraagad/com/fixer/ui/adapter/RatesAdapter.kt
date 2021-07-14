package android.esraagad.com.fixer.ui.adapter

import android.esraagad.com.fixer.R
import android.esraagad.com.fixer.ui.view_holder.RateViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RatesAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<RateViewHolder>() {
    private val rates: MutableMap<String, Double> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val itemView = layoutInflater.inflate(R.layout.rate_list_item, parent, false)
        return RateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val code = rates.keys.toList()[position]
        val rate = rates[code]
        rate?.let { holder.setItem(code, it) }
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    fun setData(rates: Map<String, Double>) {
        this.rates.clear()
        this.rates.putAll(rates)
        notifyDataSetChanged()
    }
}