package com.lablabla.blablawatering.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lablabla.blablawatering.domain.repository.WateringRepository
import com.lablabla.blablawatering.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: WateringRepository
): ViewModel() {

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        getStations()
    }

    private fun getStations(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getStations(fetchFromRemote)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { stations ->
                                _state.update {
                                    it.copy(stations = stations)
                                }
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }
                    }

                }
        }
    }
}