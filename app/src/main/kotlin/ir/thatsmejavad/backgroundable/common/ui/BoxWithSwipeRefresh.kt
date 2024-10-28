package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BoxWithSwipeRefresh(
    onSwipe: () -> Unit,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        modifier = modifier,
        onRefresh = onSwipe,
        content = content
    )
}
