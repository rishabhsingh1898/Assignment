package com.rishabhsingh.asteroidneo.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.rishabhsingh.asteroidneo.R
import com.rishabhsingh.asteroidneo.api.NasaClient
import com.rishabhsingh.asteroidneo.contracts.Contracts
import com.rishabhsingh.asteroidneo.databinding.ActivityMainBinding
import com.rishabhsingh.asteroidneo.presenter.MainActivityPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main),Contracts.View {

    private lateinit var binding : ActivityMainBinding
    private var mPresenter: MainActivityPresenter? = null
    private lateinit var startDate :String
    private lateinit var endDate :String

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
                mPresenter?.requestFeed(startDate, endDate)

            }
        }
    }

    override fun updateData(fastest :String, average : String, minimum : String) {
        binding.apply {
            tvFastestAsteroid.text = fastest
            tvClosestAsteroid.text = minimum
            tvAverageSizeAsteroid.text = average
            progressBar.isVisible = false
        }
    }

}