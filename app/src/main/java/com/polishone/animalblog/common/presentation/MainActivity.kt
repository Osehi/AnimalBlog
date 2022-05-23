package com.polishone.animalblog.common.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polishone.animalblog.R
import com.polishone.animalblog.common.presentation.adapter.AniBlogAdapter
import com.polishone.animalblog.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /**
     * decare variables and views
     */
    val TAG = "Main_Activity"
    private lateinit var binding: ActivityMainBinding
    lateinit var myRecyclerView:RecyclerView
    lateinit var aniBlogAdapter: AniBlogAdapter
    private val aniBlogViewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        /**
         * initialize variables and views
         */
        myRecyclerView = binding.mainActivityRecyclerview
        myRecyclerView.layoutManager = LinearLayoutManager(this)


        /**
         * the viewModel is called
         */
        aniBlogViewModel.getAniBlog()

        /**
         * observe the view modele
         */
        lifecycleScope.launchWhenStarted {
            aniBlogViewModel.aniBlog.collect{
                Log.d(TAG, "the result: ${it.data}")
                aniBlogAdapter = AniBlogAdapter(it.data, AniBlogAdapter.OnClickListener {
                    Log.d(TAG, "this is the message: ${it}")
                })
                myRecyclerView.adapter = aniBlogAdapter
                aniBlogAdapter.notifyDataSetChanged()
            }
        }
    }
}