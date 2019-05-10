package com.emi.kotlinmvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: RecipeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recipeViews)

        val layoutmanager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutmanager
        val adapter = RecipeAdapter(applicationContext)
        recyclerview.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
        viewModel.recipesAll.observe(this, Observer {
            recipes -> recipes?.let{
            adapter.updateAdapter(it)
        }

        })

    }
}
