package ir.thatsmejavad.backgroundable.common.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.ui.BackgroundableTheme

@Preview
@Composable
private fun SearchImagePreviewLight() {
    BackgroundableTheme(
        themeColor = ThemeColor.Ao,
        darkTheme = false
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(
                getSearchImage(MaterialTheme.colorScheme.primary),
                null
            )
        }
    }
}

@Preview
@Composable
private fun SearchImagePreviewDark() {
    BackgroundableTheme(
        themeColor = ThemeColor.Ao,
        darkTheme = true
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(
                getSearchImage(MaterialTheme.colorScheme.primary),
                null
            )
        }
    }
}

@Suppress("LongMethod")
internal fun getSearchImage(color: Color): ImageVector {
    return ImageVector.Builder(
        name = "search_image",
        defaultWidth = 286.dp,
        defaultHeight = 253.dp,
        viewportWidth = 286f,
        viewportHeight = 253f
    ).apply {
        path(
            fill = SolidColor(Color(0xFFFAFAFA)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(142.556f, 252.207f)
            curveTo(221.287f, 252.207f, 285.111f, 224.297f, 285.111f, 189.868f)
            curveTo(285.111f, 155.439f, 221.287f, 127.528f, 142.556f, 127.528f)
            curveTo(63.8243f, 127.528f, 0f, 155.439f, 0f, 189.868f)
            curveTo(0f, 224.297f, 63.8243f, 252.207f, 142.556f, 252.207f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(235.723f, 204.421f)
            curveTo(252.588f, 204.421f, 266.259f, 197.905f, 266.259f, 189.868f)
            curveTo(266.259f, 181.83f, 252.588f, 175.314f, 235.723f, 175.314f)
            curveTo(218.858f, 175.314f, 205.187f, 181.83f, 205.187f, 189.868f)
            curveTo(205.187f, 197.905f, 218.858f, 204.421f, 235.723f, 204.421f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(46.0588f, 173.892f)
            curveTo(46.0588f, 173.892f, 46.6799f, 161.984f, 40.7479f, 153.58f)
            curveTo(34.8158f, 145.175f, 27.3682f, 145.002f, 24.3804f, 146.2f)
            curveTo(21.3926f, 147.399f, 19.113f, 151.381f, 23.7592f, 154.797f)
            curveTo(28.4055f, 158.214f, 38.2881f, 165.115f, 39.5366f, 170.581f)
            lineTo(46.0588f, 173.892f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.15f,
            stroke = null,
            strokeAlpha = 0.15f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(46.0588f, 173.892f)
            curveTo(46.0588f, 173.892f, 46.6799f, 161.984f, 40.7479f, 153.58f)
            curveTo(34.8158f, 145.175f, 27.3682f, 145.002f, 24.3804f, 146.2f)
            curveTo(21.3926f, 147.399f, 19.113f, 151.381f, 23.7592f, 154.797f)
            curveTo(28.4055f, 158.214f, 38.2881f, 165.115f, 39.5366f, 170.581f)
            lineTo(46.0588f, 173.892f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFFFFF)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(43.8909f, 171.525f)
            curveTo(43.8193f, 171.525f, 43.7499f, 171.5f, 43.6945f, 171.455f)
            curveTo(43.639f, 171.41f, 43.6009f, 171.347f, 43.5866f, 171.276f)
            curveTo(40.2944f, 155.033f, 30.6727f, 150.486f, 26.6911f, 149.281f)
            curveTo(26.612f, 149.258f, 26.5453f, 149.205f, 26.5057f, 149.132f)
            curveTo(26.4661f, 149.06f, 26.4568f, 148.975f, 26.4799f, 148.896f)
            curveTo(26.5056f, 148.818f, 26.5609f, 148.753f, 26.6339f, 148.714f)
            curveTo(26.707f, 148.676f, 26.7921f, 148.668f, 26.8712f, 148.691f)
            curveTo(30.9584f, 149.934f, 40.8348f, 154.58f, 44.1953f, 171.152f)
            curveTo(44.2043f, 171.192f, 44.2052f, 171.234f, 44.1978f, 171.274f)
            curveTo(44.1904f, 171.314f, 44.1749f, 171.353f, 44.1523f, 171.387f)
            curveTo(44.1297f, 171.421f, 44.1004f, 171.451f, 44.0661f, 171.473f)
            curveTo(44.0319f, 171.496f, 43.9934f, 171.511f, 43.953f, 171.519f)
            lineTo(43.8909f, 171.525f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(40.5181f, 167.022f)
            curveTo(39.2074f, 163.09f, 37.4806f, 161.22f, 32.0082f, 161.636f)
            curveTo(26.5358f, 162.052f, 27.3371f, 161.984f, 20.8646f, 159.692f)
            curveTo(14.3922f, 157.4f, 9.5596f, 159.692f, 8.8453f, 162.214f)
            curveTo(8.0688f, 164.934f, 13.3673f, 166.643f, 17.2371f, 168.81f)
            curveTo(21.1069f, 170.978f, 23.6661f, 173.519f, 22.3741f, 177.059f)
            curveTo(21.2684f, 180.097f, 21.5479f, 182.774f, 26.2377f, 184.339f)
            curveTo(30.4118f, 185.731f, 35.1823f, 183.681f, 36.5675f, 187.917f)
            curveTo(37.3564f, 190.334f, 41.1082f, 193.228f, 44.9718f, 191.396f)
            curveTo(51.8852f, 185.6f, 50.8044f, 177.668f, 47.2017f, 172.183f)
            curveTo(45.6178f, 169.773f, 41.3069f, 169.388f, 40.5181f, 167.022f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFFFFF)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(44.363f, 184.588f)
            curveTo(44.2982f, 184.588f, 44.235f, 184.568f, 44.1825f, 184.53f)
            curveTo(44.1299f, 184.492f, 44.0909f, 184.438f, 44.0711f, 184.377f)
            curveTo(39.9963f, 172.773f, 20.8088f, 162.704f, 13.7959f, 161.568f)
            curveTo(13.7149f, 161.553f, 13.6431f, 161.507f, 13.5955f, 161.439f)
            curveTo(13.548f, 161.372f, 13.5285f, 161.289f, 13.5412f, 161.208f)
            curveTo(13.5472f, 161.167f, 13.5612f, 161.129f, 13.5824f, 161.094f)
            curveTo(13.6036f, 161.059f, 13.6315f, 161.029f, 13.6645f, 161.005f)
            curveTo(13.6975f, 160.982f, 13.7349f, 160.965f, 13.7746f, 160.956f)
            curveTo(13.8142f, 160.947f, 13.8552f, 160.946f, 13.8953f, 160.953f)
            curveTo(21.0137f, 162.114f, 40.5056f, 172.357f, 44.655f, 184.172f)
            curveTo(44.683f, 184.249f, 44.679f, 184.334f, 44.6441f, 184.409f)
            curveTo(44.6091f, 184.484f, 44.546f, 184.541f, 44.4686f, 184.569f)
            lineTo(44.363f, 184.588f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFFFFF)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(27.157f, 178.277f)
            curveTo(27.1095f, 178.276f, 27.0627f, 178.265f, 27.0198f, 178.245f)
            curveTo(26.9769f, 178.225f, 26.9389f, 178.195f, 26.9085f, 178.159f)
            curveTo(26.86f, 178.093f, 26.8387f, 178.011f, 26.8491f, 177.93f)
            curveTo(26.8595f, 177.849f, 26.9008f, 177.775f, 26.9644f, 177.724f)
            curveTo(30.0704f, 175.568f, 33.7439f, 174.377f, 37.5241f, 174.302f)
            curveTo(37.6064f, 174.302f, 37.6854f, 174.334f, 37.7437f, 174.392f)
            curveTo(37.8019f, 174.451f, 37.8346f, 174.53f, 37.8346f, 174.612f)
            curveTo(37.8346f, 174.694f, 37.8019f, 174.773f, 37.7437f, 174.832f)
            curveTo(37.6854f, 174.89f, 37.6064f, 174.923f, 37.5241f, 174.923f)
            curveTo(33.8881f, 175.017f, 30.356f, 176.155f, 27.3495f, 178.202f)
            curveTo(27.2957f, 178.248f, 27.2278f, 178.275f, 27.157f, 178.277f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(164.135f, 6.27229f)
            verticalLineTo(119.348f)
            lineTo(49.2639f, 185.662f)
            lineTo(51.8541f, 190.967f)
            lineTo(164.135f, 126.149f)
            lineTo(169.153f, 126.615f)
            verticalLineTo(6.75679f)
            lineTo(164.135f, 6.27229f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(141.046f, 68.5184f)
            lineTo(141.984f, 67.2078f)
            lineTo(136.052f, 63.779f)
            horizontalLineTo(136.009f)
            curveTo(135.572f, 63.5276f, 135.056f, 63.4518f, 134.566f, 63.5672f)
            curveTo(134.075f, 63.6826f, 133.647f, 63.9804f, 133.369f, 64.4002f)
            lineTo(117.007f, 89.5881f)
            lineTo(100.056f, 74.9102f)
            lineTo(100.758f, 73.8542f)
            lineTo(94.8817f, 70.4627f)
            curveTo(94.4422f, 70.1984f, 93.917f, 70.1154f, 93.4174f, 70.2313f)
            curveTo(92.9178f, 70.3472f, 92.4828f, 70.653f, 92.2045f, 71.0838f)
            lineTo(49.2701f, 135.504f)
            verticalLineTo(181.637f)
            lineTo(55.1587f, 185.035f)
            lineTo(161.19f, 120.435f)
            verticalLineTo(87.8923f)
            lineTo(141.046f, 68.5184f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFAFAFA)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(55.1587f, 185.035f)
            verticalLineTo(138.908f)
            lineTo(98.0869f, 74.4691f)
            curveTo(98.2424f, 74.2341f, 98.4463f, 74.0349f, 98.6849f, 73.8849f)
            curveTo(98.9236f, 73.7349f, 99.1915f, 73.6376f, 99.4707f, 73.5994f)
            curveTo(99.75f, 73.5611f, 100.034f, 73.5829f, 100.304f, 73.6633f)
            curveTo(100.574f, 73.7437f, 100.824f, 73.8807f, 101.037f, 74.0654f)
            lineTo(122.896f, 92.992f)
            lineTo(139.257f, 67.8041f)
            curveTo(139.417f, 67.5589f, 139.629f, 67.3519f, 139.878f, 67.1981f)
            curveTo(140.127f, 67.0442f, 140.406f, 66.9472f, 140.697f, 66.9139f)
            curveTo(140.988f, 66.8807f, 141.282f, 66.9121f, 141.56f, 67.0058f)
            curveTo(141.837f, 67.0996f, 142.09f, 67.2534f, 142.301f, 67.4563f)
            lineTo(167.073f, 91.2963f)
            verticalLineTo(118.478f)
            curveTo(167.072f, 119.07f, 166.917f, 119.652f, 166.622f, 120.166f)
            curveTo(166.326f, 120.679f, 165.901f, 121.106f, 165.389f, 121.404f)
            lineTo(55.1587f, 185.035f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(122.896f, 92.992f)
            lineTo(131.735f, 100.645f)
            lineTo(124.002f, 91.2963f)
            lineTo(122.896f, 92.992f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFFFFF)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(172.949f, 4.42745f)
            curveTo(172.943f, 4.3622f, 172.93f, 4.2976f, 172.912f, 4.2349f)
            curveTo(172.825f, 3.7744f, 172.646f, 3.3362f, 172.385f, 2.9465f)
            curveTo(172.125f, 2.557f, 171.788f, 2.224f, 171.396f, 1.9677f)
            lineTo(168.644f, 0.377503f)
            curveTo(168.157f, 0.1294f, 167.619f, 0f, 167.073f, 0f)
            curveTo(166.526f, 0f, 165.988f, 0.1294f, 165.501f, 0.3775f)
            lineTo(44.9531f, 69.9782f)
            curveTo(44.4896f, 70.2716f, 44.1055f, 70.6748f, 43.835f, 71.1522f)
            curveTo(43.5559f, 71.6212f, 43.3999f, 72.1533f, 43.3816f, 72.6988f)
            verticalLineTo(191.501f)
            curveTo(43.4112f, 192.046f, 43.569f, 192.577f, 43.842f, 193.05f)
            curveTo(44.1151f, 193.522f, 44.4957f, 193.924f, 44.9531f, 194.222f)
            lineTo(47.6986f, 195.812f)
            curveTo(48.1259f, 196.027f, 48.5927f, 196.152f, 49.0702f, 196.18f)
            curveTo(49.5477f, 196.208f, 50.0259f, 196.138f, 50.4752f, 195.974f)
            lineTo(50.587f, 195.93f)
            curveTo(50.6759f, 195.9f, 50.7612f, 195.86f, 50.8417f, 195.812f)
            lineTo(85.7197f, 175.674f)
            lineTo(171.396f, 126.212f)
            curveTo(171.852f, 125.912f, 172.232f, 125.51f, 172.505f, 125.038f)
            curveTo(172.778f, 124.566f, 172.936f, 124.036f, 172.967f, 123.491f)
            verticalLineTo(4.68212f)
            curveTo(172.992f, 4.5952f, 172.955f, 4.5082f, 172.949f, 4.4275f)
            close()
            moveTo(169.154f, 121.404f)
            curveTo(169.124f, 121.95f, 168.965f, 122.481f, 168.691f, 122.953f)
            curveTo(168.417f, 123.426f, 168.035f, 123.827f, 167.576f, 124.124f)
            lineTo(84.7879f, 171.68f)
            lineTo(53.786f, 189.837f)
            curveTo(52.9226f, 190.334f, 52.2144f, 189.93f, 52.2144f, 188.93f)
            verticalLineTo(77.7985f)
            curveTo(52.2329f, 77.2541f, 52.3912f, 76.7236f, 52.6741f, 76.2581f)
            curveTo(52.9316f, 75.7733f, 53.3159f, 75.3675f, 53.786f, 75.0841f)
            lineTo(167.576f, 9.37186f)
            curveTo(168.452f, 8.8625f, 169.154f, 9.2663f, 169.154f, 10.2725f)
            verticalLineTo(121.404f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(50.8417f, 195.812f)
            curveTo(50.7612f, 195.86f, 50.6759f, 195.9f, 50.587f, 195.93f)
            lineTo(50.4752f, 195.974f)
            curveTo(50.0259f, 196.138f, 49.5477f, 196.208f, 49.0702f, 196.18f)
            curveTo(48.5927f, 196.153f, 48.1259f, 196.027f, 47.6986f, 195.812f)
            lineTo(44.9531f, 194.222f)
            curveTo(44.4957f, 193.924f, 44.1151f, 193.522f, 43.842f, 193.05f)
            curveTo(43.569f, 192.577f, 43.4112f, 192.046f, 43.3816f, 191.501f)
            verticalLineTo(72.6988f)
            curveTo(43.3999f, 72.1533f, 43.5559f, 71.6212f, 43.835f, 71.1522f)
            lineTo(49.7236f, 74.5499f)
            curveTo(50.6802f, 75.1027f, 51.6678f, 75.668f, 52.6741f, 76.2581f)
            curveTo(52.3912f, 76.7236f, 52.2329f, 77.2541f, 52.2144f, 77.7985f)
            verticalLineTo(188.93f)
            curveTo(52.2144f, 189.93f, 52.9226f, 190.334f, 53.786f, 189.837f)
            lineTo(50.8417f, 195.812f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFAFAFA)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(172.949f, 4.42745f)
            curveTo(172.943f, 4.3622f, 172.93f, 4.2976f, 172.911f, 4.2349f)
            curveTo(172.725f, 3.5765f, 172.116f, 3.3591f, 171.396f, 3.7752f)
            lineTo(50.8417f, 73.3883f)
            curveTo(50.3804f, 73.6813f, 49.9964f, 74.0808f, 49.7218f, 74.5532f)
            curveTo(49.4472f, 75.0256f, 49.2902f, 75.557f, 49.2639f, 76.1028f)
            verticalLineTo(194.905f)
            curveTo(49.2639f, 195.756f, 49.7795f, 196.179f, 50.469f, 195.974f)
            lineTo(50.5808f, 195.93f)
            curveTo(50.6696f, 195.9f, 50.755f, 195.861f, 50.8354f, 195.812f)
            lineTo(171.39f, 126.243f)
            curveTo(171.846f, 125.943f, 172.226f, 125.542f, 172.499f, 125.069f)
            curveTo(172.772f, 124.597f, 172.93f, 124.067f, 172.961f, 123.522f)
            verticalLineTo(4.68212f)
            curveTo(172.992f, 4.5952f, 172.955f, 4.5082f, 172.949f, 4.4275f)
            close()
            moveTo(169.153f, 121.404f)
            curveTo(169.124f, 121.95f, 168.965f, 122.481f, 168.691f, 122.953f)
            curveTo(168.417f, 123.426f, 168.035f, 123.827f, 167.576f, 124.124f)
            lineTo(53.7859f, 189.837f)
            curveTo(52.9225f, 190.334f, 52.2144f, 189.93f, 52.2144f, 188.93f)
            verticalLineTo(77.7985f)
            curveTo(52.2329f, 77.2541f, 52.3912f, 76.7236f, 52.6741f, 76.2581f)
            curveTo(52.9316f, 75.7733f, 53.3159f, 75.3675f, 53.7859f, 75.0841f)
            lineTo(167.576f, 9.37186f)
            curveTo(168.452f, 8.8625f, 169.153f, 9.2663f, 169.153f, 10.2725f)
            verticalLineTo(121.404f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(151.916f, 39.8086f)
            lineTo(154.892f, 34.7523f)
            lineTo(149.798f, 31.7832f)
            curveTo(148.301f, 30.926f, 146.239f, 31.0502f, 143.966f, 32.3671f)
            curveTo(139.406f, 35.0008f, 135.704f, 41.4049f, 135.704f, 46.6537f)
            curveTo(135.704f, 49.2501f, 136.611f, 51.0826f, 138.065f, 51.9584f)
            horizontalLineTo(138.108f)
            lineTo(143.015f, 54.8095f)
            lineTo(145.86f, 49.9893f)
            curveTo(148.896f, 47.314f, 151.014f, 43.7528f, 151.916f, 39.8086f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFAFAFA)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(154.793f, 48.1755f)
            curveTo(158.017f, 42.5901f, 158.019f, 36.5536f, 154.795f, 34.6925f)
            curveTo(151.572f, 32.8314f, 146.344f, 35.8506f, 143.12f, 41.4359f)
            curveTo(139.895f, 47.0213f, 139.894f, 53.0579f, 143.117f, 54.919f)
            curveTo(146.341f, 56.78f, 151.568f, 53.7609f, 154.793f, 48.1755f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(178.421f, 14.3473f)
            verticalLineTo(127.423f)
            lineTo(63.5505f, 193.738f)
            lineTo(66.1408f, 199.042f)
            lineTo(178.421f, 134.224f)
            lineTo(183.44f, 134.69f)
            verticalLineTo(14.8318f)
            lineTo(178.421f, 14.3473f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(155.333f, 76.5935f)
            lineTo(156.271f, 75.2828f)
            lineTo(150.339f, 71.8541f)
            horizontalLineTo(150.295f)
            curveTo(149.859f, 71.6026f, 149.343f, 71.5269f, 148.852f, 71.6422f)
            curveTo(148.362f, 71.7576f, 147.934f, 72.0555f, 147.655f, 72.4752f)
            lineTo(131.294f, 97.6631f)
            lineTo(114.343f, 82.9852f)
            lineTo(115.044f, 81.9292f)
            lineTo(109.168f, 78.5377f)
            curveTo(108.729f, 78.2734f, 108.204f, 78.1904f, 107.704f, 78.3064f)
            curveTo(107.204f, 78.4223f, 106.769f, 78.728f, 106.491f, 79.1589f)
            lineTo(63.5568f, 143.579f)
            verticalLineTo(189.712f)
            lineTo(69.4453f, 193.11f)
            lineTo(175.477f, 128.51f)
            verticalLineTo(95.9674f)
            lineTo(155.333f, 76.5935f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF5F5F5)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(69.4453f, 193.11f)
            verticalLineTo(146.983f)
            lineTo(112.373f, 82.5442f)
            curveTo(112.529f, 82.3091f, 112.733f, 82.1099f, 112.972f, 81.96f)
            curveTo(113.21f, 81.81f, 113.478f, 81.7126f, 113.757f, 81.6744f)
            curveTo(114.037f, 81.6362f, 114.321f, 81.658f, 114.591f, 81.7383f)
            curveTo(114.861f, 81.8187f, 115.111f, 81.9558f, 115.324f, 82.1404f)
            lineTo(137.183f, 101.067f)
            lineTo(153.544f, 75.8792f)
            curveTo(153.703f, 75.6339f, 153.915f, 75.427f, 154.164f, 75.2731f)
            curveTo(154.413f, 75.1192f, 154.693f, 75.0222f, 154.984f, 74.989f)
            curveTo(155.275f, 74.9557f, 155.569f, 74.9871f, 155.846f, 75.0809f)
            curveTo(156.123f, 75.1746f, 156.377f, 75.3284f, 156.587f, 75.5313f)
            lineTo(181.359f, 99.3713f)
            verticalLineTo(126.553f)
            curveTo(181.359f, 127.145f, 181.204f, 127.727f, 180.908f, 128.241f)
            curveTo(180.613f, 128.754f, 180.188f, 129.181f, 179.676f, 129.479f)
            lineTo(69.4453f, 193.11f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(137.183f, 101.067f)
            lineTo(146.022f, 108.72f)
            lineTo(138.288f, 99.3713f)
            lineTo(137.183f, 101.067f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFAFAFA)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(187.235f, 12.5025f)
            curveTo(187.23f, 12.4372f, 187.217f, 12.3727f, 187.198f, 12.3099f)
            curveTo(187.111f, 11.8494f, 186.932f, 11.4112f, 186.672f, 11.0216f)
            curveTo(186.411f, 10.632f, 186.075f, 10.299f, 185.683f, 10.0427f)
            lineTo(182.931f, 8.45255f)
            curveTo(182.444f, 8.2044f, 181.906f, 8.075f, 181.359f, 8.075f)
            curveTo(180.813f, 8.075f, 180.274f, 8.2044f, 179.788f, 8.4526f)
            lineTo(59.2397f, 78.0532f)
            curveTo(58.7762f, 78.3466f, 58.3921f, 78.7499f, 58.1217f, 79.2272f)
            curveTo(57.8425f, 79.6963f, 57.6865f, 80.2283f, 57.6682f, 80.7739f)
            verticalLineTo(199.576f)
            curveTo(57.6978f, 200.121f, 57.8556f, 200.652f, 58.1286f, 201.125f)
            curveTo(58.4017f, 201.597f, 58.7824f, 201.999f, 59.2397f, 202.297f)
            lineTo(61.9853f, 203.887f)
            curveTo(62.4125f, 204.102f, 62.8793f, 204.228f, 63.3568f, 204.255f)
            curveTo(63.8344f, 204.283f, 64.3125f, 204.213f, 64.7618f, 204.049f)
            lineTo(64.8736f, 204.005f)
            curveTo(64.9625f, 203.975f, 65.0479f, 203.936f, 65.1283f, 203.887f)
            lineTo(100.006f, 183.749f)
            lineTo(185.683f, 134.287f)
            curveTo(186.139f, 133.987f, 186.519f, 133.586f, 186.792f, 133.113f)
            curveTo(187.065f, 132.641f, 187.223f, 132.111f, 187.254f, 131.566f)
            verticalLineTo(12.7572f)
            curveTo(187.279f, 12.6702f, 187.242f, 12.5832f, 187.235f, 12.5025f)
            close()
            moveTo(183.44f, 129.479f)
            curveTo(183.41f, 130.025f, 183.252f, 130.556f, 182.978f, 131.028f)
            curveTo(182.703f, 131.501f, 182.321f, 131.903f, 181.862f, 132.199f)
            lineTo(99.0746f, 179.755f)
            lineTo(68.0726f, 197.912f)
            curveTo(67.2092f, 198.409f, 66.5011f, 198.005f, 66.5011f, 197.005f)
            verticalLineTo(85.8736f)
            curveTo(66.5196f, 85.3291f, 66.6779f, 84.7986f, 66.9607f, 84.3331f)
            curveTo(67.2182f, 83.8483f, 67.6025f, 83.4426f, 68.0726f, 83.1591f)
            lineTo(181.862f, 17.4469f)
            curveTo(182.738f, 16.9376f, 183.44f, 17.3413f, 183.44f, 18.3476f)
            verticalLineTo(129.479f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(65.1283f, 203.887f)
            curveTo(65.0479f, 203.936f, 64.9625f, 203.975f, 64.8736f, 204.005f)
            lineTo(64.7618f, 204.049f)
            curveTo(64.3125f, 204.213f, 63.8344f, 204.283f, 63.3568f, 204.255f)
            curveTo(62.8793f, 204.228f, 62.4125f, 204.102f, 61.9853f, 203.887f)
            lineTo(59.2397f, 202.297f)
            curveTo(58.7824f, 201.999f, 58.4017f, 201.597f, 58.1286f, 201.125f)
            curveTo(57.8556f, 200.652f, 57.6978f, 200.121f, 57.6682f, 199.576f)
            verticalLineTo(80.7739f)
            curveTo(57.6865f, 80.2283f, 57.8425f, 79.6963f, 58.1217f, 79.2272f)
            lineTo(64.0102f, 82.6249f)
            curveTo(64.9668f, 83.1778f, 65.9545f, 83.743f, 66.9607f, 84.3331f)
            curveTo(66.6779f, 84.7986f, 66.5196f, 85.3291f, 66.5011f, 85.8736f)
            verticalLineTo(197.005f)
            curveTo(66.5011f, 198.005f, 67.2092f, 198.409f, 68.0726f, 197.912f)
            lineTo(65.1283f, 203.887f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF5F5F5)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(187.235f, 12.5025f)
            curveTo(187.23f, 12.4372f, 187.217f, 12.3727f, 187.198f, 12.3099f)
            curveTo(187.012f, 11.6515f, 186.403f, 11.4341f, 185.682f, 11.8503f)
            lineTo(65.1283f, 81.4634f)
            curveTo(64.6671f, 81.7563f, 64.283f, 82.1558f, 64.0084f, 82.6282f)
            curveTo(63.7339f, 83.1006f, 63.5768f, 83.6321f, 63.5505f, 84.1778f)
            verticalLineTo(202.98f)
            curveTo(63.5505f, 203.831f, 64.0661f, 204.254f, 64.7556f, 204.049f)
            lineTo(64.8674f, 204.005f)
            curveTo(64.9562f, 203.975f, 65.0416f, 203.936f, 65.1221f, 203.887f)
            lineTo(185.676f, 134.318f)
            curveTo(186.133f, 134.019f, 186.512f, 133.617f, 186.785f, 133.144f)
            curveTo(187.058f, 132.672f, 187.217f, 132.142f, 187.248f, 131.597f)
            verticalLineTo(12.7572f)
            curveTo(187.279f, 12.6702f, 187.242f, 12.5832f, 187.235f, 12.5025f)
            close()
            moveTo(183.44f, 129.479f)
            curveTo(183.41f, 130.025f, 183.252f, 130.556f, 182.978f, 131.028f)
            curveTo(182.703f, 131.501f, 182.321f, 131.903f, 181.862f, 132.199f)
            lineTo(68.0726f, 197.912f)
            curveTo(67.2092f, 198.409f, 66.501f, 198.005f, 66.501f, 197.005f)
            verticalLineTo(85.8736f)
            curveTo(66.5195f, 85.3292f, 66.6778f, 84.7986f, 66.9607f, 84.3331f)
            curveTo(67.2182f, 83.8483f, 67.6025f, 83.4426f, 68.0726f, 83.1591f)
            lineTo(181.862f, 17.4469f)
            curveTo(182.738f, 16.9376f, 183.44f, 17.3413f, 183.44f, 18.3476f)
            verticalLineTo(129.479f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(166.203f, 47.8836f)
            lineTo(169.178f, 42.8274f)
            lineTo(164.085f, 39.8582f)
            curveTo(162.588f, 39.0011f, 160.526f, 39.1253f, 158.252f, 40.4421f)
            curveTo(153.693f, 43.0758f, 149.991f, 49.48f, 149.991f, 54.7288f)
            curveTo(149.991f, 57.3252f, 150.898f, 59.1576f, 152.351f, 60.0334f)
            horizontalLineTo(152.395f)
            horizontalLineTo(152.426f)
            lineTo(157.333f, 62.8846f)
            lineTo(160.178f, 58.0644f)
            curveTo(163.202f, 55.3846f, 165.309f, 51.8239f, 166.203f, 47.8836f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF5F5F5)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(169.085f, 56.2514f)
            curveTo(172.309f, 50.666f, 172.31f, 44.6295f, 169.087f, 42.7684f)
            curveTo(165.863f, 40.9074f, 160.636f, 43.9265f, 157.411f, 49.5119f)
            curveTo(154.187f, 55.0973f, 154.186f, 61.1338f, 157.409f, 62.9949f)
            curveTo(160.632f, 64.856f, 165.86f, 61.8368f, 169.085f, 56.2514f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFE0E0E0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(192.708f, 22.4224f)
            verticalLineTo(135.498f)
            lineTo(77.8372f, 201.813f)
            lineTo(80.4336f, 207.117f)
            lineTo(192.708f, 142.3f)
            lineTo(197.727f, 142.765f)
            verticalLineTo(22.9069f)
            lineTo(192.708f, 22.4224f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFE6E6E6)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(169.619f, 84.6685f)
            lineTo(170.557f, 83.3579f)
            lineTo(164.625f, 79.9291f)
            horizontalLineTo(164.582f)
            curveTo(164.145f, 79.6776f, 163.629f, 79.6019f, 163.139f, 79.7173f)
            curveTo(162.649f, 79.8327f, 162.22f, 80.1305f, 161.942f, 80.5503f)
            lineTo(145.581f, 105.738f)
            lineTo(128.629f, 91.0602f)
            lineTo(129.331f, 90.0043f)
            lineTo(123.455f, 86.6128f)
            curveTo(123.015f, 86.3484f, 122.49f, 86.2655f, 121.991f, 86.3814f)
            curveTo(121.491f, 86.4973f, 121.056f, 86.8031f, 120.778f, 87.2339f)
            lineTo(77.8434f, 151.654f)
            verticalLineTo(197.787f)
            lineTo(83.732f, 201.185f)
            lineTo(189.763f, 136.585f)
            verticalLineTo(104.042f)
            lineTo(169.619f, 84.6685f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(83.7319f, 201.185f)
            verticalLineTo(155.058f)
            lineTo(126.66f, 90.6192f)
            curveTo(126.816f, 90.3842f, 127.02f, 90.185f, 127.258f, 90.035f)
            curveTo(127.497f, 89.885f, 127.765f, 89.7877f, 128.044f, 89.7494f)
            curveTo(128.323f, 89.7112f, 128.607f, 89.733f, 128.878f, 89.8134f)
            curveTo(129.148f, 89.8938f, 129.398f, 90.0308f, 129.611f, 90.2155f)
            lineTo(151.469f, 109.142f)
            lineTo(167.83f, 83.9542f)
            curveTo(167.99f, 83.709f, 168.202f, 83.502f, 168.451f, 83.3482f)
            curveTo(168.7f, 83.1943f, 168.98f, 83.0973f, 169.27f, 83.064f)
            curveTo(169.561f, 83.0308f, 169.856f, 83.0622f, 170.133f, 83.1559f)
            curveTo(170.41f, 83.2497f, 170.663f, 83.4035f, 170.874f, 83.6064f)
            lineTo(195.646f, 107.446f)
            verticalLineTo(134.628f)
            curveTo(195.646f, 135.221f, 195.49f, 135.802f, 195.195f, 136.316f)
            curveTo(194.899f, 136.829f, 194.475f, 137.256f, 193.963f, 137.554f)
            lineTo(83.7319f, 201.185f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFE6E6E6)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(151.469f, 109.142f)
            lineTo(160.308f, 116.795f)
            lineTo(152.575f, 107.446f)
            lineTo(151.469f, 109.142f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF0F0F0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(201.522f, 20.5775f)
            curveTo(201.516f, 20.5122f, 201.504f, 20.4477f, 201.485f, 20.385f)
            curveTo(201.398f, 19.9245f, 201.219f, 19.4862f, 200.958f, 19.0966f)
            curveTo(200.698f, 18.707f, 200.361f, 18.3741f, 199.969f, 18.1177f)
            lineTo(197.217f, 16.5276f)
            curveTo(196.731f, 16.2794f, 196.192f, 16.1501f, 195.646f, 16.1501f)
            curveTo(195.1f, 16.1501f, 194.561f, 16.2794f, 194.074f, 16.5276f)
            lineTo(73.5264f, 86.1283f)
            curveTo(73.0628f, 86.4217f, 72.6788f, 86.8249f, 72.4083f, 87.3022f)
            curveTo(72.1291f, 87.7713f, 71.9731f, 88.3034f, 71.9548f, 88.8489f)
            verticalLineTo(207.651f)
            curveTo(71.9845f, 208.197f, 72.1422f, 208.727f, 72.4153f, 209.2f)
            curveTo(72.6883f, 209.672f, 73.069f, 210.074f, 73.5264f, 210.372f)
            lineTo(76.2719f, 211.962f)
            curveTo(76.6991f, 212.177f, 77.1659f, 212.303f, 77.6435f, 212.33f)
            curveTo(78.121f, 212.358f, 78.5991f, 212.288f, 79.0484f, 212.124f)
            lineTo(79.1603f, 212.08f)
            curveTo(79.2491f, 212.05f, 79.3345f, 212.011f, 79.4149f, 211.962f)
            lineTo(114.293f, 191.824f)
            lineTo(199.969f, 142.362f)
            curveTo(200.425f, 142.062f, 200.805f, 141.661f, 201.078f, 141.188f)
            curveTo(201.351f, 140.716f, 201.51f, 140.186f, 201.541f, 139.641f)
            verticalLineTo(20.8322f)
            curveTo(201.565f, 20.7452f, 201.528f, 20.6583f, 201.522f, 20.5775f)
            close()
            moveTo(197.727f, 137.554f)
            curveTo(197.697f, 138.1f, 197.538f, 138.631f, 197.264f, 139.103f)
            curveTo(196.99f, 139.576f, 196.608f, 139.978f, 196.149f, 140.275f)
            lineTo(113.361f, 187.83f)
            lineTo(82.3592f, 205.987f)
            curveTo(81.4958f, 206.484f, 80.7877f, 206.08f, 80.7877f, 205.08f)
            verticalLineTo(93.9486f)
            curveTo(80.8062f, 93.4042f, 80.9645f, 92.8737f, 81.2474f, 92.4081f)
            curveTo(81.5048f, 91.9234f, 81.8891f, 91.5176f, 82.3592f, 91.2342f)
            lineTo(196.149f, 25.5219f)
            curveTo(197.025f, 25.0126f, 197.727f, 25.4163f, 197.727f, 26.4226f)
            verticalLineTo(137.554f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFE0E0E0)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(79.4149f, 211.962f)
            curveTo(79.3345f, 212.011f, 79.2491f, 212.05f, 79.1603f, 212.08f)
            lineTo(79.0484f, 212.124f)
            curveTo(78.5991f, 212.288f, 78.121f, 212.358f, 77.6435f, 212.33f)
            curveTo(77.1659f, 212.303f, 76.6991f, 212.177f, 76.2719f, 211.962f)
            lineTo(73.5264f, 210.372f)
            curveTo(73.069f, 210.074f, 72.6883f, 209.672f, 72.4153f, 209.2f)
            curveTo(72.1422f, 208.727f, 71.9845f, 208.197f, 71.9548f, 207.651f)
            verticalLineTo(88.8489f)
            curveTo(71.9731f, 88.3034f, 72.1291f, 87.7713f, 72.4083f, 87.3022f)
            lineTo(78.2969f, 90.7f)
            curveTo(79.2534f, 91.2528f, 80.2411f, 91.8181f, 81.2474f, 92.4082f)
            curveTo(80.9645f, 92.8737f, 80.8062f, 93.4042f, 80.7877f, 93.9486f)
            verticalLineTo(205.08f)
            curveTo(80.7877f, 206.08f, 81.4958f, 206.484f, 82.3592f, 205.987f)
            lineTo(79.4149f, 211.962f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(201.522f, 20.5775f)
            curveTo(201.516f, 20.5122f, 201.504f, 20.4477f, 201.485f, 20.385f)
            curveTo(201.298f, 19.7265f, 200.69f, 19.5091f, 199.969f, 19.9253f)
            lineTo(79.4149f, 89.5384f)
            curveTo(78.9537f, 89.8314f, 78.5696f, 90.2309f, 78.2951f, 90.7033f)
            curveTo(78.0205f, 91.1756f, 77.8634f, 91.7071f, 77.8372f, 92.2529f)
            verticalLineTo(211.055f)
            curveTo(77.8372f, 211.906f, 78.3527f, 212.329f, 79.0422f, 212.124f)
            lineTo(79.154f, 212.08f)
            curveTo(79.2429f, 212.05f, 79.3282f, 212.011f, 79.4087f, 211.962f)
            lineTo(199.963f, 142.393f)
            curveTo(200.419f, 142.094f, 200.799f, 141.692f, 201.072f, 141.219f)
            curveTo(201.345f, 140.747f, 201.503f, 140.217f, 201.534f, 139.672f)
            verticalLineTo(20.8322f)
            curveTo(201.565f, 20.7452f, 201.528f, 20.6583f, 201.522f, 20.5775f)
            close()
            moveTo(197.727f, 137.554f)
            curveTo(197.697f, 138.1f, 197.538f, 138.631f, 197.264f, 139.103f)
            curveTo(196.99f, 139.576f, 196.608f, 139.978f, 196.149f, 140.275f)
            lineTo(82.3592f, 205.987f)
            curveTo(81.4958f, 206.484f, 80.7877f, 206.08f, 80.7877f, 205.08f)
            verticalLineTo(93.9486f)
            curveTo(80.8061f, 93.4042f, 80.9644f, 92.8737f, 81.2473f, 92.4082f)
            curveTo(81.5048f, 91.9234f, 81.8891f, 91.5176f, 82.3592f, 91.2342f)
            lineTo(196.149f, 25.5219f)
            curveTo(197.025f, 25.0126f, 197.727f, 25.4163f, 197.727f, 26.4226f)
            verticalLineTo(137.554f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFE6E6E6)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(180.49f, 55.9586f)
            lineTo(183.465f, 50.9024f)
            lineTo(178.372f, 47.9333f)
            curveTo(176.875f, 47.0761f, 174.812f, 47.2003f, 172.539f, 48.5172f)
            curveTo(167.98f, 51.1509f, 164.277f, 57.555f, 164.277f, 62.8038f)
            curveTo(164.277f, 65.4002f, 165.184f, 67.2326f, 166.638f, 68.1085f)
            horizontalLineTo(166.681f)
            horizontalLineTo(166.712f)
            lineTo(171.62f, 70.9596f)
            lineTo(174.464f, 66.1394f)
            curveTo(177.488f, 63.4597f, 179.596f, 59.899f, 180.49f, 55.9586f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFEBEBEB)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(183.37f, 64.3273f)
            curveTo(186.595f, 58.742f, 186.596f, 52.7054f, 183.372f, 50.8443f)
            curveTo(180.149f, 48.9833f, 174.922f, 52.0024f, 171.697f, 57.5878f)
            curveTo(168.472f, 63.1732f, 168.471f, 69.2097f, 171.695f, 71.0708f)
            curveTo(174.918f, 72.9319f, 180.145f, 69.9127f, 183.37f, 64.3273f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(207.616f, 31.1186f)
            verticalLineTo(144.194f)
            lineTo(92.745f, 210.509f)
            lineTo(95.3414f, 215.813f)
            lineTo(207.616f, 150.996f)
            lineTo(212.635f, 151.462f)
            verticalLineTo(31.6031f)
            lineTo(207.616f, 31.1186f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(184.527f, 93.3647f)
            lineTo(185.465f, 92.0541f)
            lineTo(179.533f, 88.6253f)
            horizontalLineTo(179.49f)
            curveTo(179.053f, 88.3738f, 178.537f, 88.2981f, 178.047f, 88.4135f)
            curveTo(177.556f, 88.5289f, 177.128f, 88.8267f, 176.85f, 89.2465f)
            lineTo(160.488f, 114.434f)
            lineTo(143.537f, 99.7564f)
            lineTo(144.239f, 98.7005f)
            lineTo(138.363f, 95.309f)
            curveTo(137.923f, 95.0446f, 137.398f, 94.9617f, 136.898f, 95.0776f)
            curveTo(136.399f, 95.1935f, 135.964f, 95.4993f, 135.686f, 95.9301f)
            lineTo(92.7512f, 160.35f)
            verticalLineTo(206.484f)
            lineTo(98.6398f, 209.881f)
            lineTo(204.671f, 145.281f)
            verticalLineTo(112.739f)
            lineTo(184.527f, 93.3647f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.15f,
            stroke = null,
            strokeAlpha = 0.15f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(184.527f, 93.3647f)
            lineTo(185.465f, 92.0541f)
            lineTo(179.533f, 88.6253f)
            horizontalLineTo(179.49f)
            curveTo(179.053f, 88.3738f, 178.537f, 88.2981f, 178.047f, 88.4135f)
            curveTo(177.556f, 88.5289f, 177.128f, 88.8267f, 176.85f, 89.2465f)
            lineTo(160.488f, 114.434f)
            lineTo(143.537f, 99.7564f)
            lineTo(144.239f, 98.7005f)
            lineTo(138.363f, 95.309f)
            curveTo(137.923f, 95.0446f, 137.398f, 94.9617f, 136.898f, 95.0776f)
            curveTo(136.399f, 95.1935f, 135.964f, 95.4993f, 135.686f, 95.9301f)
            lineTo(92.7512f, 160.35f)
            verticalLineTo(206.484f)
            lineTo(98.6398f, 209.881f)
            lineTo(204.671f, 145.281f)
            verticalLineTo(112.739f)
            lineTo(184.527f, 93.3647f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(98.6398f, 209.881f)
            verticalLineTo(163.754f)
            lineTo(141.568f, 99.3154f)
            curveTo(141.723f, 99.0804f, 141.927f, 98.8812f, 142.166f, 98.7312f)
            curveTo(142.405f, 98.5812f, 142.673f, 98.4839f, 142.952f, 98.4457f)
            curveTo(143.231f, 98.4074f, 143.515f, 98.4292f, 143.785f, 98.5096f)
            curveTo(144.056f, 98.59f, 144.305f, 98.727f, 144.518f, 98.9117f)
            lineTo(166.377f, 117.838f)
            lineTo(182.738f, 92.6504f)
            curveTo(182.898f, 92.4052f, 183.11f, 92.1982f, 183.359f, 92.0444f)
            curveTo(183.608f, 91.8905f, 183.887f, 91.7935f, 184.178f, 91.7602f)
            curveTo(184.469f, 91.727f, 184.763f, 91.7584f, 185.041f, 91.8521f)
            curveTo(185.318f, 91.9459f, 185.571f, 92.0997f, 185.782f, 92.3026f)
            lineTo(210.554f, 116.143f)
            verticalLineTo(143.324f)
            curveTo(210.554f, 143.917f, 210.398f, 144.499f, 210.103f, 145.012f)
            curveTo(209.807f, 145.525f, 209.382f, 145.952f, 208.87f, 146.25f)
            lineTo(98.6398f, 209.881f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.15f,
            stroke = null,
            strokeAlpha = 0.15f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(166.377f, 117.838f)
            lineTo(175.216f, 125.491f)
            lineTo(167.483f, 116.143f)
            lineTo(166.377f, 117.838f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF455A64)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(216.43f, 29.2737f)
            curveTo(216.424f, 29.2084f, 216.412f, 29.1439f, 216.393f, 29.0812f)
            curveTo(216.306f, 28.6207f, 216.127f, 28.1824f, 215.866f, 27.7928f)
            curveTo(215.606f, 27.4032f, 215.269f, 27.0703f, 214.877f, 26.8139f)
            lineTo(212.125f, 25.2238f)
            curveTo(211.639f, 24.9756f, 211.1f, 24.8463f, 210.554f, 24.8463f)
            curveTo(210.007f, 24.8463f, 209.469f, 24.9756f, 208.982f, 25.2238f)
            lineTo(88.4342f, 94.8244f)
            curveTo(87.9706f, 95.1179f, 87.5866f, 95.5211f, 87.3161f, 95.9984f)
            curveTo(87.037f, 96.4675f, 86.881f, 96.9996f, 86.8627f, 97.5451f)
            verticalLineTo(216.348f)
            curveTo(86.8923f, 216.893f, 87.0501f, 217.423f, 87.3231f, 217.896f)
            curveTo(87.5962f, 218.369f, 87.9768f, 218.77f, 88.4342f, 219.068f)
            lineTo(91.1797f, 220.658f)
            curveTo(91.607f, 220.874f, 92.0738f, 220.999f, 92.5513f, 221.027f)
            curveTo(93.0288f, 221.054f, 93.507f, 220.984f, 93.9563f, 220.82f)
            lineTo(94.0681f, 220.776f)
            curveTo(94.157f, 220.746f, 94.2423f, 220.707f, 94.3228f, 220.658f)
            lineTo(129.201f, 200.521f)
            lineTo(214.877f, 151.058f)
            curveTo(215.333f, 150.759f, 215.713f, 150.357f, 215.986f, 149.884f)
            curveTo(216.259f, 149.412f, 216.417f, 148.882f, 216.448f, 148.337f)
            verticalLineTo(29.5284f)
            curveTo(216.473f, 29.4414f, 216.436f, 29.3545f, 216.43f, 29.2737f)
            close()
            moveTo(212.635f, 146.25f)
            curveTo(212.605f, 146.796f, 212.446f, 147.327f, 212.172f, 147.8f)
            curveTo(211.898f, 148.272f, 211.516f, 148.674f, 211.057f, 148.971f)
            lineTo(128.269f, 196.527f)
            lineTo(97.2671f, 214.683f)
            curveTo(96.4036f, 215.18f, 95.6955f, 214.776f, 95.6955f, 213.776f)
            verticalLineTo(102.645f)
            curveTo(95.714f, 102.1f, 95.8723f, 101.57f, 96.1552f, 101.104f)
            curveTo(96.4127f, 100.62f, 96.797f, 100.214f, 97.2671f, 99.9304f)
            lineTo(211.057f, 34.2181f)
            curveTo(211.933f, 33.7088f, 212.635f, 34.1125f, 212.635f, 35.1188f)
            verticalLineTo(146.25f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(94.3228f, 220.658f)
            curveTo(94.2423f, 220.707f, 94.157f, 220.746f, 94.0681f, 220.776f)
            lineTo(93.9563f, 220.82f)
            curveTo(93.507f, 220.984f, 93.0288f, 221.054f, 92.5513f, 221.027f)
            curveTo(92.0738f, 220.999f, 91.607f, 220.874f, 91.1797f, 220.658f)
            lineTo(88.4342f, 219.068f)
            curveTo(87.9768f, 218.77f, 87.5962f, 218.369f, 87.3231f, 217.896f)
            curveTo(87.0501f, 217.423f, 86.8923f, 216.893f, 86.8627f, 216.348f)
            verticalLineTo(97.5451f)
            curveTo(86.881f, 96.9996f, 87.037f, 96.4675f, 87.3161f, 95.9984f)
            lineTo(93.2047f, 99.3962f)
            curveTo(94.1613f, 99.949f, 95.1489f, 100.514f, 96.1552f, 101.104f)
            curveTo(95.8723f, 101.57f, 95.714f, 102.1f, 95.6955f, 102.645f)
            verticalLineTo(213.776f)
            curveTo(95.6955f, 214.776f, 96.4036f, 215.18f, 97.2671f, 214.683f)
            lineTo(94.3228f, 220.658f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF37474F)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(216.43f, 29.2737f)
            curveTo(216.424f, 29.2084f, 216.411f, 29.1439f, 216.393f, 29.0812f)
            curveTo(216.206f, 28.4227f, 215.597f, 28.2053f, 214.877f, 28.6215f)
            lineTo(94.3227f, 98.2346f)
            curveTo(93.8615f, 98.5276f, 93.4775f, 98.9271f, 93.2029f, 99.3995f)
            curveTo(92.9283f, 99.8718f, 92.7713f, 100.403f, 92.745f, 100.949f)
            verticalLineTo(219.752f)
            curveTo(92.745f, 220.603f, 93.2606f, 221.025f, 93.95f, 220.82f)
            lineTo(94.0618f, 220.777f)
            curveTo(94.1507f, 220.746f, 94.2361f, 220.707f, 94.3165f, 220.658f)
            lineTo(214.871f, 151.089f)
            curveTo(215.327f, 150.79f, 215.707f, 150.388f, 215.98f, 149.915f)
            curveTo(216.253f, 149.443f, 216.411f, 148.913f, 216.442f, 148.368f)
            verticalLineTo(29.5284f)
            curveTo(216.473f, 29.4414f, 216.436f, 29.3545f, 216.43f, 29.2737f)
            close()
            moveTo(212.635f, 146.25f)
            curveTo(212.605f, 146.796f, 212.446f, 147.327f, 212.172f, 147.8f)
            curveTo(211.898f, 148.272f, 211.516f, 148.674f, 211.057f, 148.971f)
            lineTo(97.267f, 214.683f)
            curveTo(96.4036f, 215.18f, 95.6955f, 214.776f, 95.6955f, 213.776f)
            verticalLineTo(102.645f)
            curveTo(95.714f, 102.1f, 95.8723f, 101.57f, 96.1552f, 101.104f)
            curveTo(96.4126f, 100.62f, 96.7969f, 100.214f, 97.267f, 99.9304f)
            lineTo(211.057f, 34.2181f)
            curveTo(211.933f, 33.7088f, 212.635f, 34.1126f, 212.635f, 35.1188f)
            verticalLineTo(146.25f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(195.398f, 64.6548f)
            lineTo(198.373f, 59.5986f)
            lineTo(193.279f, 56.6295f)
            curveTo(191.782f, 55.7723f, 189.72f, 55.8965f, 187.447f, 57.2134f)
            curveTo(182.887f, 59.8471f, 179.185f, 66.2512f, 179.185f, 71.5f)
            curveTo(179.185f, 74.0964f, 180.092f, 75.9288f, 181.546f, 76.8047f)
            horizontalLineTo(181.589f)
            horizontalLineTo(181.62f)
            lineTo(186.527f, 79.6558f)
            lineTo(189.372f, 74.8356f)
            curveTo(192.396f, 72.1559f, 194.504f, 68.5952f, 195.398f, 64.6548f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.15f,
            stroke = null,
            strokeAlpha = 0.15f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(195.398f, 64.6548f)
            lineTo(198.373f, 59.5986f)
            lineTo(193.279f, 56.6295f)
            curveTo(191.782f, 55.7723f, 189.72f, 55.8965f, 187.447f, 57.2134f)
            curveTo(182.887f, 59.8471f, 179.185f, 66.2512f, 179.185f, 71.5f)
            curveTo(179.185f, 74.0964f, 180.092f, 75.9288f, 181.546f, 76.8047f)
            horizontalLineTo(181.589f)
            horizontalLineTo(181.62f)
            lineTo(186.527f, 79.6558f)
            lineTo(189.372f, 74.8356f)
            curveTo(192.396f, 72.1559f, 194.504f, 68.5952f, 195.398f, 64.6548f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(198.274f, 73.0207f)
            curveTo(201.499f, 67.4353f, 201.5f, 61.3988f, 198.277f, 59.5377f)
            curveTo(195.053f, 57.6766f, 189.826f, 60.6957f, 186.601f, 66.2811f)
            curveTo(183.376f, 71.8665f, 183.375f, 77.9031f, 186.599f, 79.7641f)
            curveTo(189.822f, 81.6252f, 195.05f, 78.6061f, 198.274f, 73.0207f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFA8A7)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(221.921f, 82.7367f)
            lineTo(221.368f, 84.4822f)
            lineTo(216.473f, 73.7175f)
            verticalLineTo(87.8364f)
            curveTo(216.473f, 87.8364f, 219.989f, 94.8866f, 221.716f, 94.8866f)
            curveTo(222.107f, 94.8866f, 223.747f, 95.7624f, 227.418f, 85.7618f)
            lineTo(221.921f, 82.7367f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(228.424f, 85.4636f)
            curveTo(225.915f, 85.5638f, 223.44f, 84.8514f, 221.368f, 83.4324f)
            curveTo(222.25f, 79.6386f, 223.37f, 75.9043f, 224.722f, 72.2516f)
            curveTo(226.952f, 66.5307f, 229.474f, 65.6549f, 233.418f, 65.7232f)
            lineTo(228.424f, 85.4636f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.1f,
            stroke = null,
            strokeAlpha = 0.1f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(228.66f, 85.4325f)
            curveTo(224.778f, 85.7431f, 221.368f, 83.4324f, 221.368f, 83.4324f)
            curveTo(222.25f, 79.6386f, 223.37f, 75.9043f, 224.722f, 72.2516f)
            curveTo(226.952f, 66.5307f, 229.474f, 65.6549f, 233.418f, 65.7232f)
            lineTo(228.66f, 85.4325f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFA8A7)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(251.165f, 179.606f)
            horizontalLineTo(245.717f)
            verticalLineTo(188.936f)
            horizontalLineTo(251.165f)
            verticalLineTo(179.606f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFA8A7)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(232.276f, 179.109f)
            lineTo(226.828f, 179.296f)
            lineTo(226.555f, 169.425f)
            lineTo(232.276f, 169.239f)
            verticalLineTo(179.109f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF37474F)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(251.153f, 107.086f)
            curveTo(251.153f, 112.291f, 250.233f, 142.567f, 250.233f, 142.567f)
            curveTo(250.364f, 144.064f, 251.805f, 146.728f, 251.917f, 152.12f)
            curveTo(252.078f, 159.947f, 251.401f, 184.277f, 251.401f, 184.277f)
            curveTo(250.459f, 184.738f, 249.428f, 184.987f, 248.38f, 185.008f)
            curveTo(247.331f, 185.028f, 246.291f, 184.819f, 245.332f, 184.395f)
            curveTo(245.332f, 184.395f, 240.525f, 152.431f, 239.549f, 144.685f)
            curveTo(238.698f, 137.927f, 237.251f, 119.565f, 237.251f, 119.565f)
            lineTo(234.294f, 141.629f)
            curveTo(234.926f, 144.471f, 235.154f, 147.388f, 234.971f, 150.294f)
            curveTo(234.717f, 153.872f, 232.617f, 176.053f, 232.617f, 176.053f)
            curveTo(231.667f, 176.588f, 230.611f, 176.908f, 229.524f, 176.991f)
            curveTo(228.437f, 177.074f, 227.345f, 176.917f, 226.325f, 176.531f)
            curveTo(226.325f, 176.531f, 223.567f, 144.915f, 223.505f, 141.629f)
            curveTo(223.437f, 137.902f, 225.319f, 99.6384f, 225.319f, 99.6384f)
            lineTo(251.153f, 107.086f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(248.91f, 69.3943f)
            curveTo(248.91f, 69.3943f, 250.997f, 71.295f, 250.997f, 76.227f)
            verticalLineTo(86.9047f)
            lineTo(251.153f, 107.067f)
            curveTo(243.332f, 111.192f, 233.387f, 111.471f, 225.064f, 105.372f)
            curveTo(225.064f, 105.372f, 225.53f, 79.4197f, 225.53f, 75.171f)
            curveTo(225.53f, 74.9971f, 225.53f, 74.817f, 225.53f, 74.6368f)
            curveTo(225.809f, 68.5495f, 231.779f, 64.3567f, 237.673f, 65.8723f)
            lineTo(242.643f, 67.1457f)
            lineTo(247.612f, 68.7793f)
            lineTo(248.91f, 69.3943f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(233.58f, 56.4245f)
            curveTo(237.717f, 56.4245f, 241.071f, 53.0706f, 241.071f, 48.9334f)
            curveTo(241.071f, 44.7961f, 237.717f, 41.4422f, 233.58f, 41.4422f)
            curveTo(229.443f, 41.4422f, 226.089f, 44.7961f, 226.089f, 48.9334f)
            curveTo(226.089f, 53.0706f, 229.443f, 56.4245f, 233.58f, 56.4245f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(246.525f, 54.2629f)
            curveTo(246.525f, 54.2629f, 245.314f, 59.9651f, 244.829f, 60.9031f)
            curveTo(244.501f, 61.4787f, 243.966f, 61.9074f, 243.332f, 62.1019f)
            lineTo(243.419f, 58.0892f)
            lineTo(246.525f, 54.2629f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(245.003f, 46.0885f)
            curveTo(245.428f, 46.039f, 245.859f, 46.0872f, 246.262f, 46.2292f)
            curveTo(246.666f, 46.3713f, 247.032f, 46.6036f, 247.332f, 46.9084f)
            curveTo(248.326f, 47.9333f, 247.773f, 51.126f, 246.525f, 54.2629f)
            lineTo(244.239f, 54.5238f)
            lineTo(245.003f, 46.0885f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFA8A7)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(243.556f, 54.5238f)
            curveTo(244.015f, 54.7971f, 244.668f, 53.9026f, 245.233f, 53.3249f)
            curveTo(245.798f, 52.7473f, 247.643f, 51.9273f, 248.562f, 53.8653f)
            curveTo(249.482f, 55.8034f, 247.755f, 58.4557f, 246.301f, 58.9154f)
            curveTo(243.817f, 59.698f, 243.419f, 58.0892f, 243.419f, 58.0892f)
            lineTo(243.233f, 67.4066f)
            curveTo(240.127f, 71.4006f, 231.48f, 71.0652f, 235.083f, 66.891f)
            verticalLineTo(64.1455f)
            curveTo(233.966f, 64.3223f, 232.833f, 64.3806f, 231.704f, 64.3194f)
            curveTo(229.841f, 64.0337f, 228.704f, 62.5802f, 228.145f, 60.5925f)
            curveTo(227.25f, 57.3873f, 226.903f, 54.8033f, 227.667f, 48.5048f)
            curveTo(228.505f, 41.6037f, 236.53f, 41.5354f, 240.866f, 44.2623f)
            curveTo(245.202f, 46.9891f, 243.556f, 54.5238f, 243.556f, 54.5238f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(243.556f, 55.294f)
            curveTo(243.786f, 55.294f, 244.661f, 53.8467f, 245.233f, 53.3249f)
            curveTo(246.022f, 52.6044f, 245.003f, 46.0885f, 245.003f, 46.0885f)
            curveTo(245.207f, 45.5245f, 245.244f, 44.9136f, 245.108f, 44.3294f)
            curveTo(244.972f, 43.7452f, 244.671f, 43.2127f, 244.239f, 42.7963f)
            curveTo(243.158f, 41.6037f, 241.344f, 41.4733f, 238.257f, 40.9825f)
            curveTo(236.636f, 40.7279f, 234.176f, 40.2744f, 232.555f, 39.9763f)
            curveTo(230.623f, 39.616f, 229.021f, 39.2371f, 227.654f, 40.4235f)
            curveTo(227.118f, 40.8866f, 226.74f, 41.5047f, 226.57f, 42.1925f)
            curveTo(226.401f, 42.8802f, 226.45f, 43.6035f, 226.71f, 44.2623f)
            curveTo(226.844f, 44.5646f, 227.029f, 44.8419f, 227.257f, 45.0822f)
            curveTo(227.625f, 45.5208f, 228.106f, 45.8514f, 228.648f, 46.0388f)
            curveTo(228.648f, 46.0388f, 229.493f, 46.7904f, 232.425f, 47.1444f)
            curveTo(235.134f, 47.3477f, 237.856f, 47.2978f, 240.556f, 46.9953f)
            curveTo(241.4f, 46.8773f, 241.587f, 47.7035f, 242.046f, 49.9521f)
            curveTo(242.444f, 51.9273f, 242.779f, 55.2816f, 243.556f, 55.294f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(244.997f, 46.5978f)
            lineTo(247.773f, 45.107f)
            curveTo(247.685f, 44.9206f, 247.559f, 44.7542f, 247.404f, 44.6178f)
            curveTo(247.248f, 44.4813f, 247.067f, 44.3778f, 246.871f, 44.3133f)
            curveTo(246.675f, 44.2489f, 246.468f, 44.2249f, 246.262f, 44.2428f)
            curveTo(246.056f, 44.2607f, 245.856f, 44.3202f, 245.674f, 44.4175f)
            curveTo(245.302f, 44.624f, 245.024f, 44.9659f, 244.898f, 45.3719f)
            curveTo(244.772f, 45.778f, 244.808f, 46.2171f, 244.997f, 46.5978f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF28F8F)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(235.108f, 64.1393f)
            curveTo(235.108f, 64.1393f, 239.214f, 63.3069f, 240.655f, 62.5429f)
            curveTo(241.499f, 62.1026f, 242.192f, 61.4187f, 242.643f, 60.5801f)
            curveTo(242.423f, 61.4197f, 242.039f, 62.2072f, 241.512f, 62.897f)
            curveTo(240.462f, 64.2387f, 235.108f, 65.2325f, 235.108f, 65.2325f)
            verticalLineTo(64.1393f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(236.077f, 53.4989f)
            curveTo(236.083f, 53.6611f, 236.137f, 53.8179f, 236.232f, 53.9497f)
            curveTo(236.326f, 54.0815f, 236.458f, 54.1826f, 236.609f, 54.2402f)
            curveTo(236.761f, 54.2978f, 236.927f, 54.3094f, 237.085f, 54.2736f)
            curveTo(237.243f, 54.2378f, 237.387f, 54.1561f, 237.5f, 54.0388f)
            curveTo(237.612f, 53.9215f, 237.687f, 53.7738f, 237.716f, 53.6141f)
            curveTo(237.744f, 53.4543f, 237.725f, 53.2896f, 237.661f, 53.1406f)
            curveTo(237.597f, 52.9916f, 237.49f, 52.8647f, 237.354f, 52.776f)
            curveTo(237.218f, 52.6873f, 237.059f, 52.6406f, 236.897f, 52.6417f)
            curveTo(236.787f, 52.6433f, 236.678f, 52.6668f, 236.576f, 52.7109f)
            curveTo(236.475f, 52.755f, 236.384f, 52.8188f, 236.308f, 52.8986f)
            curveTo(236.231f, 52.9784f, 236.172f, 53.0725f, 236.132f, 53.1756f)
            curveTo(236.092f, 53.2786f, 236.074f, 53.3885f, 236.077f, 53.4989f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(236.972f, 50.3869f)
            lineTo(238.736f, 51.3621f)
            curveTo(238.862f, 51.1204f, 238.889f, 50.8394f, 238.813f, 50.5777f)
            curveTo(238.737f, 50.3161f, 238.562f, 50.0941f, 238.326f, 49.9583f)
            curveTo(238.209f, 49.8943f, 238.081f, 49.8546f, 237.949f, 49.8417f)
            curveTo(237.817f, 49.8287f, 237.683f, 49.8426f, 237.557f, 49.8827f)
            curveTo(237.43f, 49.9228f, 237.313f, 49.9881f, 237.213f, 50.0748f)
            curveTo(237.112f, 50.1614f, 237.03f, 50.2676f, 236.972f, 50.3869f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF28F8F)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(235.462f, 59.1887f)
            lineTo(233.599f, 59.8968f)
            curveTo(233.682f, 60.143f, 233.86f, 60.3463f, 234.092f, 60.4626f)
            curveTo(234.325f, 60.5789f, 234.594f, 60.5988f, 234.841f, 60.5179f)
            curveTo(235.097f, 60.4201f, 235.304f, 60.2268f, 235.42f, 59.9788f)
            curveTo(235.536f, 59.7308f, 235.551f, 59.4475f, 235.462f, 59.1887f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(228.729f, 51.0701f)
            lineTo(230.338f, 49.8278f)
            curveTo(230.262f, 49.7195f, 230.165f, 49.6277f, 230.052f, 49.558f)
            curveTo(229.94f, 49.4883f, 229.815f, 49.4422f, 229.684f, 49.4224f)
            curveTo(229.553f, 49.4027f, 229.42f, 49.4097f, 229.292f, 49.4431f)
            curveTo(229.164f, 49.4765f, 229.044f, 49.5355f, 228.94f, 49.6166f)
            curveTo(228.723f, 49.7841f, 228.58f, 50.0294f, 228.54f, 50.3007f)
            curveTo(228.501f, 50.5719f, 228.568f, 50.8478f, 228.729f, 51.0701f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(229.226f, 53.2628f)
            curveTo(229.223f, 53.3726f, 229.243f, 53.4819f, 229.282f, 53.5842f)
            curveTo(229.322f, 53.6865f, 229.382f, 53.78f, 229.458f, 53.8591f)
            curveTo(229.535f, 53.9382f, 229.626f, 54.0014f, 229.727f, 54.0451f)
            curveTo(229.827f, 54.0889f, 229.936f, 54.1122f, 230.046f, 54.1138f)
            curveTo(230.217f, 54.1213f, 230.386f, 54.0772f, 230.532f, 53.9874f)
            curveTo(230.678f, 53.8976f, 230.794f, 53.7661f, 230.864f, 53.6099f)
            curveTo(230.935f, 53.4537f, 230.957f, 53.2799f, 230.927f, 53.1111f)
            curveTo(230.898f, 52.9423f, 230.819f, 52.7861f, 230.7f, 52.6627f)
            curveTo(230.581f, 52.5393f, 230.428f, 52.4543f, 230.26f, 52.4188f)
            curveTo(230.093f, 52.3833f, 229.918f, 52.3989f, 229.76f, 52.4635f)
            curveTo(229.601f, 52.5281f, 229.465f, 52.6388f, 229.37f, 52.7812f)
            curveTo(229.275f, 52.9237f, 229.225f, 53.0915f, 229.226f, 53.2628f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFF28F8F)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(233.418f, 52.4802f)
            lineTo(233.151f, 57.437f)
            lineTo(230.561f, 56.555f)
            lineTo(233.418f, 52.4802f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(245.717f, 186.277f)
            verticalLineTo(185.47f)
            curveTo(245.196f, 185.7f, 243.984f, 190.321f, 241.823f, 192.303f)
            curveTo(240.102f, 193.874f, 237.76f, 195.682f, 237.164f, 197.433f)
            curveTo(236.568f, 199.185f, 240.431f, 200.253f, 242.034f, 199.968f)
            curveTo(243.897f, 199.639f, 247.134f, 198.328f, 247.947f, 197.11f)
            curveTo(248.761f, 195.893f, 249.14f, 194.092f, 249.811f, 193.197f)
            curveTo(250.482f, 192.303f, 251.991f, 191.334f, 252.351f, 190.278f)
            curveTo(252.544f, 189.706f, 252.314f, 188.464f, 252.041f, 187.414f)
            curveTo(251.767f, 186.364f, 251.513f, 185.389f, 251.184f, 185.482f)
            verticalLineTo(186.103f)
            curveTo(250.405f, 186.711f, 249.444f, 187.035f, 248.457f, 187.023f)
            curveTo(247.649f, 187.017f, 245.711f, 186.855f, 245.717f, 186.277f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(226.766f, 177.618f)
            curveTo(226.486f, 177.618f, 226.256f, 178.028f, 225.629f, 178.706f)
            curveTo(224.696f, 179.647f, 223.613f, 180.426f, 222.424f, 181.01f)
            curveTo(220.38f, 182.029f, 216.417f, 183.6f, 214.616f, 184.414f)
            curveTo(213.498f, 184.917f, 213.548f, 186.277f, 214.709f, 187.041f)
            curveTo(215.871f, 187.805f, 218.666f, 188.371f, 222.033f, 187.619f)
            curveTo(223.865f, 187.209f, 226.244f, 185.52f, 227.753f, 185.607f)
            curveTo(229.263f, 185.694f, 232.102f, 185.681f, 233.201f, 184.911f)
            curveTo(234.3f, 184.141f, 233.872f, 182.501f, 233.555f, 181.097f)
            curveTo(233.213f, 179.556f, 232.785f, 177.159f, 232.276f, 177.264f)
            verticalLineTo(177.792f)
            curveTo(231.617f, 178.786f, 228.238f, 179.19f, 226.797f, 178.457f)
            lineTo(226.766f, 177.618f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF263238)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(237.251f, 119.565f)
            curveTo(234.433f, 118.688f, 231.842f, 117.204f, 229.661f, 115.217f)
            curveTo(229.661f, 115.217f, 231.126f, 117.956f, 235.481f, 120.509f)
            lineTo(234.549f, 139.765f)
            lineTo(237.251f, 119.565f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFFFA8A7)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(241.419f, 102.732f)
            curveTo(243.904f, 101.446f, 244.767f, 100.993f, 245.891f, 97.7625f)
            curveTo(253.966f, 74.5064f, 251.302f, 71.4627f, 247.276f, 70.0154f)
            lineTo(244.239f, 76.1835f)
            lineTo(238.835f, 95.4767f)
            curveTo(235.251f, 98.5328f, 227.803f, 102.092f, 223.716f, 102.986f)
            curveTo(222.759f, 103.086f, 221.792f, 102.984f, 220.877f, 102.688f)
            curveTo(219.107f, 102.067f, 218.206f, 102.179f, 216.43f, 101.707f)
            verticalLineTo(103.775f)
            curveTo(214.824f, 103.714f, 213.225f, 103.531f, 211.647f, 103.229f)
            curveTo(207.97f, 102.545f, 206.678f, 103.769f, 209.672f, 107.459f)
            curveTo(211.38f, 109.552f, 214.728f, 110.36f, 219.61f, 109.838f)
            curveTo(222.13f, 109.434f, 224.605f, 108.789f, 227.002f, 107.912f)
            curveTo(233.3f, 105.788f, 238.145f, 104.421f, 241.419f, 102.732f)
            close()
        }
        path(
            fill = SolidColor(color),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(247.575f, 68.798f)
            curveTo(250.364f, 69.5558f, 252.258f, 71.9845f, 251.923f, 76.4382f)
            curveTo(251.637f, 80.4384f, 250.01f, 86.1407f, 249.134f, 90.1968f)
            curveTo(249.134f, 90.1968f, 243.966f, 90.2652f, 240.549f, 87.5631f)
            curveTo(241.4f, 83.9728f, 242.226f, 80.221f, 242.86f, 77.0034f)
            curveTo(244.084f, 72.6678f, 245.935f, 68.6675f, 247.575f, 68.798f)
            close()
        }
        group {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 0.05f,
                stroke = null,
                strokeAlpha = 0.05f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(247.575f, 68.798f)
                curveTo(250.364f, 69.5558f, 252.258f, 71.9845f, 251.923f, 76.4382f)
                curveTo(251.637f, 80.4384f, 250.01f, 86.1407f, 249.134f, 90.1968f)
                curveTo(249.134f, 90.1968f, 243.966f, 90.2652f, 240.549f, 87.5631f)
                curveTo(241.4f, 83.9728f, 242.226f, 80.221f, 242.86f, 77.0034f)
                curveTo(244.084f, 72.6678f, 245.935f, 68.6675f, 247.575f, 68.798f)
                close()
            }
        }
        path(
            fill = SolidColor(Color(0xFFF5F5F5)),
            fillAlpha = 1.0f,
            stroke = null,
            strokeAlpha = 1.0f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1.0f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(86.4526f, 9.13582f)
            lineTo(78.9988f, 13.428f)
            curveTo(79.0376f, 13.1002f, 79.0584f, 12.7705f, 79.0609f, 12.4404f)
            verticalLineTo(10.2912f)
            curveTo(79.0609f, 6.4649f, 76.3713f, 4.912f, 73.0543f, 6.8251f)
            curveTo(71.6287f, 7.7024f, 70.4085f, 8.876f, 69.4764f, 10.2663f)
            curveTo(68.936f, 5.4524f, 65.1283f, 3.6572f, 60.569f, 6.3095f)
            curveTo(55.5998f, 9.1731f, 51.5871f, 16.1363f, 51.5871f, 21.8385f)
            verticalLineTo(25.0623f)
            curveTo(51.5549f, 26.3459f, 51.814f, 27.6201f, 52.3449f, 28.7892f)
            lineTo(46.1768f, 32.3485f)
            curveTo(45.2075f, 32.9811f, 44.401f, 33.8333f, 43.8225f, 34.8359f)
            curveTo(43.2441f, 35.8385f, 42.91f, 36.9632f, 42.8474f, 38.119f)
            curveTo(42.8474f, 40.2434f, 44.3382f, 41.1068f, 46.1768f, 40.0446f)
            lineTo(86.4216f, 16.8071f)
            curveTo(87.3927f, 16.1759f, 88.201f, 15.3242f, 88.7806f, 14.3214f)
            curveTo(89.3603f, 13.3186f, 89.6949f, 12.1931f, 89.7572f, 11.0366f)
            curveTo(89.7883f, 8.937f, 88.2975f, 8.0736f, 86.4526f, 9.1358f)
            close()
        }
    }.build()
}
