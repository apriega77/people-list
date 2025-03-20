package com.apriega77.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.apriega77.presentation.component.navigationComponent
import com.apriega77.presentation.detail.DetailScreen
import com.apriega77.presentation.list.ListScreen
import kotlinx.coroutines.flow.collect

@Composable
fun PeopleListMainScreen() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<PeopleListViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendEvent(PeopleListEvent.GetPeopleList)
        viewModel.effect.collect {
            when (it) {
                PeopleListEffect.NavigateToDetail -> {
                    navController.navigate(PeopleListNav.DETAIL.route)
                }
            }
        }
    }
    Scaffold { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = PeopleListNav.LIST.route
        ) {
            navigationComponent(PeopleListNav.LIST.route) {
                ListScreen(state.peopleList) { peopleDetail ->
                    viewModel.sendEvent(PeopleListEvent.NavigateToDetail(peopleDetail))
                }
            }

            navigationComponent(PeopleListNav.DETAIL.route) {
                DetailScreen(state.peopleDetail)
            }
        }
    }
}