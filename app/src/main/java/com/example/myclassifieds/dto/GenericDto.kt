package com.example.myclassifieds.dto

import com.google.gson.annotations.SerializedName

data class GenericDto<T>(
    @SerializedName("results") val result: MutableList<T>
)