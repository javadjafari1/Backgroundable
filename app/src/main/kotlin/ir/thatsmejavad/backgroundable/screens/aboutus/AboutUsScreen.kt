package ir.thatsmejavad.backgroundable.screens.aboutus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.BuildConfig
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.HexagonShape
import ir.thatsmejavad.backgroundable.common.ui.drawCustomHexagonPath
import ir.thatsmejavad.backgroundable.core.composeMail
import ir.thatsmejavad.backgroundable.core.openUrl
import ir.thatsmejavad.backgroundable.model.Contributor
import ir.thatsmejavad.backgroundable.model.ContributorLink

@Composable
fun AboutUsScreen(
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current

    BackgroundableScaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.label_about_us),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            BackgroundableAbout()

            contributors.forEach { contributor ->
                ContributorItem(
                    contributor = contributor,
                    openEmail = { mail ->
                        context.composeMail(mail)
                    },
                    openUrl = { url ->
                        context.openUrl(url)
                    }
                )
            }
        }
    }
}

@Composable
private fun ContributorItem(
    contributor: Contributor,
    openUrl: (String) -> Unit,
    openEmail: (String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            Modifier
                .padding(top = 60.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = MaterialTheme.shapes.small
                )
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = contributor.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = contributor.position,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.surfaceTint,
            )

            Spacer(modifier = Modifier.size(24.dp))

            Row(
                modifier = Modifier.align(CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                contributor.links.forEach { link ->
                    IconButton(
                        modifier = Modifier
                            .size(54.dp),
                        onClick = {
                            if (link.name == "mail") {
                                openEmail(link.url)
                            } else {
                                openUrl(link.url)
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(link.icon),
                            contentDescription = "${contributor.name}'s ${link.name}",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(40.dp))
        }

        Image(
            modifier = Modifier
                .padding(top = 10.dp)
                .size(100.dp)
                .align(TopCenter)
                .clip(CircleShape),
            painter = painterResource(contributor.image),
            contentDescription = "${contributor.name}'s image",
        )
    }
}

@Composable
private fun BackgroundableAbout() {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            Modifier
                .padding(top = 60.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = MaterialTheme.shapes.small
                )
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(R.string.label_version, BuildConfig.VERSION_NAME),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.size(60.dp))

            Text(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
                text = stringResource(R.string.label_pexels),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        val primaryColor = MaterialTheme.colorScheme.primary
        Icon(
            modifier = Modifier
                .padding(top = 10.dp)
                .drawWithContent {
                    drawContent()
                    drawPath(
                        path = drawCustomHexagonPath(size),
                        color = primaryColor,
                        style = Stroke(
                            width = 5.dp.toPx(),
                            pathEffect = PathEffect.cornerPathEffect(16f)
                        )
                    )
                }
                .background(
                    color = primaryColor,
                    shape = HexagonShape
                )
                .size(100.dp)
                .align(TopCenter),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "app logo",
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

val contributors = listOf(
    Contributor(
        name = "Javad Jafari",
        position = "Android Developer",
        image = R.drawable.jai_photo,
        links = listOf(
            ContributorLink(
                icon = R.drawable.ic_linkedin,
                url = "https://www.linkedin.com/in/javadjafari1/",
                name = "LinkedIn"
            ),
            ContributorLink(
                icon = R.drawable.ic_github,
                url = "https://github.com/javadjafari1",
                name = "Github"
            ),
            ContributorLink(
                icon = R.drawable.ic_mail,
                url = "javad2147@yahoo.com",
                name = "mail"
            ),
        )
    ),
    Contributor(
        name = "Mohammad Ghasemi",
        position = "UI/UX Designer",
        image = R.drawable.mmd_photo,
        links = listOf(
            ContributorLink(
                icon = R.drawable.ic_linkedin,
                url = "https://www.linkedin.com/in/mohammadghasemi-de/",
                name = "LinkedIn"
            ),
            ContributorLink(
                icon = R.drawable.ic_mail,
                url = "Mohammadghasemi.de@gmail.com",
                name = "mail"
            ),
            ContributorLink(
                icon = R.drawable.ic_dribble,
                url = "https://dribbble.com/MohammadGhasemi-de",
                name = "dribble"
            ),
        )
    )
)
