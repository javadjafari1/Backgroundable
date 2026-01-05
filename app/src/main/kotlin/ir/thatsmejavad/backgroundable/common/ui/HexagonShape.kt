package ir.thatsmejavad.backgroundable.common.ui

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import java.lang.Float.min
import kotlin.math.sqrt

object HexagonShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawCustomHexagonPath(size)
        )
    }
}

fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
        val radius = min(size.width / 2f, size.height / 2f)
        customHexagon(radius, size)
    }
}

private fun Path.customHexagon(
    radius: Float,
    size: Size
) {
    val triangleHeight = (sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2

    moveTo(x = centerX, y = centerY + radius)
    lineTo(x = (centerX - triangleHeight).toFloat(), y = centerY + radius / 2)
    lineTo(x = (centerX - triangleHeight).toFloat(), y = centerY - radius / 2)
    lineTo(x = centerX, y = centerY - radius)
    lineTo(x = (centerX + triangleHeight).toFloat(), y = centerY - radius / 2)
    lineTo(x = (centerX + triangleHeight).toFloat(), y = centerY + radius / 2)

    close()
}
