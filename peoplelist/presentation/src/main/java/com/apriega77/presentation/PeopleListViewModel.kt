package com.apriega77.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apriega77.usecase.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(private val getPeopleListUseCase: GetPeopleListUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(PeopleListState())
    val state: StateFlow<PeopleListState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<PeopleListEvent>()


    val effect = MutableSharedFlow<PeopleListEffect>()

    init {
        _event.onEach {
            mapEvent(it)
        }.launchIn(viewModelScope)
    }

    fun sendEvent(event: PeopleListEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun mapEvent(event: PeopleListEvent) {
        viewModelScope.launch {
            when (event) {
                PeopleListEvent.GetPeopleList -> {
                    val list = getPeopleListUseCase.invoke(Unit)
                    Log.d("TEST123", list.toString())
                    _state.update {
                        it.copy(peopleList = list)
                    }
                }

                is PeopleListEvent.NavigateToDetail -> {
                    _state.update {
                        it.copy(peopleDetail = event.peopleDetail)
                    }
                    effect.emit(PeopleListEffect.NavigateToDetail)
                }
            }
        }
    }
}