package com.example.rickmortyquiz.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortyquiz.R


class GameViewModel : ViewModel() {

    private var answeredCorrect = 0
    private var attempt = 0
    private var questionIndex = 0
    private lateinit var questionBank:MutableList<Question>

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int> get() = _score

    private val _enableButtons = MutableLiveData<Boolean>(true)
    val enableButtons:LiveData<Boolean> get() = _enableButtons

    private val _tchecked = MutableLiveData<Boolean>(false)
    val tchecked:LiveData<Boolean> get() = _tchecked
    private val _fchecked = MutableLiveData<Boolean>(false)
    val fchecked:LiveData<Boolean> get() = _fchecked
    private val _correct = MutableLiveData<Boolean>(false)
    val correct:LiveData<Boolean> get() = _correct

    private val _questionId = MutableLiveData<Int>()
    val questionId:LiveData<Int> get() = _questionId

    init {
        newGame()
    }

    fun newGame() {
        answeredCorrect = 0
        attempt = 0
        questionIndex = 0
        resetQuestionBank()
        _questionId.value = questionBank[questionIndex].resourceId
    }

    private fun resetQuestionBank() {
        questionBank = mutableListOf(
                Question(R.string.question_1, false),
                Question(R.string.question_2, true),
                Question(R.string.question_3, true)
//                Question(R.string.question_4, false),
//                Question(R.string.question_5, false),
//                Question(R.string.question_6, true),
//                Question(R.string.question_7, false),
//                Question(R.string.question_8, true),
//                Question(R.string.question_9, false),
//                Question(R.string.question_10, false),
//                Question(R.string.question_11, false),
//                Question(R.string.question_12, true),
//                Question(R.string.question_13, false),
//                Question(R.string.question_14, true),
//                Question(R.string.question_15, false),
//                Question(R.string.question_16, false),
//                Question(R.string.question_17, true),
//                Question(R.string.question_18, false),
//                Question(R.string.question_19, false),
//                Question(R.string.question_20, true)
        )
        questionBank.shuffle()
    }



    fun onSelected(selected:Boolean) {
        val q = questionBank[questionIndex]
        q.attempted = true
        q.answered = selected
        _enableButtons.value = false
        _tchecked.value = selected == true
        _fchecked.value = selected == false
        _correct.value = selected == q.answer

        if (q.answer == selected) {
            _score.value = (score.value)?.plus(1)
        } else {

        }

       // System.out.println(question.value)
    }

    fun prev() {
        if (questionIndex == 0) {
            //resetList()
            questionIndex = questionBank.size-1
        } else {
            // Remove a word from the list
            questionIndex --;
        }

        updateVars()
    }

    fun next() {
        if (questionIndex == questionBank.size-1) {
            //resetList()
            questionIndex = 0
        } else {
            // Remove a word from the list
            questionIndex ++;
        }

        updateVars()
    }

    private fun updateVars() {
        _questionId.value = questionBank[questionIndex].resourceId
        val q = questionBank[questionIndex]

        _enableButtons.value = !q.attempted
        _tchecked.value = q.attempted && q.answer == q.answered
        _fchecked.value = q.attempted && q.answer != q.answered
        _correct.value = q.attempted && q.answered == q.answer
    }


    data class Question(
            val resourceId: Int,
            val answer: Boolean,
            var attempted:Boolean = false,
        var answered:Boolean = false)


}