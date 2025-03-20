package com.apriega77.usecase.base

abstract class BaseUseCase<Args, Result> {
    protected abstract suspend fun build(args: Args): Result

    suspend operator fun invoke(args: Args): Result {
        return build(args)
    }
}
