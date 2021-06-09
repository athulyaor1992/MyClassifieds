package com.example.myclassifieds.network



import com.example.myclassifieds.dto.GenericDto
import com.example.myclassifieds.model.Classify
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("default/dynamodb-writer")
    suspend fun getClassifieds(): Response<GenericDto<Classify?>>


}