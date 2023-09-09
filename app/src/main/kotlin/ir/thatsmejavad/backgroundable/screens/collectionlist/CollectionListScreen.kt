package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.HexagonShape
import ir.thatsmejavad.backgroundable.common.ui.LazyVerticalGridWithSwipeRefresh
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.common.ui.drawCustomHexagonPath
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(
    viewModel: CollectionListViewModel,
    onCollectionClicked: (String, String) -> Unit,
    openColumnCountPicker: (Int) -> Unit,
) {
    val collections = viewModel.collection.collectAsLazyPagingItems()
    val columnCounts by viewModel.columnCount.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    LaunchedEffect(collections.loadState.refresh) {
        val refresh = collections.loadState.refresh
        if (refresh is LoadState.Error && collections.itemCount != 0) {
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
            .padding(bottom = NAVIGATION_BAR_HEIGHT)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHostState = snackbarHostState,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                actions = {
                    IconButton(onClick = { openColumnCountPicker(columnCounts) }) {
                        Icon(
                            painter = painterResource(
                                if (columnCounts == 1) {
                                    R.drawable.ic_grid
                                } else {
                                    R.drawable.ic_list
                                }
                            ),
                            contentDescription = "Change column count"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) {
        LazyVerticalGridWithSwipeRefresh(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
            pagingItems = collections,
            columns = GridCells.Fixed(columnCounts),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = collections.itemCount,
                key = collections.itemKey(),
                contentType = collections.itemContentType()
            ) { index ->
                collections[index]?.let { collection ->
                    CollectionCard(
                        isGrid = columnCounts > 2,
                        collection = collection,
                        onCollectionClicked = onCollectionClicked
                    )
                }
            }
        }
    }
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
private fun LazyGridItemScope.CollectionCard(
    isGrid: Boolean,
    collection: CollectionEntity,
    onCollectionClicked: (String, String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = MaterialTheme.shapes.extraSmall,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        onClick = { onCollectionClicked(collection.id, collection.title) },
        interactionSource = remember { MutableInteractionSource() }
    ) {
        ConstraintLayout {
            val (count, title) = createRefs()

            val secondaryColor = MaterialTheme.colorScheme.secondary
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = 8.dp)
                    .drawWithContent {
                        drawContent()
                        drawPath(
                            path = drawCustomHexagonPath(size),
                            color = secondaryColor,
                            style = Stroke(
                                width = 10.dp.toPx(),
                                pathEffect = PathEffect.cornerPathEffect(8f)
                            )
                        )
                    }
                    .background(
                        color = secondaryColor,
                        shape = HexagonShape
                    )
                    .padding(12.dp)
                    .constrainAs(count) {
                        if (isGrid) {
                            top.linkTo(parent.top)
                            bottom.linkTo(title.top)
                        } else {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(title.start)
                        }
                    },
                text = collection.photosCount.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                modifier = Modifier
                    .basicMarquee()
                    .padding(vertical = 8.dp)
                    .padding(end = 8.dp)
                    .padding(horizontal = if (isGrid) 8.dp else 0.dp)
                    .constrainAs(title) {
                        if (isGrid) {
                            top.linkTo(count.bottom)
                            bottom.linkTo(parent.bottom)
                        } else {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(count.end, margin = 10.dp)
                            // TODO fix not showing the end of the text
                            // end.linkTo(parent.end)
                        }
                    },
                text = collection.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
