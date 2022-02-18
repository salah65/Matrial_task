package com.example.fake_matrial.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fake_matrial.R
import com.example.fake_matrial.data.mapper.mapToListOfMaterial
import com.example.fake_matrial.data.network.ResponseWrapper
import com.example.fake_matrial.data.network.dto.responses.GetMaterialsResponse
import com.example.fake_matrial.databinding.ActivityMainBinding
import com.example.fake_matrial.domain.model.DownloadStatus
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel by viewModels<MainActivityViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var adapter: MaterialItemsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MaterialItemsAdapter() { material, position ->
            viewModel.downloadMaterial(material.url, position)
        }
        binding.materialRv.apply {
            adapter = this@MainActivity.adapter
            itemAnimator = null

        }

        lifecycleScope.launchWhenStarted {
            viewModel.getMaterialsStateFlow.collect { responseWrapper ->
                when (responseWrapper) {
                    is ResponseWrapper.Loading -> {
                        showLoading()
                    }
                    is ResponseWrapper.Success -> {
                        hideLoading()
                        adapter?.updateData(responseWrapper.value.mapToListOfMaterial())
                    }
                    is ResponseWrapper.NetworkError -> {
                        hideLoading()
                        adapter?.updateData(
                            Gson().fromJson(
                                resources.openRawResource(R.raw.data).bufferedReader()
                                    .use { it.readText() },
                                GetMaterialsResponse::class.java
                            ).materialsResponse.mapToListOfMaterial()
                        )
                    }
                    is ResponseWrapper.GenericError -> {
                        hideLoading()
                        adapter?.updateData(
                            Gson().fromJson(
                                resources.openRawResource(R.raw.data).bufferedReader()
                                    .use { it.readText() },
                                GetMaterialsResponse::class.java
                            ).materialsResponse.mapToListOfMaterial()
                        )
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.downloadStateflow.collect {
                when {
                    it.first >= 100 -> adapter!!.updateMaterialState(
                        DownloadStatus.Downloaded,
                        it.second
                    )
                    else -> {
                        adapter!!.updateMaterialState(
                            DownloadStatus.Downloading(it.first),
                            it.second
                        )
                    }
                }
            }
        }

    }

    private fun showLoading() {
        binding.loader.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loader.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}