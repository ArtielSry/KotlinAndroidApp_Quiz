package com.art.geoquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.art.geoquiz.databinding.ActivityMainBinding
import com.art.geoquiz.viewModel.QuizViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.trueBtn.setOnClickListener {
            checkAnswer(true)
            //btnDisabled()
            nextQuestion()
        }

        binding.falseBtn.setOnClickListener {
            checkAnswer(false)
            //btnDisabled()
            nextQuestion()
        }

        // pone la pregunta
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTv.setText(questionTextResId)
        binding.questionPositionTv.text = (quizViewModel.currentIndex + 1).toString()

        // boton cheat
        binding.cheatBtn.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)
        }
        updateQuestion()
    }

    // funcion para cambiar a la siguiente pregunta
    private fun nextQuestion() {
        quizViewModel.moveToNext()
        updateQuestion()
        // btnEnabled()
        val displayedIndex = quizViewModel.currentIndex + 1
        binding.questionPositionTv.text = displayedIndex.toString()
    }

    // update the question
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTv.setText(questionTextResId)
    }

    // check if you are right
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswers = quizViewModel.currentQuestionAnswer
        if (quizViewModel.currentIndex == quizViewModel.questionBank.size - 1) {
            val percentage =
                (quizViewModel.correctAnswers.toFloat() / quizViewModel.questionBank.size) * 100
            val intPercentage = percentage.toInt().toString()
            resetQuiz(intPercentage, quizViewModel.correctAnswers)
        }
    }

    private fun alertDialog(percentage: String, answer: Int) {
        val builder = AlertDialog.Builder(this)

        if (answer > 5) {
            builder.setTitle("Congrats!")
        } else {
            builder.setTitle("Maybe next time!")
        }
        builder.setMessage("YOU ANSWERED ${percentage}% RIGHT")
        val dialog = builder.create()
        dialog.show()
    }

    private fun resetQuiz(percentage: String, correctAnswers: Int) {
        quizViewModel.correctAnswers = 0
        updateQuestion()
        alertDialog(percentage, correctAnswers)
    }


}