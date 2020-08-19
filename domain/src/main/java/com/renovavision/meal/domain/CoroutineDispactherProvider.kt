package com.renovavision.meal.domain;

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    fun mainDispatcher(): CoroutineDispatcher
    fun ioDispatcher(): CoroutineDispatcher
}
