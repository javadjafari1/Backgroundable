package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ElevatedButton
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
fun <T : Any> LazyVerticalStaggeredGridWithSwipeRefresh(
    columns: StaggeredGridCells,
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyStaggeredGridScope.() -> Unit,
) {
    val context = LocalContext.current
    val refreshLoadState = pagingItems.loadState.refresh

    BoxWithSwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        onSwipe = { pagingItems.refresh() },
        isRefreshing = pagingItems.loadState.refresh is LoadState.Loading
    ) {
        LazyVerticalStaggeredGrid(
            columns = columns,
            state = state,
            modifier = modifier.fillMaxSize(),
            contentPadding = contentPadding,
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

        if (pagingItems.itemCount == 0 && refreshLoadState is LoadState.Error) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
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
