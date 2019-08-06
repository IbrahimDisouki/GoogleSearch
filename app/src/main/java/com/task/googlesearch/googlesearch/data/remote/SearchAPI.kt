package com.task.googlesearch.googlesearch.data.remote

import com.task.googlesearch.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET
    fun search(
            @Query("key") apiKey: String = BuildConfig.API_KEY,
            @Query("cx") customSearchEngineId: String = BuildConfig.CUSTOM_SEARCH_ENGINE_ID,
            @Query("q") searchQuery: String
    )
}