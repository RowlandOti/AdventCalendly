package com.rowland.adventcalendly


import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rowland.adventcalendly.data.AdventDay
import com.rowland.adventcalendly.uihelper.GridOffsetDecorator


class CalendarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView


    companion object {
        fun newInstance(args: Bundle?): Fragment {
            val frag = CalendarFragment()
            frag.arguments = args
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_calendar)

        val items = ArrayList<AdventDay>()

        for (i in 1 until 24) {
            items.add(AdventDay(i, false))
        }

        val adapter = CalendarAdapter()
        adapter.addData(items)
        recyclerView.layoutManager = GridLayoutManager(
            activity,
            calculateNoOfColumns(activity!!),
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.addItemDecoration(GridOffsetDecorator(activity!!, R.dimen.item_offset));
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 180
        var noOfColumns = (dpWidth / scalingFactor).toInt()
        if (noOfColumns < 2) noOfColumns = 2
        return noOfColumns
    }
}
