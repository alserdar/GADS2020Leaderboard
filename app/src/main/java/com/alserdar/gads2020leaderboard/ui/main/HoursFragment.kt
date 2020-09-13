package com.alserdar.gads2020leaderboard.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alserdar.gads2020leaderboard.R
import com.alserdar.gads2020leaderboard.adapter.MyAdapterForHours
import com.alserdar.gads2020leaderboard.model.HoursItem
import com.alserdar.gads2020leaderboard.retrofit.the_interfaces.RetrofitForHours
import com.alserdar.gads2020leaderboard.retrofit.url.TheRestUrlForHours
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_hours.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A placeholder fragment containing a simple view.
 */
class HoursFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    lateinit var mForHours : RetrofitForHours
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapterForHours : MyAdapterForHours
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hours, container, false)
        mForHours = TheRestUrlForHours.retrofitForHours

        root.recyclerHoursList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        root.recyclerHoursList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireContext()).build()

        dialog.show()

        mForHours.getHoursList().enqueue(object : Callback<MutableList<HoursItem>> {
            override fun onFailure(call: Call<MutableList<HoursItem>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MutableList<HoursItem>>, response: Response<MutableList<HoursItem>>) {
                adapterForHours = MyAdapterForHours(requireContext(), response.body() as MutableList<HoursItem>)
                adapterForHours.notifyDataSetChanged()
                root.recyclerHoursList.adapter = adapterForHours

                dialog.dismiss()
            }

        })
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): HoursFragment {
            return HoursFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}