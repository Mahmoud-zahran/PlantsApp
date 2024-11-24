package com.example.plantsapp.home.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private var nextPageUrl: String? = null // Tracks the next page URL
    private var prevPageUrl: String? = null // Tracks the next page URL
    private var lastPageUrl: String? = null // Tracks the next page URL

    var isLoading by mutableStateOf(false)
        private set
    var selectedTabIndex by mutableStateOf(0)
        private set

    fun setSelectedTab(index: Int) {
        selectedTabIndex = index
        nextPageUrl = null // Reset pagination for the new tab
        prevPageUrl = null
        lastPageUrl = null
        _Plants.value = null // Clear current list for the new tab
        getPlants(getDistributionPathForTab(index), 1) // Start fresh

    }
    fun getDistributionPathForTab(index: Int): String {
        return when (index) {
            0 -> "" // All
            1 -> "distributions/pal/"
            2 -> "distributions/sud/"
            3 -> "distributions/mya/"
            4 -> "distributions/tcs/"
            5 -> "distributions/uzb/"
            else -> ""
        }
    }
    fun getPlants(distributionPath: String?, // Nullable path parameter
         pageNumber: Int){
        viewModelScope.launch {
            if (pageNumber==1) {
                _Plants.value = Result.Loading  // Set loading state
            }
            isLoading = true

            try {

                val result = getPlansUseCase(distributionPath,pageNumber)
                if (result is Result.Success) {
                    val response = result.data
                    val newPlants = response?.data ?: emptyList()

                    val currentResult = _Plants.value
                if (currentResult is Result.Success) {
                    // Combine existing and new data
                    val currentPlants = currentResult.data.data
                    val updatedPlants = currentPlants + newPlants
                    val updatedResponse = currentResult.data.copy(data = updatedPlants)

                    _Plants.value = Result.Success(updatedResponse)
                } else {
                    // Initialize with new data if no existing data
                    _Plants.value = Result.Success(response)
                }
                    nextPageUrl = response.links.next // Update next page URL
                    prevPageUrl = response.links.prev // Update prev page URL
                    lastPageUrl = response.links.last // Update last page URL

                    Log.d(TAG, "getPlants: $result")
                }
            } catch (e: Exception) {
                _Plants.value = Result.Error("Failed to fetch Plants", e)
                Log.e(TAG, "getPlants Error: ${e.message}")
            }finally {
//                isLoading = false
            }
        }
    }
    fun reloadHome() {
        // Reset the state
        setSelectedTab(0) // Select the first tab
        getPlants(distributionPath = "", pageNumber = 1) // Fetch data for the first tab
    }

    fun loadNextPage() {
        if (!nextPageUrl.isNullOrEmpty()) {
            val nextPageNum = nextPageUrl?.substringAfter("page=")?.toInt()?:1
            val lastPageNum = lastPageUrl?.substringAfter("page=")?.toInt()?:1
            if (nextPageNum == 1){
                _Plants.value = null
            }
            if (nextPageNum <= lastPageNum ) {
                getPlants(getDistributionPathForTab(selectedTabIndex), nextPageNum)
            }else{
                isLoading =false
            }
        }
    }
    fun loadPrevPage() {
        if (!prevPageUrl.isNullOrEmpty()) {
            val prevPageNum = prevPageUrl?.substringAfter("page=")?.toInt()?:1
            if (prevPageNum == 1){
                _Plants.value = null
            }
            if (prevPageNum <= 1 ) {
                getPlants(getDistributionPathForTab(selectedTabIndex), prevPageNum)
            }else{
                isLoading =false
            }
        }
    }
}