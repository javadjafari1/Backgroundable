package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.common.ui.ZoomableCoilImage
import ir.thatsmejavad.backgroundable.core.AppScreens
import ir.thatsmejavad.backgroundable.core.capitalizeFirstChar
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.openUrl
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob.Fail
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob.Loading
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob.Success
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob.Uninitialized
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality.Companion.toResourceSize
import ir.thatsmejavad.backgroundable.core.sealeds.OrientationMode
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.core.setAsWallpaper
import ir.thatsmejavad.backgroundable.core.shareFileWithUri
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.core.toast
import ir.thatsmejavad.backgroundable.core.viewmodel.daggerViewModel
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@Composable
fun MediaDetailScreen(
    mediaId: Int,
    title: String,
    navController: NavController,
    viewModel: MediaDetailViewModel = daggerViewModel()
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val fileUri by viewModel.fileUri.collectAsStateWithLifecycle()
    val imageQuality by viewModel.imageQuality.collectAsStateWithLifecycle()
    val savePurpose by viewModel.savePurpose.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    viewModel.snackbarManager.ObserveSnackbars(snackbarHostState)
    val context = LocalContext.current

    LaunchedEffect(key1 = fileUri) {
        if (fileUri is Success) {
            when (savePurpose) {
                is SavePurpose.Share -> {
                    val media = (mediaResult as Success).value
                    (fileUri as Success).value.shareFileWithUri(
                        context = context,
                        contentType = "image/plain",
                        text = "${media.media.alt} by ${media.media.photographer} from Backgroundable",
                        onFail = {
                            context.toast(
                                R.string.label_no_app_found_to_handle_this_request
                            )
                        }
                    )
                }

                is SavePurpose.SettingWallpaper -> {
                    (fileUri as Success).value.setAsWallpaper(
                        context = context,
                        onError = {
                            context.toast(
                                R.string.label_no_app_found_to_handle_this_request
                            )
                        }
                    )
                }
            }
        }
    }

    MediaDetailScreen(
        title = title,
        fileUri = fileUri,
        mediaId = mediaId,
        savePurpose = savePurpose,
        mediaResult = mediaResult,
        imageQuality = imageQuality,
        snackbarHostState = snackbarHostState,
        onBackClicked = { navController.navigateUp() },
        navigateTo = {
            navController.navigate(it) {
                launchSingleTop = true
            }
        },
        onRetryClick = { viewModel.getMedia(mediaId) },
        openLink = {
            context.openUrl(it)
        },
        setAsWallpaper = { drawable ->
            if (fileUri is Success) {
                (fileUri as Success).value.setAsWallpaper(
                    context = context,
                    onError = {
                        context.toast(
                            R.string.label_no_app_found_to_handle_this_request
                        )
                    }
                )
            } else {
                viewModel.saveFile(
                    purpose = SavePurpose.SettingWallpaper,
                    context = context,
                    drawable = drawable
                )
            }
        },
        share = { drawable, name, photographer ->
            if (fileUri is Success) {
                (fileUri as Success).value.shareFileWithUri(
                    context = context,
                    contentType = "image/plain",
                    text = "$name by $photographer from Backgroundable",
                    onFail = {
                        context.toast(
                            R.string.label_no_app_found_to_handle_this_request
                        )
                    }
                )
            } else {
                viewModel.saveFile(
                    purpose = SavePurpose.Share,
                    context = context,
                    drawable = drawable
                )
            }
        }
    )
}

@Composable
private fun MediaDetailScreen(
    mediaId: Int,
    title: String,
    snackbarHostState: SnackbarHostState,
    mediaResult: AsyncJob<MediaWithResources>,
    imageQuality: ImageQuality,
    savePurpose: SavePurpose,
    fileUri: AsyncJob<Uri>,
    onRetryClick: () -> Unit,
    onBackClicked: () -> Unit,
    navigateTo: (route: String) -> Unit,
    openLink: (String) -> Unit,
    setAsWallpaper: (Drawable) -> Unit,
    share: (Drawable, name: String, photographer: String) -> Unit
) {
    var isToolsVisible by rememberSaveable { mutableStateOf(true) }
    var isImageLoading by rememberSaveable { mutableStateOf(false) }
    var isDetailDialogShowing by rememberSaveable { mutableStateOf(false) }

    BackgroundableScaffold(
        contentWindowInsets = if (isToolsVisible) {
            ScaffoldDefaults.contentWindowInsets
        } else {
            WindowInsets(0, 0, 0, 0)
        },
        topBar = {
            AnimatedVisibility(
                visible = isToolsVisible && title.isNotEmpty(),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                MediumTopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.basicMarquee(),
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClicked) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { isDetailDialogShowing = true }) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "info"
                            )
                        }
                    }
                )
            }
        },
        snackbarHostState = snackbarHostState
    ) { paddingValues ->
        when (mediaResult) {
            is Fail -> {
                Text(
                    text = mediaResult
                        .exception
                        .getErrorMessage()
                        .asString()
                )
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(onClick = onRetryClick) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }

            Loading, Uninitialized -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Success -> {
                val mediaWithResources = mediaResult.value
                var drawable by remember { mutableStateOf<Drawable?>(null) }

                if (isDetailDialogShowing) {
                    DetailDialog(
                        alt = mediaWithResources.media.alt,
                        avgColor = mediaWithResources.media.avgColor,
                        photographer = mediaWithResources.media.photographer,
                        sizes = mediaWithResources.resources.map { it.size },
                        onDismiss = { isDetailDialogShowing = false },
                        openPhotographerLink = {
                            openLink(mediaWithResources.media.photographerUrl)
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AnimatedVisibility(isImageLoading) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                    ZoomableCoilImage(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        onClick = { isToolsVisible = !isToolsVisible },
                        url = mediaWithResources
                            .resources
                            .first { it.size == ResourceSize.Original }
                            .url,
                        placeHolder = mediaWithResources
                            .resources
                            .first { it.size == imageQuality.toResourceSize() }
                            .url,
                        contentDescription = mediaWithResources.media.alt,
                        isLoading = { isImageLoading = it },
                        onDrawableLoaded = { drawable = it }
                    )
                    AnimatedVisibility(visible = isToolsVisible && drawable != null) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            ElevatedButton(
                                modifier = Modifier
                                    .height(56.dp)
                                    .weight(1f),
                                enabled = fileUri !is Loading,
                                onClick = { setAsWallpaper(drawable!!) },
                                shape = MaterialTheme.shapes.extraSmall,
                                elevation = ButtonDefaults.elevatedButtonElevation(
                                    defaultElevation = 2.dp,
                                    pressedElevation = 5.dp
                                ),
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                if (fileUri is Loading && savePurpose == SavePurpose.SettingWallpaper) {
                                    CircularProgressIndicator(modifier = Modifier.size(36.dp))
                                } else {
                                    Text(
                                        text = stringResource(R.string.label_set_as_wallpaper)
                                    )
                                }
                            }

                            OutlinedIconButton(
                                modifier = Modifier.size(56.dp),
                                shape = MaterialTheme.shapes.extraSmall,
                                enabled = fileUri !is Loading,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                onClick = {
                                    share(
                                        drawable!!,
                                        mediaWithResources.media.alt,
                                        mediaWithResources.media.photographer
                                    )
                                },
                            ) {
                                if (fileUri is Loading && savePurpose == SavePurpose.Share) {
                                    CircularProgressIndicator(modifier = Modifier.size(36.dp))
                                } else {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_share),
                                        contentDescription = "share",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            OutlinedIconButton(
                                modifier = Modifier.size(56.dp),
                                shape = MaterialTheme.shapes.extraSmall,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                onClick = { navigateTo(AppScreens.DownloadPicker.createRoute(mediaId)) },
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_download),
                                    contentDescription = "Download",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailDialog(
    alt: String,
    avgColor: String,
    photographer: String,
    sizes: List<ResourceSize>,
    onDismiss: () -> Unit,
    openPhotographerLink: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = MaterialTheme.shapes.small
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_image),
                    contentDescription = "name"
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = alt,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_photographer),
                    contentDescription = "name"
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    modifier = Modifier.clickable { openPhotographerLink() },
                    text = photographer,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_palette),
                    contentDescription = "name"
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = avgColor,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.size(12.dp))

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(avgColor.toColor())
                )
            }

            val sizesString = remember(sizes) {
                sizes
                    .filter { it !is OrientationMode }
                    .joinToString(", ") { it.size.capitalizeFirstChar() }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_sizes),
                    contentDescription = "name"
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = sizesString,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            val orientationString = remember(sizes) {
                sizes
                    .filter { it is OrientationMode }
                    .joinToString(", ") { it.size.capitalizeFirstChar() }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_orientations),
                    contentDescription = "name"
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = orientationString,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onDismiss,
            ) {
                Text(text = stringResource(R.string.label_ok))
            }
        }
    }
}
