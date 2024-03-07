package ir.thatsmejavad.backgroundable.screens.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.MediaCard
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.common.ui.clearFocusOnKeyboardDismiss
import ir.thatsmejavad.backgroundable.common.ui.icons.getSearchImage
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel
import ir.thatsmejavad.backgroundable.model.media.Media

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = daggerViewModel(),
) {
    val queryString by viewModel.searchQuery.collectAsStateWithLifecycle()
    val medias = viewModel.medias.collectAsLazyPagingItems()
    val columnType by viewModel.mediaColumnTypeFlow.collectAsStateWithLifecycle()
    val imageQuality by viewModel.imageQuality.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    viewModel.snackbarManager.ObserveSnackbars(snackbarHostState)

    LaunchedEffect(medias.loadState.refresh) {
        val refresh = medias.loadState.refresh
        if (refresh is LoadState.Error && medias.itemCount != 0) {
            viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
        }
    }

    SearchScreen(
        queryString = queryString,
        medias = medias,
        snackbarHostState = snackbarHostState,
        columnType = columnType,
        imageQuality = imageQuality,
        navigateTo = { route ->
            navController.navigate(route)
        },
        updateSearchText = { text ->
            viewModel.updateSearchText(text)
        },
        onSearchClicked = {
        }
    )
}

@Composable
private fun SearchScreen(
    columnType: List,
    queryString: String,
    imageQuality: ImageQuality,
    snackbarHostState: SnackbarHostState,
    medias: LazyPagingItems<Media>,
    navigateTo: (String) -> Unit,
    updateSearchText: (String) -> Unit,
    onSearchClicked: () -> Unit,
) {
    val refreshLoadState = medias.loadState.refresh

    val pagingIsLoading = medias.loadState.prepend is LoadState.Loading ||
        medias.loadState.append is LoadState.Loading ||
        medias.loadState.refresh is LoadState.Loading

    var isFocused by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val padding: Dp by animateDpAsState(
        if (isFocused) 0.dp else 16.dp,
        tween(400),
        label = "padding animation"
    )
    val cornerRadius: Dp by animateDpAsState(
        if (isFocused) 0.dp else 16.dp,
        tween(400),
        label = "corner radius animation"
    )

    BackgroundableScaffold(
        snackbarHostState = snackbarHostState,
        topBar = {
            TextField(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = padding)
                    .fillMaxWidth()
                    .clearFocusOnKeyboardDismiss()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                    .focusRequester(focusRequester)
                    .shadow(
                        elevation = 2.dp,
                        shape = MaterialTheme.shapes.extraSmall.copy(CornerSize(cornerRadius))
                    ),
                value = queryString,
                onValueChange = { text ->
                    if (text.length < 40) {
                        updateSearchText(text)
                    }
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_text_field_place_holder),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                leadingIcon = {
                    AnimatedContent(targetState = isFocused, label = "") {
                        IconButton(
                            onClick = {
                                if (it) {
                                    focusManager.clearFocus()
                                } else {
                                    focusRequester.requestFocus()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (it) {
                                    Icons.AutoMirrored.Filled.ArrowBack
                                } else {
                                    Icons.Filled.Search
                                },
                                contentDescription = if (it) "back" else "search"
                            )
                        }
                    }
                },
                shape = MaterialTheme.shapes.extraSmall.copy(CornerSize(cornerRadius)),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchClicked() }
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
                        IconButton(onClick = { updateSearchText("") }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "clear-text"
                            )
                        }
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (refreshLoadState is LoadState.Loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            AnimatedContent(
                modifier = Modifier.imePadding(),
                targetState = columnType,
                label = "change Staggered to Grid anim",
                transitionSpec = {
                    (
                        fadeIn(animationSpec = tween(220, delayMillis = 120)) +
                            slideIn(
                                animationSpec = tween(220, delayMillis = 120),
                                initialOffset = { IntOffset.Zero }
                            ) +
                            scaleIn(
                                initialScale = 0.92f,
                                animationSpec = tween(220, delayMillis = 120)
                            )
                        )
                        .togetherWith(fadeOut(animationSpec = tween(120)))
                }
            ) { type ->
                if (type == List.StaggeredType) {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 12.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(
                            top = 16.dp,

                            /*
                             The padding of the bottomBar,
                             can't use Scaffold to add bottomBar with animation.
                             the bottom of the ui will jump
                             */
                            bottom = NAVIGATION_BAR_HEIGHT
                        ),
                    ) {
                        items(
                            count = medias.itemCount,
                            key = medias.itemKey { it.id },
                            contentType = medias.itemContentType()
                        ) { index ->
                            medias[index]?.let { media ->
                                MediaCard(
                                    alt = media.alt,
                                    aspectRatio = media.width / media.height.toFloat(),
                                    avgColor = media.avgColor,
                                    photographer = media.photographer,
                                    resourceUrl = when (imageQuality) {
                                        ImageQuality.High -> media.resources.medium
                                        ImageQuality.Medium -> media.resources.small
                                        ImageQuality.Low -> media.resources.tiny
                                        ImageQuality.Ultra -> media.resources.original
                                    },
                                    onMediaClicked = {
                                        navigateTo(
                                            AppScreens.MediaDetail.createRoute(
                                                media.id,
                                                media.alt
                                            )
                                        )
                                    }
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
                                                text = paginationLoadState.error.getErrorMessage()
                                                    .asString()
                                            )
                                        }
                                    }
                                }
                            }

                            is LoadState.Loading -> {
                                item {
                                    Box(Modifier.fillMaxSize()) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                }
                            }

                            else -> {}
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize(),
                        columns = GridCells.Fixed(
                            if (type == List.ListType) 1 else 2
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            /*
                             The padding of the bottomBar,
                             can't use Scaffold to add bottomBar with animation.
                             the bottom of the ui will jump
                             */
                            bottom = NAVIGATION_BAR_HEIGHT
                        ),
                    ) {
                        items(
                            count = medias.itemCount,
                            key = medias.itemKey { it.id },
                            contentType = medias.itemContentType()
                        ) { index ->
                            medias[index]?.let { media ->
                                MediaCard(
                                    alt = media.alt,
                                    avgColor = media.avgColor,
                                    isSingleColumn = columnType == List.ListType,
                                    photographer = media.photographer,
                                    resourceUrl = when (imageQuality) {
                                        ImageQuality.High -> media.resources.medium
                                        ImageQuality.Medium -> media.resources.small
                                        ImageQuality.Low -> media.resources.tiny
                                        ImageQuality.Ultra -> media.resources.original
                                    },
                                    onMediaClicked = {
                                        navigateTo(
                                            AppScreens.MediaDetail.createRoute(
                                                media.id,
                                                media.alt
                                            )
                                        )
                                    }
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
                                                text = paginationLoadState.error.getErrorMessage()
                                                    .asString()
                                            )
                                        }
                                    }
                                }
                            }

                            is LoadState.Loading -> {
                                item {
                                    Box(Modifier.fillMaxSize()) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }

            if (
                medias.itemCount == 0 &&
                !pagingIsLoading &&
                !medias.loadState.append.endOfPaginationReached &&
                refreshLoadState !is LoadState.Error
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            top = 16.dp,
                            /*
                             The padding of the bottomBar,
                             can't use Scaffold to add bottomBar with animation.
                             the bottom of the ui will jump
                             */
                            bottom = NAVIGATION_BAR_HEIGHT + 4.dp
                        ),
                    imageVector = getSearchImage(MaterialTheme.colorScheme.primary),
                    contentDescription = "search_icon"
                )
            }

            if (medias.itemCount == 0 && !pagingIsLoading && medias.loadState.append.endOfPaginationReached) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.label_nothing_found_with_keyword)
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
                        text = (refreshLoadState as? LoadState.Error)
                            ?.error
                            .getErrorMessage()
                            .asString()
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
