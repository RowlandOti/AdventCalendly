package com.rowland.adventcalendly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_calendar.view.card_cell_hidden
import kotlinx.android.synthetic.main.row_calendar.view.cell_content_hidden
import kotlinx.android.synthetic.main.row_calendar.view.cell_content_show
import java.util.*

class CalendarAdapter(var items: List<Any> = mutableListOf()) :
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
           holder.bind()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(list: List<Any>) {
        items = list
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.card_cell_hidden.setOnClickListener { view: View? ->
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
