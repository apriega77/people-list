package com.apriega77.abstraction

import com.apriega77.model.People

interface PeopleRepository {
    suspend fun getPeopleList(): List<People>
}