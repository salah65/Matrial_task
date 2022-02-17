package com.example.fake_matrial.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.useCases.GetAllMaterialsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val getAllMaterialsUseCase: GetAllMaterialsUseCase) :
    ViewModel() {
    private val mutableStateflow by lazy {
        MutableStateFlow<ResponseWrapper<List<MaterialsResponseItem?>>>(ResponseWrapper.Loading)
    }
    val getMaterialsStateFlow: StateFlow<ResponseWrapper<List<MaterialsResponseItem?>>>
        get() = mutableStateflow.asStateFlow()


    fun getMaterialsList() {
        viewModelScope.launch {
            val response = getAllMaterialsUseCase.invoke()
            mutableStateflow.value = response
        }
    }
}