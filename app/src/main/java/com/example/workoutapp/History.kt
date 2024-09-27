package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class History : AppCompatActivity() {

    private var binding: ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarHistoryActivity)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        val historyDao = (application as WorkOutApp).db.historyDao()

        getAllDates(historyDao)








    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getAllDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{
               if(it.isNotEmpty()){
                   binding?.tvHistory?.visibility= View.VISIBLE
                   binding?.rvHistory?.visibility=View.VISIBLE
                   binding?.tvNoDataAvailable?.visibility=View.INVISIBLE

                   binding?.rvHistory?.layoutManager = LinearLayoutManager(this@History)

                   val dates = ArrayList<String>()
                   for(date in it){
                       dates.add(date.date)
                   }

                   val historyAdapter = HistoryAdapter(dates)
                   binding?.rvHistory?.adapter = historyAdapter


               }else{
                   binding?.tvHistory?.visibility= View.INVISIBLE
                   binding?.rvHistory?.visibility=View.INVISIBLE
                   binding?.tvNoDataAvailable?.visibility=View.VISIBLE



               }
            }
        }
    }
}