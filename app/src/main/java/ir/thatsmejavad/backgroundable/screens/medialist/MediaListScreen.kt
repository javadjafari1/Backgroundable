package ir.thatsmejavad.backgroundable.screens.medialist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.CoilImage
import ir.thatsmejavad.backgroundable.common.ui.LazyVerticalStaggeredGridWithSwipeRefresh
import ir.thatsmejavad.backgroundable.core.getSnackbarMessage
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun MediaListScreen(
    title: String,
    viewModel: MediaListViewModel,
    onMediaClicked: (Int, String) -> Unit,
    onBackClicked: () -> Unit,
) {
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
    ) { paddingValues ->
        val medias = viewModel.medias.collectAsLazyPagingItems()

        LaunchedEffect(medias.loadState.refresh) {
            val refresh = medias.loadState.refresh
            if (refresh is LoadState.Error && medias.itemCount != 0) {
                viewModel.snackbarManager.sendError(refresh.error.getSnackbarMessage())
            }
        }

        LazyVerticalStaggeredGridWithSwipeRefresh(
            pagingItems = medias,
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
        ) {
            items(
                count = medias.itemCount,
                key = medias.itemKey { it.media.id },
                contentType = medias.itemContentType()
            ) { index ->
                medias[index]?.let { media ->
                    MediaCard(
                        mediaWithResources = media,
                        onCollectionClicked = onMediaClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun MediaCard(
    mediaWithResources: MediaWithResources,
    onCollectionClicked: (Int, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onCollectionClicked(
                    mediaWithResources.media.id,
                    mediaWithResources.media.alt
                )
            }
            .padding(4.dp)

    ) {
        CoilImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .heightIn(max = (mediaWithResources.media.height / 20).dp),
            url = mediaWithResources.resources.first { it.size == ResourceSize.Medium }.url,
            contentDescription = mediaWithResources.media.alt,
            placeHolder = ColorPainter(mediaWithResources.media.avgColor.toColor())
        )
        Spacer(modifier = Modifier.padding(4.dp))
        val text = buildAnnotatedString {
            append(mediaWithResources.media.alt)
            addStyle(
                SpanStyle(fontWeight = FontWeight.Bold),
                0,
                mediaWithResources.media.alt.length
            )
            append(" by ")
            append(mediaWithResources.media.photographer)
        }
        Text(text = text)
    }
}
