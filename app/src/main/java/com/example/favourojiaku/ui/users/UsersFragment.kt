package com.example.favourojiaku.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favourojiaku.R
import com.example.favourojiaku.adapters.UserAdapter
import com.example.favourojiaku.api.Status
import com.example.favourojiaku.util.AppCenter
import kotlinx.android.synthetic.main.fragment_user.*

class UsersFragment : Fragment() {

    private val usersAdapter = UserAdapter()
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = AppCenter.getViewModelFactory(activity!!.application)
        usersViewModel = ViewModelProvider(this, factory).get(UsersViewModel::class.java)

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        observe(usersViewModel)
        usersViewModel.fetchData()

    }


    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(context)
        recycler_view.layoutManager = manager
        recycler_view.adapter = usersAdapter
    }

    private fun observe(viewModel: UsersViewModel){
        viewModel.users.observe(this, Observer { res ->
            //Log.d("Attendance", "List size ${res}")
            when(res.status) {
                Status.SUCCESS -> {
                    Log.d("AttendanceFragment", "submitSize ${res.data}")
                    usersAdapter.setUser(res.data!!)
                    recycler_view.visibility = View.VISIBLE
                    placeholder.visibility = View.GONE
                }
            }

            val adapterSize = usersAdapter.itemCount
            Log.d("User", "Adapter size $adapterSize")
        })
    }

}
