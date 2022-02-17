package com.example.fake_matrial.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fake_matrial.R
import com.example.fake_matrial.data.mapper.mapToListOfMaterial
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}