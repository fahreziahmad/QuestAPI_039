package com.example.questapi_039.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.questapi_039.modeldata.DetailSiswa
import com.example.questapi_039.modeldata.UIStateSiswa
import com.example.questapi_039.modeldata.toDataSiswa
import com.example.questapi_039.repositori.RepositoryDataSiswa

class EntryViewModel(private val repositoryDataSiswa: RepositoryDataSiswa) : ViewModel() {
