package ir.thatsmejavad.backgroundable.screens.columncountpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ColumnCountPicker(
    items: List<Int>,
    selectedItem: Int,
    onSelect: (Int) -> Unit
) {
    LazyColumn {
        item {
            Text(
                text = "Grid Count",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(24.dp)
            )
        }
        item {
            Divider()
        }

        items(items) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(
                        color = if (selectedItem == it) {
                            MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    )
                    .clickable { onSelect(it) }
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                text = it.toString(),
                color = if (selectedItem == it) {
                    MaterialTheme.colorScheme.onSecondary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}
