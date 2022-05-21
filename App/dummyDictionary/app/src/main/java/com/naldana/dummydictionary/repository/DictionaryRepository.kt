package com.naldana.dummydictionary.repository

import androidx.lifecycle.LiveData
import com.naldana.dummydictionary.data.model.DummyDictionaryDatabase
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.network.ApiResponse
import com.naldana.dummydictionary.network.WordService
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository(
    database: DummyDictionaryDatabase,
    private val api:WordService,
){
    private val wordDoa = database.wordDao()
    suspend fun getAllWords():ApiResponse<LiveData<List<Word>>> {
        return try{
            val response = api.getAllWord()
            //Use Database as cache
            if(response.count > 0){
                wordDoa.insertWord(response.word)
            }
            ApiResponse.Success(data = wordDoa.getAllWords())
        } catch (e:HttpException){
            ApiResponse.Error(exception = e)
        } catch (e:IOException){
            ApiResponse.Error(exception = e)
        }

    }
    suspend fun addWord(word:Word){
        wordDoa.insertWord(word)
    }
}


