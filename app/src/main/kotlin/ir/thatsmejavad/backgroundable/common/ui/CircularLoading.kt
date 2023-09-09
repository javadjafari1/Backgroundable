package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BoxScope.CircularLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
}
