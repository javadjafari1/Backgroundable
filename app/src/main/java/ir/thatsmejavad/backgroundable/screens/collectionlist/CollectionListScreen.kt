package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ir.thatsmejavad.backgroundable.common.ui.LazyVerticalGridWithSwipeRefresh
import ir.thatsmejavad.backgroundable.core.Constants.NAVIGATION_BAR_HEIGHT
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(
    viewModel: CollectionListViewModel,
    onCollectionClicked: (String, String) -> Unit,
) {
    val collections = viewModel.collection.collectAsLazyPagingItems()
    val columnCounts by viewModel.gridState.collectAsStateWithLifecycle(initialValue = 1)

    LaunchedEffect(collections.loadState.refresh) {
        val refresh = collections.loadState.refresh
        if (refresh is LoadState.Error && collections.itemCount != 0) {
            viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
        }
    }

    BackgroundableScaffold(
        modifier = Modifier
            /*
            The padding of the bottomBar,
            can't use Scaffold to add bottomBar with animation.
            the bottom of the ui will jump
             */
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(
                        onClick = {
                            val s = when (columnCounts) {
                                1 -> 2
                                3 -> 2
                                else -> 3
                            }
                            viewModel.setColumnCount(s)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Change column count"
                        )
                    }
                }
            )
        },
    ) {

        LazyVerticalGridWithSwipeRefresh(
            modifier = Modifier
                .fillMaxSize(),
            pagingItems = collections,
            columns = GridCells.Fixed(columnCounts),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(
                count = collections.itemCount,
                key = collections.itemKey(),
                contentType = collections.itemContentType()
            ) { index ->
                collections[index]?.let { collection ->
                    CollectionCard(
                        columnCounts = columnCounts,
                        collection = collection,
                        onCollectionClicked = onCollectionClicked
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyGridItemScope.CollectionCard(
    collection: CollectionEntity,
    onCollectionClicked: (String, String) -> Unit,
    columnCounts: Int
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .animateItemPlacement()
    ) {
        if (columnCounts == 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCollectionClicked(collection.id, collection.title) }
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = collection.title,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        )
                        .padding(8.dp),
                    text = collection.photosCount.toString()
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCollectionClicked(collection.id, collection.title) }
                    .padding(vertical = 24.dp, horizontal = 16.dp),
            ) {
                Text(
                    modifier = Modifier.basicMarquee(),
                    text = collection.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        )
                        .padding(8.dp),
                    text = collection.photosCount.toString(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}