package com.app.electroluxassigment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.app.electroluxassigment.ui.components.SelectableItem
import com.app.electroluxassigment.ui.theme.AppTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SelectableItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun initView(isSelected: Boolean) {
        composeTestRule.setContent {
            var selected by remember { mutableStateOf(isSelected) }
            AppTheme {
                SelectableItem(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_sport),
                    title = "My Title",
                    description = "My Description",
                    isSelected = selected,
                    isEnabled = true,
                    onSelectionChange = { selected = it }
                )
            }
        }
    }

    @Test
    fun given_isSelected_true_then_verify_expand_view_displayed() {
        initView(isSelected = true)
        composeTestRule.onNodeWithTag("ExpandableView", true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertIsDisplayed()
    }

    @Test
    fun given_isSelected_false_then_verify_expand_view_not_displayed() {
        initView(isSelected = false)
        composeTestRule.onNodeWithTag("ExpandableView", true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun given_isSelected_false_then_verify_expand_view_after_click() {
        runTest {
            initView(isSelected = false)
            composeTestRule.onNodeWithTag("ExpandableView", true).assertDoesNotExist()
            composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertDoesNotExist()
            composeTestRule.onNodeWithTag("SelectableView").performClick()
            composeTestRule.onNodeWithTag("ExpandableView", true).assertIsDisplayed()
            composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertIsDisplayed()
        }
    }

    @Test
    fun given_isSelected_true_then_verify_expand_view_after_click() {
        initView(isSelected = true)
        composeTestRule.onNodeWithTag("ExpandableView", true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("SelectableView").performClick()
        composeTestRule.onNodeWithTag("ExpandableView", true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("CheckMarkIcon", true).assertDoesNotExist()
    }
}