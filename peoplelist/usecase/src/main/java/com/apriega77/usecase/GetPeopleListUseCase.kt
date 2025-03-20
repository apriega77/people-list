package com.apriega77.usecase

import com.apriega77.abstraction.PeopleRepository
import com.apriega77.model.People
import com.apriega77.usecase.base.BaseUseCase

class GetPeopleListUseCase(private val peopleRepository: PeopleRepository) :
    BaseUseCase<Unit, List<People>>() {
    override suspend fun build(args: Unit): List<People> {
        return peopleRepository.getPeopleList()
    }
}