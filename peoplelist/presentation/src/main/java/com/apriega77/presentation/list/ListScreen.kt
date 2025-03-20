package com.apriega77.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apriega77.model.People
import com.apriega77.model.PeopleDetail
import com.apriega77.presentation.component.PeopleItem

@Composable
fun ListScreen(list: List<People>, onItemClicked: (PeopleDetail) -> Unit) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(list, key = { list -> list.id }) { people ->
            PeopleItem(
                modifier = Modifier.padding(vertical = 16.dp),
                people = people
            ) { peopleDetail ->
                onItemClicked(peopleDetail)
            }
        }
    }
}