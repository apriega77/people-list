package com.apriega77.data.di

import com.apriega77.abstraction.PeopleRepository
import com.apriega77.data.network.ApiService
import com.apriega77.usecase.GetPeopleListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleProvide {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetPeopleListUseCase(peopleRepository: PeopleRepository) =
        GetPeopleListUseCase(peopleRepository)


}