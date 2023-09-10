package ir.thatsmejavad.backgroundable.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.MediaCard
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.getStringMessage

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onMediaClicked: (Int, String) -> Unit,
) {
    val queryString by viewModel.searchQuery.collectAsStateWithLifecycle()
    val medias = viewModel.medias.collectAsLazyPagingItems()

    val context = LocalContext.current
    val refreshLoadState = medias.loadState.refresh
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    val pagingIsLoading = medias.loadState.prepend is LoadState.Loading ||
            medias.loadState.append is LoadState.Loading ||
            medias.loadState.refresh is LoadState.Loading

    LaunchedEffect(medias.loadState.refresh) {
        val refresh = medias.loadState.refresh
        if (refresh is LoadState.Error && medias.itemCount != 0) {
            viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    viewModel.snackbarManager.ObserveSnackbars(snackbarHostState)

    BackgroundableScaffold(
        modifier = Modifier
            /*
            The padding of the bottomBar,
            can't use Scaffold to add bottomBar with animation.
            the bottom of the ui will jump
             */
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
        snackbarHostState = snackbarHostState
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                    .shadow(2.dp, MaterialTheme.shapes.extraSmall),
                value = queryString,
                onValueChange = { text ->
                    if (text.length < 40) {
                        viewModel.updateSearchText(text)
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_text_field_place_holder),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search"
                    )
                },
                shape = MaterialTheme.shapes.extraSmall,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { viewModel.search() }
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                },
            )

            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (refreshLoadState is LoadState.Loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    state = lazyStaggeredGridState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                ) {
                    items(
                        count = medias.itemCount,
                        key = medias.itemKey { it.id },
                        contentType = medias.itemContentType()
                    ) { index ->
                        medias[index]?.let { media ->
                            MediaCard(
                                id = media.id,
                                alt = media.alt,
                                height = media.height,
                                avgColor = media.avgColor,
                                isSingleColumn = false,
                                // TODO we should get the staggered type from data store
                                isStaggered = true,
                                photographer = media.photographer,
                                resourceUrl = media.resources.medium,
                                onMediaClicked = onMediaClicked
                            )
                        }
                    }

                    when (val paginationLoadState = medias.loadState.append) {
                        is LoadState.Error -> {
                            if (!paginationLoadState.endOfPaginationReached) {
                                item {
                                    Box(Modifier.fillMaxSize()) {
                                        Text(
                                            modifier = Modifier.align(Alignment.Center),
                                            text = paginationLoadState.error.getStringMessage(
                                                context
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        is LoadState.Loading -> {
                            item {
                                Box(Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                            }
                        }

                        else -> {}
                    }
                }

                if (medias.itemCount == 0 && !pagingIsLoading && !medias.loadState.append.endOfPaginationReached) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.label_looking_for_wallpapers_start_here)
                    )
                }

                if (medias.itemCount == 0 && !pagingIsLoading && medias.loadState.append.endOfPaginationReached) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(
                            R.string.label_nothing_found_with_keyword,
                            queryString
                        )
                    )
                }

                if (medias.itemCount == 0 && refreshLoadState is LoadState.Error) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = (refreshLoadState as? LoadState.Error)?.error?.getStringMessage(
                                context
                            )
                                ?: ""
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ElevatedButton(onClick = { medias.retry() }) {
                            Text(text = stringResource(R.string.label_try_again))
                        }
                    }
                }
            }
        }
    }
}