package com.app.electroluxassigment.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.electroluxassigment.R
import com.app.electroluxassigment.ui.theme.AppTheme

/**
 * [SelectableItem] component allows user to toggle between [isSelected] states.
 * if [isSelected] is TRUE then [description] will be shown and vice-versa.
 * @param title shows title.
 * @param description shows the description.
 * @param isSelected is used for setting default selection.
 * @param isEnabled is used for make the view enabled/disabled.
 * @param onSelectionChange is used for observing changes in the selected state.
 * */
@Composable
fun SelectableItem(
    imageVector: ImageVector,
    title: String,
    description: String,
    isSelected: Boolean,
    isEnabled: Boolean,
    onSelectionChange: ((Boolean) -> Unit),
) {
    val animationDurationMillis = 300

    val interactionSource = remember { MutableInteractionSource() }
    val alpha = if (isEnabled) 1.0f else 0.5f

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(animationDurationMillis)
        ) + fadeIn(
            initialAlpha = .3f, animationSpec = tween(animationDurationMillis)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(animationDurationMillis)
        ) + fadeOut(animationSpec = tween(animationDurationMillis))
    }

    Column(modifier = Modifier
        .testTag("SelectableView")
        .alpha(alpha)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onSelectionChange(isSelected.not())
        }
        .padding(horizontal = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Icon(
                imageVector = imageVector,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(22.dp)

            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                // Enter and Exit transition animation while select list item
                AnimatedVisibility(
                    visible = isSelected,
                    enter = enterTransition,
                    exit = exitTransition
                ) {
                    Text(
                        modifier = Modifier.testTag("ExpandableView"),
                        text = description,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            // visible invisible icon when item select or unselect.
            AnimatedVisibility(
                visible = isSelected,
                modifier = Modifier.testTag("CheckMarkIcon")
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_select),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(20.dp)
                )
            }
        }
        Divider(
            thickness = .2.dp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableItem() {
    AppTheme {
        SelectableItem(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delicate),
            title = "Hello World",
            description = "Hello World Description",
            isSelected = true,
            isEnabled = true,
            onSelectionChange = {}
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SelectableItemPreviewNotSelected() {
    AppTheme {
        SelectableItem(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delicate),
            title = "Hello World",
            description = "Hello World Description",
            isSelected = false,
            isEnabled = true,
            onSelectionChange = {}
        )
    }
}