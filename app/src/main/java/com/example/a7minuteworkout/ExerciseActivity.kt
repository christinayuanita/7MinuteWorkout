package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.data.Constants
import com.example.a7minuteworkout.model.Exercise
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration : Long = 10

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimerDuration : Long = 30

    private var exerciseList: ArrayList<Exercise>? = null
    private var currExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()

        exerciseList = Constants.defaultExerciseList()
    }

    override fun onDestroy() {
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        super.onDestroy()
    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restTimer = object: CountDownTimer(restTimerDuration * 1000, 1000){
            override fun onFinish() {
                currExercisePosition++
                setupExerciseView()
            }

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = restTimerDuration.toInt() - restProgress
                timerTxt.text = (restTimerDuration.toInt() - restProgress).toString()
            }

        }.start()
    }

    private fun setExerciseProgressBar(){
        progressBarExercise.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(exerciseTimerDuration * 1000, 1000){
            override fun onFinish() {
                if(currExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity, "Completed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration.toInt() - exerciseProgress
                exerciseTimerTxt.text = (exerciseTimerDuration.toInt() - exerciseProgress).toString()
            }

        }.start()
    }

    private fun setupRestView(){

        restView.visibility = View.VISIBLE
        exerciseView.visibility = View.GONE

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setupExerciseView(){

        restView.visibility = View.GONE
        exerciseView.visibility = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()

        exerciseImage.setImageResource(exerciseList!![currExercisePosition].getImage())
        exerciseNameTxt.text = exerciseList!![currExercisePosition].getName()

    }

}