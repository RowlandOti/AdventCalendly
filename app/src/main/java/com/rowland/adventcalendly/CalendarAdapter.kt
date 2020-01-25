package com.rowland.adventcalendly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rowland.adventcalendly.data.AdventDay

import kotlinx.android.synthetic.main.row_calendar.view.card_cell
import kotlinx.android.synthetic.main.row_calendar.view.cell_content_hidden
import kotlinx.android.synthetic.main.row_calendar.view.cell_content_show
import java.util.*

class CalendarAdapter(var items: List<AdventDay> = mutableListOf()) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_calendar,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
           holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(list: List<AdventDay>) {
        items = list
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(adventDay: AdventDay) {
            itemView.card_cell.setOnClickListener { view: View? ->
                view!!.cell_content_hidden.visibility = View.VISIBLE
                view.cell_content_show.visibility = View.GONE
            }


            val calendar = Calendar.getInstance()
            val minDate = Date(calendar.timeInMillis)
            calendar.add(Calendar.DATE, 6)
            val maxDate = Date(calendar.timeInMillis)

        }
    }

}
