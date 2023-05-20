package ir.thatsmejavad.backgroundable.screens.featuredCollections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.common.ui.CircularLoading
import ir.thatsmejavad.backgroundable.core.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.getStringMessage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedCollectionsScreen(
    viewModel: FeaturedCollectionsViewModel,
    onCollectionClicked: (String) -> Unit,
) {
    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Backgroundable")
                },
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val collections = viewModel.collection.collectAsLazyPagingItems()
            val context = LocalContext.current

            LazyColumn(
                Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = collections.itemCount,
                    key = collections.itemKey(),
                    contentType = collections.itemContentType()
                ) { index ->
                    val collection = collections[index]

                    collection?.let {
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onCollectionClicked(it.id) }
                                    .padding(vertical = 24.dp, horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${index + 1}. ${it.title}",
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
                                    text = it.photosCount.toString()
                                )
                            }
                        }

                    }
                }

                when (val state = collections.loadState.refresh) {
                    is LoadState.Error -> {
                        item {
                            Box(Modifier.fillMaxSize()) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = state.error.getStringMessage(context)
                                )
                            }
                        }
                    }

                    is LoadState.Loading -> {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularLoading()
                            }
                        }
                    }

                    is LoadState.NotLoading -> {}
                }

                when (val state = collections.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        //TODO Pagination Error Item
                        //state.error to get error message
                        item {
                            if (!state.endOfPaginationReached) {
                                Box(Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = state.error.getStringMessage(context)
                                    )
                                }
                            }
                        }
                    }

                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            Box(Modifier.fillMaxSize()) {
                                CircularLoading()
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}
