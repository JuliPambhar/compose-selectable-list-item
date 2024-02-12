package com.app.electroluxassigment.ui

import androidx.lifecycle.ViewModel
import com.app.electroluxassigment.data.IDataSource
import com.app.electroluxassigment.ui.model.DataInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dataSource: IDataSource) : ViewModel() {

    private val _viewData = MutableStateFlow(listOf<DataInfo>())
    val viewData get() = _viewData.asStateFlow()

    init {
        _viewData.value = dataSource.getData()
    }

    /**
     * This function will make sure that only one item is selected in [viewData]
     */
    fun onSelectionChange(changedIndex: Int) {
        _viewData.value = _viewData.value.mapIndexed { j, selectionData ->
            selectionData.copy(isSelected = changedIndex == j)
        }
    }

}