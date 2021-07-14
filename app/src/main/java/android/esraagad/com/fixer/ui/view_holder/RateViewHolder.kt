package android.esraagad.com.fixer.ui.view_holder

import android.content.Intent
import android.esraagad.com.fixer.ui.activity.ConverterActivity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rate_list_item.view.*

class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setItem(code: String, rate: Double) {
        itemView.country_tv.text = code
        itemView.rate_tv.text = rate.toString()
        itemView.setOnClickListener {
            launchConverterScreen(code, rate)
        }
    }

    private fun launchConverterScreen(code: String, rate: Double) {
        val launchConverterIntent: Intent = Intent(itemView.context, ConverterActivity::class.java)
        launchConverterIntent.putExtra("code", code)
        launchConverterIntent.putExtra("rate", rate)
        itemView.context.startActivity(launchConverterIntent)
    }
}