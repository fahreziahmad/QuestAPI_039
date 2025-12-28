package com.example.questapi_039.uicontroller.route

import com.example.questapi_039.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "id"
    val routeWithArgs = "$route/{$itemIdArg}"
}