package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_b_m_i.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(toolbar_bmi_activity)

        val actionBar = supportActionBar

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "CALCULATE BMI"
        }

        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        calculateUnitsBtn.setOnClickListener {
            calculateBMI()
        }

        makeVisibleMetricUnitsView()
        unitsRg.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.metricUnitsRb){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }
        }
    }

    private fun calculateBMI(){
        if(currVisibleView.equals(METRIC_UNITS_VIEW)){
            calculateMetricUnitsBMI()
        }else{
            calculateUSUnitsBMI()
        }
    }

    private fun calculateMetricUnitsBMI(){
        if(validateMetricUnits()){
            val heightValue: Float = metricUnitHeightEt.text.toString().toFloat() / 100
            val weightValue: Float = metricUnitWeightEt.text.toString().toFloat()

            val bmi = weightValue / (heightValue*heightValue)
            displayBMIResult(bmi)
        }else{
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateUSUnitsBMI(){
        if(validateUSUnits()){
            val usHeightValueFeet: String = usUnitHeightFeetEt.text.toString()
            val usHeightValueInch: String = usUnitHeightInchEt.text.toString()
            val usWeightValue: Float = usUnitWeightEt.text.toString().toFloat()

            val usHeightValue = usHeightValueInch.toFloat() + usHeightValueFeet.toFloat() * 12

            val bmi = 703 * (usWeightValue / (usHeightValue * usHeightValue))
            displayBMIResult(bmi)
        }else{
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeVisibleUSUnitsView(){
        currVisibleView = US_UNITS_VIEW
        metricUnitHeightTil.visibility = View.GONE
        metricUnitWeightTil.visibility = View.GONE

        usUnitWeightEt.text!!.clear()
        usUnitHeightFeetEt.text!!.clear()
        usUnitHeightInchEt.text!!.clear()

        usUnitWeightTil.visibility = View.VISIBLE
        usUnitsHeight.visibility = View.VISIBLE

        displayBMIResult.visibility = View.GONE
    }

    private fun makeVisibleMetricUnitsView(){
        currVisibleView = METRIC_UNITS_VIEW
        metricUnitHeightTil.visibility = View.VISIBLE
        metricUnitWeightTil.visibility = View.VISIBLE

        metricUnitHeightEt.text!!.clear()
        metricUnitWeightEt.text!!.clear()

        usUnitWeightTil.visibility = View.GONE
        usUnitsHeight.visibility = View.GONE

        displayBMIResult.visibility = View.GONE
    }

    private fun displayBMIResult(bmi: Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        displayBMIResult.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        BMIValueTxt.text = bmiValue
        BMITypeTxt.text = bmiLabel
        BMIDescriptionTxt.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true

        when {
            metricUnitWeightEt.text.toString().isEmpty() -> isValid = false
            metricUnitHeightEt.text.toString().isEmpty() -> isValid = false
        }

        return isValid
    }

    private fun validateUSUnits(): Boolean{
        var isValid = true

        when {
            usUnitWeightEt.text.toString().isEmpty() -> isValid = false
            usUnitHeightFeetEt.text.toString().isEmpty() -> isValid = false
            usUnitHeightInchEt.text.toString().isEmpty() -> isValid = false
        }

        return isValid
    }
}