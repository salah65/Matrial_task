package com.example.fake_matrial.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fake_matrial.R
import com.example.fake_matrial.data.mapper.mapToListOfMaterial
import com.example.fake_matrial.data.network.ResponseWrapper
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            viewModel.getMaterialsStateFlow.collect {
                when (it) {
                    is ResponseWrapper.Loading -> {

                    }
                    is ResponseWrapper.Success -> {

                    }
                    is ResponseWrapper.GenericError -> {

                    }
                    else -> {

                    }
                }
            }
        }

    }
}