package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.core.toColor

@Composable
fun MediaCard(
    id: Int,
    alt: String,
    height: Int,
    avgColor: String,
    photographer: String,
    resourceUrl: String,
    isSingleColumn: Boolean,
    isStaggered: Boolean,
    onMediaClicked: (Int, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .animateContentSize()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onMediaClicked(id, alt) }
            .padding(4.dp)

    ) {
        CoilImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .then(
                    if (isStaggered) {
                        Modifier.heightIn(max = if (isSingleColumn) 420.dp else (height / 20).dp)
                    } else {
                        Modifier.aspectRatio(1f)
                    }
                ),
            url = resourceUrl,
            contentDescription = alt,
            placeHolder = ColorPainter(avgColor.toColor())
        )
        Spacer(modifier = Modifier.padding(4.dp))
        val text = buildAnnotatedString {
            append(alt)
            addStyle(
                SpanStyle(fontWeight = FontWeight.Bold),
                0,
                alt.length
            )
            append(" by ")
            append(photographer)
        }
        Text(
            text = text,
            maxLines = if (isStaggered) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
