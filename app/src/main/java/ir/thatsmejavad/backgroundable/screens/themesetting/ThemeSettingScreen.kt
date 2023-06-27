package ir.thatsmejavad.backgroundable.screens.themesetting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.model.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingScreen(
    viewModel: ThemeSettingViewModel,
    onBackClicked: () -> Unit,
) {
    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle(
        initialValue = UserPreferences()
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = stringResource(R.string.label_theme))
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
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {

            MaterialYouRow(
                isChecked = userPreferences.isMaterialYouEnabled,
                onCheckChanged = {
                    viewModel.updateIsMaterialYouEnabled(it)
                }
            )

        }
    }
}

@Composable
private fun MaterialYouRow(
    isChecked: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onCheckChanged(!isChecked) }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = stringResource(R.string.label_material_you))

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = isChecked, onCheckedChange = onCheckChanged)

    }
}
