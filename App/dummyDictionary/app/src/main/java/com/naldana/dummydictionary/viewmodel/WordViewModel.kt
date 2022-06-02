package com.naldana.dummydictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.network.ApiResponse
import com.naldana.dummydictionary.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    val term = MutableLiveData("")
    val definition = MutableLiveData("")

    private val _status = MutableLiveData<WordUiState>(WordUiState.Loading)
    val status:LiveData<WordUiState>
        get() = _status

    fun getAllWords(){
        _status.value = WordUiState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.getAllWords()){
                    is ApiResponse.Error -> WordUiState.Error(response.exception)
                    is ApiResponse.Success -> WordUiState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()

                }
            )
        }
    }
    fun addWord(word:List<Word>){
        viewModelScope.launch {
            repository.addWord(word)
        }
    }

}
