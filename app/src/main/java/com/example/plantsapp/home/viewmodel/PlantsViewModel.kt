package com.example.plantsapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PlantsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.domain.model.Result
import com.example.domain.usecase.GetPlansUseCase

const val TAG ="PlantsViewModel"

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val getPlansUseCase: GetPlansUseCase
): ViewModel() {

    private val _Plants: MutableStateFlow<Result<PlantsResponse>?> = MutableStateFlow(null)
    val plants: StateFlow<Result<PlantsResponse>?> = _Plants

    fun getPlants(pageNumber: Int){
        viewModelScope.launch {
            _Plants.value = Result.Loading  // Set loading state
            try {
                val result = getPlansUseCase(pageNumber)
                _Plants.value = result
                Log.d(TAG, "getPlants: $result")
            } catch (e: Exception) {
                _Plants.value = Result.Error("Failed to fetch Plants", e)
                Log.e(TAG, "getPlants Error: ${e.message}")
            }
        }
    }


}