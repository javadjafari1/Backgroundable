package ir.thatsmejavad.backgroundable.screens.mediadetail

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.BackgroundableScaffold
import ir.thatsmejavad.backgroundable.common.ui.ObserveSnackbars
import ir.thatsmejavad.backgroundable.common.ui.ZoomableCoilImage
import ir.thatsmejavad.backgroundable.core.capitalizeFirstChar
import ir.thatsmejavad.backgroundable.core.getErrorMessage
import ir.thatsmejavad.backgroundable.core.openUrl
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.sealeds.OrientationMode
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import ir.thatsmejavad.backgroundable.core.setAsWallpaper
import ir.thatsmejavad.backgroundable.core.shareFileWithUri
import ir.thatsmejavad.backgroundable.core.toColor
import ir.thatsmejavad.backgroundable.core.toast

@Composable
fun MediaDetailScreen(
    mediaId: Int,
    title: String,
    viewModel: MediaDetailViewModel,
    onBackClicked: () -> Unit,
    navigateToDownloadPicker: () -> Unit,
) {
    val mediaResult by viewModel.media.collectAsStateWithLifecycle()
    val fileUrl by viewModel.fileUri.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isToolsVisible by rememberSaveable { mutableStateOf(true) }
    var isImageLoading by rememberSaveable { mutableStateOf(false) }
    var isDetailDialogShowing by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    viewModel.snackbarManager.ObserveSnackbars(snackbarHostState)

    LaunchedEffect(key1 = fileUrl) {
        if (fileUrl is AsyncJob.Success) {
            val uri = (fileUrl as AsyncJob.Success).value
            when (viewModel.savePurpose) {
                is SavePurpose.Share -> {
                    val media = (mediaResult as AsyncJob.Success).value
                    uri.shareFileWithUri(
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
                    uri.setAsWallpaper(
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

    BackgroundableScaffold(
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
                                imageVector = Icons.Filled.ArrowBack,
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
            is AsyncJob.Fail -> {
                Text(
                    text = (mediaResult as AsyncJob.Fail)
                        .exception
                        .getErrorMessage()
                        .asString(context)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(onClick = { viewModel.getMedia(mediaId) }) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }

            AsyncJob.Loading, AsyncJob.Uninitialized -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is AsyncJob.Success -> {
                val mediaWithResources = (mediaResult as AsyncJob.Success).value
                var drawable by remember { mutableStateOf<Drawable?>(null) }

                if (isDetailDialogShowing) {
                    DetailDialog(
                        onDismiss = { isDetailDialogShowing = false },
                        width = mediaWithResources.media.width,
                        height = mediaWithResources.media.height,
                        alt = mediaWithResources.media.alt,
                        avgColor = mediaWithResources.media.avgColor,
                        photographer = mediaWithResources.media.photographer,
                        sizes = mediaWithResources.resources.map { it.size },
                        openPhotographerLink = {
                            context.openUrl(mediaWithResources.media.photographerUrl)
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
                        url = mediaWithResources.resources.first { it.size == ResourceSize.Original }.url,
                        placeHolder = mediaWithResources.resources.first { it.size == ResourceSize.Medium }.url,
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
                                enabled = fileUrl !is AsyncJob.Loading,
                                onClick = {
                                    if (fileUrl is AsyncJob.Success) {
                                        (fileUrl as AsyncJob.Success).value.setAsWallpaper(
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
                                            drawable = drawable!!,
                                            context = context
                                        )
                                    }
                                },
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
                                if (fileUrl is AsyncJob.Loading && viewModel.savePurpose == SavePurpose.SettingWallpaper) {
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
                                enabled = fileUrl !is AsyncJob.Loading,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                onClick = {
                                    if (fileUrl is AsyncJob.Success) {
                                        (fileUrl as AsyncJob.Success).value.shareFileWithUri(
                                            context = context,
                                            contentType = "image/plain",
                                            text = "${mediaWithResources.media.alt} by ${mediaWithResources.media.photographer} from Backgroundable",
                                            onFail = {
                                                context.toast(
                                                    R.string.label_no_app_found_to_handle_this_request
                                                )
                                            }
                                        )
                                    } else {
                                        viewModel.saveFile(
                                            purpose = SavePurpose.Share,
                                            drawable = drawable!!,
                                            context = context
                                        )
                                    }
                                },
                            ) {
                                if (fileUrl is AsyncJob.Loading && viewModel.savePurpose == SavePurpose.Share) {
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
                                onClick = navigateToDownloadPicker,
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
    width: Int,
    height: Int,
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
                    shape = MaterialTheme.shapes.large
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val wallpaperText = buildAnnotatedString {
                val text = stringResource(R.string.label_wallpaper)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )

                append(alt)
                addStyle(
                    style = SpanStyle(fontSize = 16.sp),
                    start = text.length,
                    end = alt.length + text.length
                )
            }
            Text(text = wallpaperText)

            val photographerText = buildAnnotatedString {
                val text = stringResource(R.string.label_photographer)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )

                append(photographer)
                addStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    start = text.length,
                    end = photographer.length + text.length
                )
                addStringAnnotation(
                    tag = "Name",
                    start = 12,
                    end = photographer.length + text.length,
                    annotation = "Link"
                )
            }
            ClickableText(
                text = photographerText,
                onClick = { offset ->
                    photographerText.getStringAnnotations(
                        start = offset,
                        end = offset,
                        tag = "Name"
                    ).firstOrNull()?.let { _ ->
                        openPhotographerLink()
                    }
                },
                style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val mainColorText = buildAnnotatedString {
                    val text = stringResource(R.string.label_main_color)
                    append(text)
                    addStyle(
                        style = SpanStyle(fontSize = 22.sp),
                        start = 0,
                        end = text.length
                    )

                    append(avgColor)
                    addStyle(
                        style = SpanStyle(fontSize = 16.sp),
                        start = text.length,
                        end = avgColor.length + text.length
                    )
                }
                Text(text = mainColorText)
                Spacer(modifier = Modifier.size(8.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(avgColor.toColor())
                )
            }

            val widthText = buildAnnotatedString {
                val text = stringResource(R.string.label_width)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )

                append(width.toString())
                addStyle(
                    style = SpanStyle(fontSize = 16.sp),
                    start = text.length,
                    end = width.toString().length + text.length
                )
            }
            Text(text = widthText)

            val heightText = buildAnnotatedString {
                val text = stringResource(R.string.label_height)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )

                append(height.toString())
                addStyle(
                    style = SpanStyle(fontSize = 16.sp),
                    start = text.length,
                    end = height.toString().length + text.length
                )
            }
            Text(text = heightText)

            val sizesString = remember(sizes) {
                sizes
                    .filter { it !is OrientationMode }
                    .joinToString(", ") { it.size.capitalizeFirstChar() }
            }

            val sizesText = buildAnnotatedString {
                val text = stringResource(R.string.label_sizes)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )

                append(sizesString)
                addStyle(
                    SpanStyle(fontSize = 16.sp),
                    start = text.length,
                    end = sizesString.length + text.length
                )
            }
            Text(text = sizesText)

            val orientationString = remember(sizes) {
                sizes
                    .filter { it is OrientationMode }
                    .joinToString(", ") { it.size.capitalizeFirstChar() }
            }

            val orientationText = buildAnnotatedString {
                val text = stringResource(R.string.label_orientation)
                append(text)
                addStyle(
                    style = SpanStyle(fontSize = 22.sp),
                    start = 0,
                    end = text.length
                )
                append(orientationString)
                addStyle(
                    SpanStyle(fontSize = 16.sp),
                    start = text.length,
                    end = orientationString.length + text.length
                )
            }
            Text(text = orientationText)

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onDismiss,
            ) {
                Text(text = stringResource(R.string.label_ok))
            }
        }
    }
}
