package com.naldana.dummydictionary.viewmodel

import androidx.lifecycle.LiveData
import com.naldana.dummydictionary.data.model.Word
import java.lang.Exception

sealed class WordUiState(){
    object Loading: WordUiState()
    class Error(val exception: Exception): WordUiState()
    data class Success(val word: LiveData<List<Word>>): WordUiState()
}