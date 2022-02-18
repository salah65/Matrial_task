package com.example.fake_matrial.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.MaterialsResponseItem
import com.example.fake_matrial.domain.model.Material
import com.example.fake_matrial.domain.useCases.GetAllMaterialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getAllMaterialsUseCase: GetAllMaterialsUseCase) :
    ViewModel() {
    private val mutableStateflow by lazy {
        MutableStateFlow<ResponseWrapper<List<MaterialsResponseItem?>>>(ResponseWrapper.Loading)
    }
    val getMaterialsStateFlow get() = mutableStateflow.asStateFlow()
    private val downloadMutableStateflow by lazy {
        Channel<Pair<Int,Int>>()
    }
    val downloadStateflow get() = downloadMutableStateflow.receiveAsFlow()

    init {
        getMaterialsList()
    }


    private fun getMaterialsList() {
        viewModelScope.launch {
            val response = getAllMaterialsUseCase.invoke()
            mutableStateflow.emit(response)
        }
    }

    fun downloadMaterial(Url: String? = null,position:Int) {
        var progress = 0
        viewModelScope.launch(Dispatchers.IO) {
            while (progress < 100) {
                delay(500)
                val random = Random.nextInt(0, 5)
                progress += random
                downloadMutableStateflow.send(Pair(progress,position))
            }

        }
    }
}