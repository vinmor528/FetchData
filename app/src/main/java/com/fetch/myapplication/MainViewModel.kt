package com.fetch.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.myapplication.entities.Candidate
import com.fetch.myapplication.entities.LoadStatus
import com.fetch.myapplication.entities.Result
import com.fetch.myapplication.repository.FetchRepository
import com.fetch.myapplication.utility.filterByListId
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FetchRepository): ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    private val _candidateList = MutableLiveData<List<Candidate>>()
    private val _loading = MutableLiveData<LoadStatus>()

    val candidateList: LiveData<List<Candidate>>
    get() = _candidateList
    val loading: LiveData<LoadStatus>
    get() = _loading

    fun loadData() {
        viewModelScope.launch {
            _loading.postValue(LoadStatus.LOADING)
            when (val result = repository.fetchCandidateList()) {
                is Result.Success -> {
                    Log.i(TAG, "Data: ${result.data}")
                    _candidateList.postValue(result.data.filterByListId())
                    _loading.postValue(LoadStatus.SUCCESS)
                }
                is Result.Failure -> {
                    Log.i(TAG, "Error: ${result.exception}")
                    _loading.postValue(LoadStatus.FAILURE)
                }
            }
        }
    }
}