package com.art.geoquiz.model

import androidx.annotation.StringRes

data class QuizModel(
    @StringRes val textResId: Int,
    val answer: Boolean,
    val img: Int
)