package com.example.rickmortyquiz.screens.over

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameOverViewModel(finalScore:Int) : ViewModel() {
    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int> get() = _score

    init {
        //Log.i("ScoreViewModel", "Final score is $finalScore")
        _score.value = finalScore
    }
}