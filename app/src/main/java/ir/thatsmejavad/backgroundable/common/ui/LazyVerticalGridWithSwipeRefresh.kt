package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
fun <T : Any> LazyVerticalGridWithSwipeRefresh(
    columns: GridCells,
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    state: LazyGridState = rememberLazyGridState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyGridScope.() -> Unit,
) {
    val context = LocalContext.current
    val refreshLoadState = pagingItems.loadState.refresh

    BoxWithSwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        onSwipe = { pagingItems.refresh() },
        isRefreshing = pagingItems.loadState.refresh is LoadState.Loading
    ) {
        LazyVerticalGrid(
            columns = columns,
            state = state,
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
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
