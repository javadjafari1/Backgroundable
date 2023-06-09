package ir.thatsmejavad.backgroundable.screens.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.BuildConfig
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateTo: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            /*
            The padding of the bottomBar,
            can't use Scaffold to add bottomBar with animation.
            the bottom of the ui will jump
             */
            .padding(bottom = Constants.NAVIGATION_BAR_HEIGHT),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.label_setting))
                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            SettingItem(
                textId = R.string.label_language,
                imageId = R.drawable.ic_language,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingItem(
                textId = R.string.label_theme,
                imageId = R.drawable.ic_theme,
                onClick = {
                    navigateTo(AppScreens.ThemeSetting.route)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingItem(
                textId = R.string.label_about_us,
                imageId = R.drawable.ic_info,
                onClick = {}
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(R.string.label_version, BuildConfig.VERSION_NAME)
            )
        }
    }
}

@Composable
private fun SettingItem(
    @StringRes textId: Int,
    @DrawableRes imageId: Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(imageId),
            contentDescription = stringResource(textId)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = stringResource(textId))
    }
}

