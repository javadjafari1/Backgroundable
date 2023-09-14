package ir.thatsmejavad.backgroundable.screens.medialist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.BoxWithSwipeRefresh
import ir.thatsmejavad.backgroundable.common.ui.MediaCard
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
internal fun MediaListScreen(
    title: String,
    viewModel: MediaListViewModel,
    onMediaClicked: (Int, String) -> Unit,
    onBackClicked: () -> Unit,
) {
    val medias = viewModel.medias.collectAsLazyPagingItems()
    val columnType by viewModel.mediaColumnTypeFlow.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val snackbarHostState = remember { SnackbarHostState() }
    viewModel.snackbarManager.ObserveSnackbars(snackbarHostState)

    LaunchedEffect(medias.loadState.refresh) {
        val refresh = medias.loadState.refresh
        if (refresh is LoadState.Error && medias.itemCount != 0) {
            viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
        }
    }

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
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.changeColumnCount() }) {
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
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                columns = StaggeredGridCells.Fixed(
                    when (columnType) {
                        List.GridType, List.StaggeredType -> 2
                        List.ListType -> 1
                    }
                ),
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
                            id = media.media.id,
                            alt = media.media.alt,
                            height = media.media.height,
                            avgColor = media.media.avgColor,
                            isSingleColumn = columnType == List.ListType,
                            isStaggered = columnType == List.StaggeredType,
                            photographer = media.media.photographer,
                            resourceUrl = media.resources.first { it.size == ResourceSize.Medium }.url,
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
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }

                    else -> {}
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
