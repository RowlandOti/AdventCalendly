package com.rowland.adventcalendly.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rowland.adventcalendly.AdventApp
import com.rowland.adventcalendly.R
import com.rowland.adventcalendly.data.AdventDayEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_calendar_hidden.view.cell_content_hidden
import kotlinx.android.synthetic.main.item_calendar_hidden.view.tv_gift_value
import kotlinx.android.synthetic.main.item_calendar_shown.view.cell_content_shown
import kotlinx.android.synthetic.main.item_calendar_shown.view.tv_front_day_num
import kotlinx.android.synthetic.main.item_calendar_shown.view.tv_front_month


import kotlinx.android.synthetic.main.row_calendar.view.card_cell

import timber.log.Timber

class CalendarAdapter(var items: List<AdventDayEntity> = mutableListOf()) :
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

    fun addData(list: List<AdventDayEntity>) {
        items = list
        //items = list.shuffled()

        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(adventDayEntity: AdventDayEntity) {

            itemView.cell_content_shown.tv_front_day_num.text = adventDayEntity.day.toString()
            itemView.cell_content_shown.tv_front_month.text = adventDayEntity.month

            itemView.cell_content_hidden.tv_gift_value.text =
                itemView.context.getString(R.string.advent_day_gift_value, adventDayEntity.value)

            if (!adventDayEntity.isOpenable) {
                itemView.card_cell.cell_content_hidden.visibility = View.GONE
                itemView.card_cell.cell_content_shown.visibility = View.VISIBLE
            }

            if (adventDayEntity.isRedeemed) {
                itemView.card_cell.cell_content_hidden.visibility = View.VISIBLE
                itemView.card_cell.cell_content_shown.visibility = View.GONE
            }


            itemView.card_cell.setOnClickListener { view: View? ->
                if (adventDayEntity.isOpenable) {
                    view!!.cell_content_hidden.visibility = View.VISIBLE
                    view.cell_content_shown.visibility = View.GONE

                    adventDayEntity.isRedeemed = true

                    AdventApp.INSTANCE.database.adventDao().update(adventDayEntity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Timber.d(it.toString())
                        }, {
                            Timber.d(it.toString())
                        })
                } else {
                    Toast.makeText(
                        itemView.context,
                        "Sorry, you cannot reddem at this time",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
