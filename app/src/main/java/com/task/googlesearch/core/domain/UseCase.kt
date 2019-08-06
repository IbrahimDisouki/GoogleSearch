package com.task.googlesearch.core.domain

interface UseCase<P, R> {
    fun execute(params: P? = null): R
}