package com.example.questapi_039.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_039.modeldata.DataSiswa
import com.example.questapi_039.repositori.RepositoryDataSiswa
import com.example.questapi_039.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    data class Success(val dataSiswa: DataSiswa) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}


class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getDetailSiswa()
    }

    fun getDetailSiswa() {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                DetailUiState.Success(repositoryDataSiswa.getSatuSiswa(idSiswa))
            } catch (e: Exception) {
                DetailUiState.Error
            }
        }
    }
