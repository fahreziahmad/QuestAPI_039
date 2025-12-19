package com.example.questapi_039.repositori

import com.example.questapi_039.apiservice.ServiceApiSiswa
import com.example.questapi_039.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {

    suspend fun getDataSiswa(): List<DataSiswa>

    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>

    // suspend fun getSatuSiswa(id:Int): DataSiswa
    // suspend fun editSatuSiswa(id:Int, dataSiswa: DataSiswa): Response<Void>
    // suspend fun hapusSatuSiswa(id:Int): Response<Void>
}
class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {

    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postSiswa(dataSiswa)
}
