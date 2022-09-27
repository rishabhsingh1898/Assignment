package com.rishabhsingh.asteroidneo.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.rishabhsingh.asteroidneo.R
import com.rishabhsingh.asteroidneo.contracts.Contracts
import com.rishabhsingh.asteroidneo.databinding.ActivityMainBinding
import com.rishabhsingh.asteroidneo.presenter.MainActivityPresenter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main),Contracts.View {

    private lateinit var binding : ActivityMainBinding
    private var mPresenter: MainActivityPresenter? = null
    private var startDate :String? = null
    private var endDate :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPresenter = MainActivityPresenter(this)
    }

    private fun updateStartDateText(myCalender:Calendar) {
        var format = "yyyy-MM-dd"
        var sdf = SimpleDateFormat(format, Locale.UK)
        startDate = sdf.format(myCalender.time)
        binding.tvStartDate.text = startDate
    }

    private fun updateEndDateText(myCalender:Calendar) {
        var format = "yyyy-MM-dd"
        var sdf = SimpleDateFormat(format, Locale.UK)
        endDate = sdf.format(myCalender.time)
        binding.tvEndDate.text = endDate
    }

    override fun initView() {

        val calendar = Calendar.getInstance()
        val startDatePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateStartDateText(calendar)

        }

        val endDatePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateEndDateText(calendar)

        }

        binding.apply {
            btnStartDate.setOnClickListener {
                DatePickerDialog(
                    root.context, startDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            btnEndDate.setOnClickListener {
                DatePickerDialog(
                    root.context, endDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            btnSubmit.setOnClickListener{
                progressBar.isVisible = true
                if (checkDateValidity(startDate, endDate)) {

                    startDate?.let { it1 -> endDate?.let { it2 -> mPresenter?.requestFeed(it1, it2) } }
                } else {
                    progressBar.isVisible = false
                }

            }
        }
    }

    private fun checkDateValidity(startDate: String?, endDate: String?):Boolean {
        if((startDate == null) || (endDate == null) || startDate.isEmpty() || endDate.isEmpty()){
            Toast.makeText(this,"Please select the dates",Toast.LENGTH_SHORT).show()
            return false
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val start = sdf.parse(startDate)
        val end = sdf.parse(endDate)
        val timeDifference: Long = end.getTime() - start.getTime()
        val daysDifference: Long = timeDifference / (1000 * 60 * 60 * 24) % 365
        if (daysDifference > 10) {
            Toast.makeText(this, "Select date range of max 10 days", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    override fun updateData(fastest :String, average : String, closest : String) {
        binding.apply {
            tvFastestAsteroid.text = fastest
            tvClosestAsteroid.text = closest
            tvAverageSizeAsteroid.text = average
            progressBar.isVisible = false
        }
    }

}