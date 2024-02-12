package com.app.electroluxassigment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.app.electroluxassigment.ui.screen.MainScreen
import com.app.electroluxassigment.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val dataInfos = viewModel.viewData.collectAsState()
                MainScreen(dataInfos.value, onSelectionChange = { changedIndex ->
                    viewModel.onSelectionChange(changedIndex)
                })
            }
        }
    }
}