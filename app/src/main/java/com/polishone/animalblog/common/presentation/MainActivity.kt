package com.polishone.animalblog.common.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.polishone.animalblog.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /**
     * decare variables and views
     */
    val TAG = "Main_Activity"
    private val aniBlogViewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aniBlogViewModel.getAniBlog()

        /**
         * observe the view modele
         */
        lifecycleScope.launchWhenStarted {
            aniBlogViewModel.aniBlog.collect{
                Log.d(TAG, "the result: ${it.data}")
            }
        }
    }
}