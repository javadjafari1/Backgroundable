package ir.thatsmejavad.backgroundable.screens.medialist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
import ir.thatsmejavad.backgroundable.common.ui.BoxWithSwipeRefresh
import ir.thatsmejavad.backgroundable.common.ui.MediaCard
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality.Companion.toResourceSize
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@Composable
fun MediaListScreen(
    navController: NavController,
    viewModel: MediaListViewModel = daggerViewModel(),
    title: String
) {
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

    MediaListScreen(
        title = title,
        columnType = columnType,
        imageQuality = imageQuality,
        snackbarHostState = snackbarHostState,
        medias = medias,
        navigateTo = {
            navController.navigate(it) {
                launchSingleTop = true
            }
        },
        onBackClicked = { navController.navigateUp() },
        changeColumnType = { viewModel.changeColumnCount() },
    )
}

@Composable
private fun MediaListScreen(
    title: String,
    columnType: List,
    imageQuality: ImageQuality,
    snackbarHostState: SnackbarHostState,
    medias: LazyPagingItems<MediaWithResources>,
    navigateTo: (String) -> Unit,
    onBackClicked: () -> Unit,
    changeColumnType: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    BackgroundableScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHostState = snackbarHostState,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = changeColumnType) {
                        Icon(
                            painter = painterResource(
                                when (columnType) {
                                    List.GridType -> R.drawable.ic_list
                                    List.ListType -> R.drawable.ic_staggered_grid
                                    List.StaggeredType -> R.drawable.ic_grid
                                }
                            ),
                            contentDescription = "Change column count"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->

        val refreshLoadState = medias.loadState.refresh

        BoxWithSwipeRefresh(
            modifier = Modifier.padding(paddingValues),
            onSwipe = { medias.refresh() },
            isRefreshing = medias.loadState.refresh is LoadState.Loading
        ) {
            AnimatedContent(
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
                        contentPadding = PaddingValues(top = 16.dp)
                    ) {
                        items(
                            count = medias.itemCount,
                            key = medias.itemKey { it.media.id },
                            contentType = medias.itemContentType()
                        ) { index ->
                            medias[index]?.let { media ->
                                MediaCard(
                                    alt = media.media.alt,
                                    aspectRatio = media.media.width / media.media.height.toFloat(),
                                    avgColor = media.media.avgColor,
                                    photographer = media.media.photographer,
                                    resourceUrl = media.resources.first {
                                        it.size == imageQuality.toResourceSize()
                                    }.url,
                                    onMediaClicked = {
                                        navigateTo(
                                            AppScreens.MediaDetail.createRoute(
                                                media.media.id,
                                                media.media.alt
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
                        contentPadding = PaddingValues(top = 16.dp)
                    ) {
                        items(
                            count = medias.itemCount,
                            key = medias.itemKey { it.media.id },
                            contentType = medias.itemContentType()
                        ) { index ->
                            medias[index]?.let { media ->
                                MediaCard(
                                    alt = media.media.alt,
                                    avgColor = media.media.avgColor,
                                    isSingleColumn = columnType == List.ListType,
                                    photographer = media.media.photographer,
                                    resourceUrl = media
                                        .resources
                                        .first { it.size == imageQuality.toResourceSize() }
                                        .url,
                                    onMediaClicked = {
                                        navigateTo(
                                            AppScreens.MediaDetail.createRoute(
                                                media.media.id,
                                                media.media.alt
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
