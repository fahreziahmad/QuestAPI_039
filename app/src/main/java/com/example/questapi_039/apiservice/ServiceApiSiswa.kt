package com.example.questapi_039.apiservice

import com.example.questapi_039.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.*

interface ServiceApiSiswa {
    @GET("bacaTeman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postSiswa(@Body dataSiswa: DataSiswa): Response<Void>

    // Tambahan untuk Detail dan Edit
    @GET("getSiswaById.php")
    suspend fun getSiswaById(@Query("id") id: Int): DataSiswa

    @POST("updateSiswa.php")
    suspend fun updateSiswa(@Query("id") id: Int, @Body dataSiswa: DataSiswa): Response<Void>

    @DELETE("deleteSiswa.php")
    suspend fun deleteSiswa(@Query("id") id: Int): Response<Void>

}