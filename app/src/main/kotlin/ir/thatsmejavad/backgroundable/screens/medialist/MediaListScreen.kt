package ir.thatsmejavad.backgroundable.screens.medialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.LazyVerticalStaggeredGridWithSwipeRefresh
import ir.thatsmejavad.backgroundable.common.ui.MediaCard
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize

@OptIn(ExperimentalMaterial3Api::class)
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
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
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
        LazyVerticalStaggeredGridWithSwipeRefresh(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
                .fillMaxSize(),
            pagingItems = medias,
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
        }
    }
}
