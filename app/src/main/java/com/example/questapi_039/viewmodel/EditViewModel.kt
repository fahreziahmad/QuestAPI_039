package com.example.questapi_039.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_039.modeldata.DetailSiswa
import com.example.questapi_039.modeldata.UIStateSiswa
import com.example.questapi_039.modeldata.toDataSiswa
import com.example.questapi_039.modeldata.toUIStateSiswa // TAMBAHKAN IMPORT INI
import com.example.questapi_039.repositori.RepositoryDataSiswa
import com.example.questapi_039.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Mengambil ID dari argument navigasi menggunakan key yang benar
    private val idSiswa: Int = checkNotNull(savedStateHandle["id"])

