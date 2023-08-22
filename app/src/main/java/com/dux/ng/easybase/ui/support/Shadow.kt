package com.dux.ng.easybase.ui.support

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadowCustom(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    shapeRadius: Dp = 0.dp,
) = composed {
    val paint: Paint = remember { Paint() }
    val blurRadiusPx = blurRadius.px(LocalDensity.current)
    val maskFilter = remember {
        BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
    }
    drawBehind {
        drawIntoCanvas { canvas ->
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = maskFilter
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + leftPixel
            val bottomPixel = size.height + topPixel

            val radiusPx = shapeRadius.toPx()
            canvas.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = radiusPx,
                radiusY = radiusPx,
                paint = paint,
            )
        }
    }
}

private fun Dp.px(density: Density): Float =
    with(density) { toPx() }

@Preview(
    widthDp = 160,
    showBackground = true,
    backgroundColor = 0xffffff,
)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier.padding(40.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .shadowCustom(
                    Color.Black,
                    offsetX = 8.dp,
                    offsetY = 8.dp,
                    blurRadius = 1.dp,
                    shapeRadius = 8.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 20.dp, spotColor = Color.Red, shape = RoundedCornerShape(8.dp)
                )
                .background(Color.Red)
                .size(100.dp), contentAlignment = Alignment.Center
        ) {
            Text("Hello World")
        }
    }
}
