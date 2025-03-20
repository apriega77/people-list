package com.apriega77.presentation

import com.apriega77.model.People
import com.apriega77.model.PeopleDetail

data class PeopleListState(
    val peopleList: List<People> = emptyList(),
    val peopleDetail: PeopleDetail = PeopleDetail("", emptyList())
)

sealed interface PeopleListEvent {
    data class NavigateToDetail(val peopleDetail: PeopleDetail) : PeopleListEvent
    data object GetPeopleList : PeopleListEvent
}

sealed interface PeopleListEffect {
    data object NavigateToDetail : PeopleListEffect
}

enum class PeopleListNav(val route: String) {
    LIST("/list"),
    DETAIL("/detail")
}