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
import com.alserdar.gads2020leaderboard.adapter.MyAdapterForSkillIq
import com.alserdar.gads2020leaderboard.model.SkillIqItem
import com.alserdar.gads2020leaderboard.retrofit.the_interfaces.RetrofitForSkillIq
import com.alserdar.gads2020leaderboard.retrofit.url.TheRestUrlForSkillIq
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_skill_iq.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A placeholder fragment containing a simple view.
 */
class SkillIqFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    lateinit var mForSkillIq : RetrofitForSkillIq
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapterForSkillIq : MyAdapterForSkillIq
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
        val root = inflater.inflate(R.layout.fragment_skill_iq, container, false)
        mForSkillIq = TheRestUrlForSkillIq.retrofitForSkillIq

        root.recyclerSkillIqList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        root.recyclerSkillIqList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(requireContext()).build()

        dialog.show()

        mForSkillIq.getSkillList().enqueue(object : Callback<MutableList<SkillIqItem>> {
            override fun onFailure(call: Call<MutableList<SkillIqItem>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MutableList<SkillIqItem>>, response: Response<MutableList<SkillIqItem>>) {
                adapterForSkillIq = MyAdapterForSkillIq(requireContext(), response.body() as MutableList<SkillIqItem>)
                adapterForSkillIq.notifyDataSetChanged()
                root.recyclerSkillIqList.adapter = adapterForSkillIq

                dialog.dismiss()
            }

        })
        return root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"


        @JvmStatic
        fun newInstance(sectionNumber: Int): SkillIqFragment {
            return SkillIqFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}