package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.LazyColumnWithSwipeRefresh
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(
    viewModel: CollectionListViewModel,
    onCollectionClicked: (String, String) -> Unit,
) {
    val collections = viewModel.collection.collectAsLazyPagingItems()

    LaunchedEffect(collections.loadState.refresh) {
        val refresh = collections.loadState.refresh
        if (refresh is LoadState.Error && collections.itemCount != 0) {
            viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
        }
    }

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
            )
        },
    ) { paddingValues ->
        LazyColumnWithSwipeRefresh(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            pagingItems = collections,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(
                count = collections.itemCount,
                key = collections.itemKey(),
                contentType = collections.itemContentType(),

                ) { index ->
                collections[index]?.let { collection ->
                    CollectionCard(
                        index = index,
                        collection = collection,
                        onCollectionClicked = onCollectionClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun CollectionCard(
    index: Int,
    collection: CollectionEntity,
    onCollectionClicked: (String, String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCollectionClicked(collection.id, collection.title) }
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${index + 1}. ${collection.title}",
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
    }
}
