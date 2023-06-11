package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.getStringMessage

@Composable
fun <T : Any> LazyColumnWithSwipeRefresh(
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit,
) {
    val context = LocalContext.current
    val refreshLoadState = pagingItems.loadState.refresh

    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            AnimatedVisibility(
                visible = pagingItems.itemCount != 0 && refreshLoadState is LoadState.Loading
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.padding(4.dp))

            LazyColumn(
                state = state,
                modifier = modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
            ) {
                content()

                when (val paginationLoadState = pagingItems.loadState.append) {
                    is LoadState.Error -> {
                        if (!paginationLoadState.endOfPaginationReached) {
                            item {
                                Box(Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = paginationLoadState.error.getStringMessage(context)
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

                    else -> {}
                }
            }
        }

        if (pagingItems.itemCount == 0 && refreshLoadState is LoadState.Loading) {
            CircularLoading()
        }

        if (pagingItems.itemCount == 0 && refreshLoadState is LoadState.Error) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = (refreshLoadState as? LoadState.Error)?.error?.getStringMessage(context)
                        ?: ""
                )
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(onClick = { pagingItems.retry() }) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }
        }
    }
}
