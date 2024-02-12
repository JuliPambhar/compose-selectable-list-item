package com.app.electroluxassigment.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.electroluxassigment.R
import com.app.electroluxassigment.ui.components.SelectableItem
import com.app.electroluxassigment.ui.model.DataInfo
import com.app.electroluxassigment.ui.theme.AppTheme
/**
 * Implemented [LazyColumn] with [SelectableItem]s loaded dynamically from [DataSource].
 *
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(dataInfos: List<DataInfo>, onSelectionChange: (Int) -> Unit) {
    Scaffold {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(it)
                .padding(top = 56.dp)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                items(dataInfos.size) { itemIndex ->
                    //Generic component SelectableItem.
                    SelectableItem(
                        imageVector = ImageVector.vectorResource(dataInfos[itemIndex].icon),
                        title = dataInfos[itemIndex].title,
                        description = dataInfos[itemIndex].description,
                        isSelected = dataInfos[itemIndex].isSelected,
                        isEnabled = dataInfos[itemIndex].isEnable,
                        onSelectionChange = {
                            onSelectionChange(itemIndex)
                        }
                    )
                }
            }
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, heightDp = 300, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, heightDp = 300, uiMode = UI_MODE_NIGHT_NO)
annotation class ThemePreviews

@ThemePreviews
@Composable
fun MainScreenPreviewDark() {
    AppTheme {
        MainScreen(
            dataInfos = listOf(
                DataInfo(
                    id = 1,
                    title = "Hello World",
                    description = "Hello World Description",
                    icon = R.drawable.ic_cotton_eco,
                    isSelected = true,
                    isEnable = true
                ),
                DataInfo(
                    id = 1,
                    title = "Hello World",
                    description = "Hello World Description",
                    icon = R.drawable.ic_cotton_eco,
                    isSelected = false,
                    isEnable = false
                )
            ),
            onSelectionChange = {}
        )
    }
}