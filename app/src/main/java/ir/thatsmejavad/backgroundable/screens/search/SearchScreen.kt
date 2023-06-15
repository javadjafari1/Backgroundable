package ir.thatsmejavad.backgroundable.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
) {
    val queryString by viewModel.searchQuery.collectAsStateWithLifecycle()

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        modifier = Modifier
            /*
            The padding of the bottomBar,
            can't use Scaffold to add bottomBar with animation.
            the bottom of the ui will jump
            */
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
        contentModifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 16.dp,
        )
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = queryString,
            onValueChange = { text ->
                if (text.length < 40) {
                    viewModel.updateSearchText(text)
                }
            },
            placeholder = {
                Text(text = "Search...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search"
                )
            },
            shape = MaterialTheme.shapes.extraLarge,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { viewModel.search() }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = queryString.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { viewModel.updateSearchText("") }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "clear-text"
                        )
                    }
                }
            }
        )
    }
}
