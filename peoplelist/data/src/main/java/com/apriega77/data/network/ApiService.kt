package com.apriega77.data.network

import com.apriega77.data.network.model.PeopleDetailResponse
import retrofit2.http.GET

interface ApiService {

    @GET("test")
    suspend fun getPeopleList(): List<PeopleDetailResponse>
}