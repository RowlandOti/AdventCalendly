package com.rowland.adventcalendly.ui


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rowland.adventcalendly.AdventApp
import com.rowland.adventcalendly.R
import com.rowland.adventcalendly.uihelper.GridOffsetDecorator
import timber.log.Timber


class CalendarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adventViewModel: AdventDayViewModel


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

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_calendar)

        adventViewModel =  ViewModelProviders.of(this).get(AdventDayViewModel::class.java)

        val adapter = CalendarAdapter()

        recyclerView.layoutManager = GridLayoutManager(
            activity,
            calculateNoOfColumns(activity!!),
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.addItemDecoration(GridOffsetDecorator(activity!!,
            R.dimen.item_offset
        ));
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = adapter

        adventViewModel.getListOfAdventDays().observe(activity!!, Observer {
            adapter.addData(it)
            Timber.d("Days in list; ${it}")
        })
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
