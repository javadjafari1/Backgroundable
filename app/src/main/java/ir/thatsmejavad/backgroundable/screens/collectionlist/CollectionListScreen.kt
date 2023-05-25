package ir.thatsmejavad.backgroundable.screens.collectionlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.CircularLoading
import ir.thatsmejavad.backgroundable.common.ui.CoilImage
import ir.thatsmejavad.backgroundable.core.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.core.getStringMessage
import ir.thatsmejavad.backgroundable.model.media.Media

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    title: String,
    id: String,
    viewModel: CollectionListViewModel,
    onMediaClicked: (String) -> Unit,
) {

    val medias = viewModel.medias.collectAsLazyPagingItems()

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = title)
                },
            )
        },
    ) {

        val context = LocalContext.current

        when (val firstLoadState = medias.loadState.refresh) {
            is LoadState.Error -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Log.d("jai", "CollectionListScreen:${firstLoadState.error} ")
                    Text(
                        text = firstLoadState.error.getStringMessage(context)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ElevatedButton(onClick = { viewModel.getMedias(id) }) {
                        Text(text = stringResource(R.string.label_try_again))
                    }
                }
            }

            is LoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularLoading()
                }
            }

            is LoadState.NotLoading -> {
                LazyColumn(
                    Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        count = medias.itemCount,
                        key = medias.itemKey(),
                        contentType = medias.itemContentType()
                    ) { index ->
                        medias[index]?.let { media ->
                            MediaCard(
                                media = media,
                                onCollectionClicked = onMediaClicked
                            )
                        }
                    }

                    when (val paginationLoadState = medias.loadState.append) {
                        is LoadState.Error -> {
                            item {
                                if (!paginationLoadState.endOfPaginationReached) {
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
                                    CircularLoading()
                                }
                            }
                        }

                        is LoadState.NotLoading -> {}
                    }
                }
            }
        }

    }
}

@Composable
private fun MediaCard(
    media: Media,
    onCollectionClicked: (String) -> Unit,
) {
    Column {
        media.resources?.let {
            CoilImage(
                url = it.small,
                contentDescription = media.alt
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = media.alt + " by " + media.photographer)
    }
}
