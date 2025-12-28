package com.example.questapi_039.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect // Tambahkan ini
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questapi_039.R
import com.example.questapi_039.modeldata.DataSiswa
import com.example.questapi_039.uicontroller.route.DestinasiHome
import com.example.questapi_039.viewmodel.HomeViewModel
import com.example.questapi_039.viewmodel.StatusUiSiswa
import com.example.questapi_039.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    // AUTO REFRESH: Memanggil loadSiswa setiap kali screen ini ditampilkan kembali
    LaunchedEffect(Unit) {
        viewModel.loadSiswa()
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = { viewModel.loadSiswa() } // HUBUNGKAN KE TOMBOL REFRESH
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_siswa)
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            statusUiSiswa = viewModel.listSiswa,
            onSiswaClick = navigateToItemUpdate,
            retryAction = viewModel::loadSiswa,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun HomeBody(
    statusUiSiswa: StatusUiSiswa,
    onSiswaClick: (Int) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (statusUiSiswa) {
        is StatusUiSiswa.Loading -> LoadingScreen(modifier.fillMaxSize())
        is StatusUiSiswa.Success -> {
            if (statusUiSiswa.siswa.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data siswa")
                }
            } else {
                ListSiswa(
                    siswa = statusUiSiswa.siswa,
                    onSiswaClick = onSiswaClick,
                    modifier = modifier
                )
            }
        }
        is StatusUiSiswa.Error -> ErrorScreen(retryAction, modifier.fillMaxSize())
    }
}

@Composable
fun ListSiswa(
    siswa: List<DataSiswa>,
    onSiswaClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(siswa) { item ->
            ItemSiswa(siswa = item, modifier = Modifier.fillMaxWidth().clickable { onSiswaClick(item.id) })
        }
    }
}

@Composable
fun ItemSiswa(siswa: DataSiswa, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = siswa.nama, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                Text(text = siswa.telpon, style = MaterialTheme.typography.titleMedium)
            }
            Text(text = siswa.alamat, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
