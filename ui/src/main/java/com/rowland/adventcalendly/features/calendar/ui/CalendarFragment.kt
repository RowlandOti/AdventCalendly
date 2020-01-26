package com.rowland.adventcalendly.features.calendar.ui


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rowland.adventcalendly.R
import com.rowland.adventcalendly.presentation.AdventDayPayLoad
import com.rowland.adventcalendly.presentation.AdventDayViewModel
import com.rowland.adventcalendly.presentation.state.ResourceState
import com.rowland.adventcalendly.uihelper.GridOffsetDecorator
import timber.log.Timber
import javax.inject.Inject


class CalendarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adventViewModel: AdventDayViewModel


    @Inject
    lateinit var adapter: CalendarAdapter

    @Inject
    lateinit var factory : ViewModelProvider.Factory

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

        adventViewModel = ViewModelProviders.of(this, factory).get(AdventDayViewModel::class.java)

        adapter = CalendarAdapter()

        recyclerView.layoutManager = GridLayoutManager(
            activity,
            calculateNoOfColumns(activity!!),
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.addItemDecoration(
            GridOffsetDecorator(
                activity!!,
                R.dimen.item_offset
            )
        );
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = adapter

        adventViewModel.getListOfAdventDays().observe(activity!!, Observer {
            handleDataState(it.status, it.data, it.message)
        })
    }

    private fun handleDataState(
        resourceState: ResourceState,
        data: List<AdventDayPayLoad>?,
        message: String?
    ) {
        when (resourceState) {
            ResourceState.LOADING -> {

            }
            ResourceState.SUCCESS -> {
                Timber.d("Days in list; $data")
                adapter.addData(data!!)
            }
            ResourceState.ERROR -> {
                Timber.d(message)
            }
        }
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
