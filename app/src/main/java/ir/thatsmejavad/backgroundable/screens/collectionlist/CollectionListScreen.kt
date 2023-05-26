package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.model.media.Media

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun CollectionListScreen(
    title: String,
    id: String,
    viewModel: CollectionListViewModel,
    onMediaClicked: (Int) -> Unit,
    onBackClicked: () -> Unit,
) {

    val medias = viewModel.medias.collectAsLazyPagingItems()

    BackgroundableScaffold(
        snackbarManager = viewModel.snackbarManager,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
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
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
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
    onCollectionClicked: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onCollectionClicked(media.id) }
            .padding(4.dp)

    ) {
        CoilImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .heightIn(max = (media.height / 20).dp),
            url = media.resources.medium,
            contentDescription = media.alt,
            placeHolder = ColorPainter(media.avgColor.toColor())
        )
        Spacer(modifier = Modifier.padding(4.dp))
        val text = buildAnnotatedString {
            append(media.alt)
            addStyle(SpanStyle(fontWeight = FontWeight.Bold), 0, media.alt.length)
            append(" by ")
            append(media.photographer)
        }
        Text(text = text)
    }
}
