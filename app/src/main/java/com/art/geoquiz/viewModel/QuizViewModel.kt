package com.art.geoquiz.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.art.geoquiz.R
import com.art.geoquiz.model.QuizModel

class QuizViewModel : ViewModel() {

    val questionBank = listOf(
        QuizModel(R.string.question1, true, R.drawable.ic_prueba),
        QuizModel(R.string.question2, true, R.drawable.ic_prueba),
        QuizModel(R.string.question3, false, R.drawable.ic_prueba),
        QuizModel(R.string.question4, true, R.drawable.ic_prueba),
        QuizModel(R.string.question5, true, R.drawable.ic_prueba),
        QuizModel(R.string.question6, true, R.drawable.ic_prueba),
        QuizModel(R.string.question7, true, R.drawable.ic_prueba),
        QuizModel(R.string.question8, true, R.drawable.ic_prueba),
        QuizModel(R.string.question9, true, R.drawable.ic_prueba),
        QuizModel(R.string.question10, true, R.drawable.ic_prueba),
    )


    var correctAnswers: Int = 0
    var currentIndex: Int = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

}