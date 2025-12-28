package com.example.questapi_039.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.questapi_039.repositori.AplikasiDataSiswa
import com.example.questapi_039.viewmodel.DetailViewModel // Pastikan ini di-import
import com.example.questapi_039.viewmodel.EditViewModel
import com.example.questapi_039.viewmodel.EntryViewModel
import com.example.questapi_039.viewmodel.HomeViewModel

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiDataSiswa)
