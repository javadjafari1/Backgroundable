package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.R

@Composable
fun BoxScope.UnexpectedError(onTyAgain: () -> Unit) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.unexpected_error_message))
        ElevatedButton(onClick = onTyAgain) {
            Text(text = stringResource(R.string.Label_try_again))
        }
    }
}
