package ir.thatsmejavad.backgroundable.screens.columncountpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.R

@Composable
fun ColumnCountPicker(
    items: List<Int>,
    selectedItem: Int,
    onSelect: (Int) -> Unit
) {
    LazyColumn {
        item {
            Text(
                modifier = Modifier.padding(
                    vertical = 20.dp,
                    horizontal = 32.dp
                ),
                text = stringResource(R.string.label_grid_count),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        item {
            HorizontalDivider()
        }

        items(items) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .then(
                        if (selectedItem == it) {
                            Modifier.shadow(
                                2.dp,
                                shape = MaterialTheme.shapes.extraSmall,
                                ambientColor = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Modifier
                        }
                    )
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(
                        color = if (selectedItem == it) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    )
                    .clickable { onSelect(it) }
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                text = it.toString(),
                color = if (selectedItem == it) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
