package com.app.electroluxassigment.ui

import com.app.electroluxassigment.data.MockDataSource
import com.app.electroluxassigment.data.IDataSource
import com.app.electroluxassigment.ui.model.DataInfo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {

    private lateinit var dataSource: IDataSource

    private lateinit var viewModelTest: MainActivityViewModel

    private var dataInfos = mutableListOf<DataInfo>()

    @Before
    fun setUp() {
        dataSource = MockDataSource()
        dataInfos = dataSource.getData().toMutableList()
        viewModelTest = MainActivityViewModel(
            dataSource = dataSource,
        )
    }

    @Test
    fun verifyInitializationOfViewData() {
        Assert.assertEquals(dataInfos, viewModelTest.viewData.value)
    }

    @Test
    fun verifyViewDataWithOnlyOneItemSelected() {
        viewModelTest.onSelectionChange(2)
        val actualItem = viewModelTest.viewData.value[2]
        val expectedItem = dataInfos[2].copy(isSelected = true)
        val selectedItemCount = viewModelTest.viewData.value.count { it.isSelected }
        Assert.assertEquals(expectedItem, actualItem)
        Assert.assertEquals(selectedItemCount, 1)
    }

    @Test
    fun verifyOnlyOneItemSelectedAfterSelectingOtherItem() {

        //First select any item
        viewModelTest.onSelectionChange(2)
        val actualItem = viewModelTest.viewData.value[2]
        val expectedItem = dataInfos[2].copy(isSelected = true)
        Assert.assertEquals(expectedItem, actualItem)

        //Select other item, then it should unselect the previous selected item.
        viewModelTest.onSelectionChange(3)
        val newActual = viewModelTest.viewData.value[2]
        val newExpected = dataInfos[2].copy(isSelected = false)
        val selectedItemCount = viewModelTest.viewData.value.count { it.isSelected }
        Assert.assertEquals(newExpected, newActual)
        Assert.assertEquals(selectedItemCount, 1)
    }
}