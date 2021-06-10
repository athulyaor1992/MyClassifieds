package com.example.myclassifieds.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myclassifieds.R
import com.example.myclassifieds.adapter.ClassifyAdapter
import com.example.myclassifieds.model.Classify
import com.example.myclassifieds.util.Status
import com.example.myclassifieds.util.snackbar
import com.example.myclassifieds.viewmodel.ClassifyViewModel
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(),ClassifyAdapter.CellClickListener  {

    private lateinit var viewModel: ClassifyViewModel
    private lateinit var adapter: ClassifyAdapter


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ClassifyViewModel::class.java)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        classifyView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = ClassifyAdapter(arrayListOf(),this)
        classifyView.adapter = adapter

    }

    private fun setupObservers() {
        val view = window.decorView.rootView
        viewModel.getClassifieds().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        classifyView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(resource.data!!.body()!!.result  as ArrayList<Classify>) }
                    }
                    Status.ERROR -> {
                        classifyView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        view.snackbar(this,resource.message.toString())
                    }
                    Status.LOADING -> {
                        classifyView.visibility = View.VISIBLE
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private  fun retrieveList(users: ArrayList<Classify>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }


    }

    override fun onCellClickListener(data: Classify) {

        val intent = Intent (this@MainActivity, DetailActivity::class.java)
        intent.putExtra("classify", data)
        startActivity(intent)

    }


}