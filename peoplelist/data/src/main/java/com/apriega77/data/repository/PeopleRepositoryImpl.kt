package com.apriega77.data.repository

import com.apriega77.abstraction.PeopleRepository
import com.apriega77.data.mapper.PeopleMapper
import com.apriega77.data.network.ApiService
import com.apriega77.model.People
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: PeopleMapper
) :
    PeopleRepository {
    override suspend fun getPeopleList(): List<People> {
        val response = apiService.getPeopleList()
        return mapper.mapPeopleList(response)
    }
}