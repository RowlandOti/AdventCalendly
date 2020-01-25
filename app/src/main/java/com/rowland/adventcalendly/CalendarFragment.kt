package com.rowland.adventcalendly


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CalendarFragment : Fragment() {

   private lateinit var recyclerView : RecyclerView


    companion object {
        fun newInstance(args: Bundle?): Fragment {
            val frag = CalendarFragment()
            frag.arguments = args
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_calendar)

        val items = ArrayList<Any>()

        for (i in 1 until 24) {
            items.add(Any())
        }

        val adapter = CalendarAdapter()
        adapter.addData(items)
        recyclerView.layoutManager = GridLayoutManager(activity, 3 , RecyclerView.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

}
