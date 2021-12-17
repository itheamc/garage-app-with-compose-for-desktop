package ui.components.image

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*
import java.io.ByteArrayInputStream

@ExperimentalAnimationApi
@Composable
fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    imageTransformation: ImageTransformation = ImageTransformation.Rectangle
) {
    /**
     * Variable to store the state of the ImageBitmap
     */
    var img: ImageBitmap? by rememberSaveable {
        mutableStateOf(null)
    }

    val imageBitmap: ImageBitmap? by produceState(img) {
        if (value == null) {
            value = try {
                HttpClient(CIO).use {
                    ByteArrayInputStream(it.get(url))
                }.use(::loadImageBitmap).also { img = it }
            } catch (e: IOException) {
                println(e.message)
                null
            }
        }
    }

    AnimatedVisibility(
        visible = imageBitmap != null,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        val bitmapPainter = remember(imageBitmap) { BitmapPainter(imageBitmap!!, filterQuality = DefaultFilterQuality) }
        Image(
            painter = bitmapPainter,
            contentDescription = contentDescription,
            modifier = if (imageTransformation == ImageTransformation.Rectangle) modifier else modifier.clip(
                shape = if (imageTransformation == ImageTransformation.Circle) CircleShape else RoundedCornerShape(
                    imageTransformation.radius
                )
            ),
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    }
}


/**
 * Composable for dynamic imageview
 * You can use this if you want to change the image with state changes without changing the views
 */
@ExperimentalAnimationApi
@Composable
fun DynamicAsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    imageTransformation: ImageTransformation = ImageTransformation.Rectangle
) {
    /**
     * Variable to store the state of the ImageBitmap
     */
    var imageBitmap: ImageBitmap? by remember {
        mutableStateOf(null)
    }

    /**
     * For animation
     */
    var loaded by remember {
        mutableStateOf(false)
    }

    val imageAlpha by animateFloatAsState(
        targetValue = if (loaded) 1f else 0f,
        animationSpec = tween(500)
    )

    LaunchedEffect(url) {
        if (url.isNotBlank()) {
            loaded = false
            imageBitmap = try {
                HttpClient(CIO).use {
                    ByteArrayInputStream(it.get(url))
                }.use(::loadImageBitmap).also { loaded = true }
            } catch (e: IOException) {
                println(e.message)
                null
            }
        }
    }



    AnimatedVisibility(
        visible = imageBitmap != null,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        val bitmapPainter = remember(imageBitmap) { BitmapPainter(imageBitmap!!, filterQuality = DefaultFilterQuality) }
        Image(
            painter = bitmapPainter,
            contentDescription = contentDescription,
            modifier = if (imageTransformation == ImageTransformation.Rectangle) modifier else modifier
                .clip(
                    shape = if (imageTransformation == ImageTransformation.Circle) CircleShape else RoundedCornerShape(
                        imageTransformation.radius
                    )
                ).alpha(imageAlpha),
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    }
}


/**
 * Image Transformation
 */
enum class ImageTransformation(
    var radius: Dp = 0.dp
) {
    Rectangle,
    Circle,
    RoundedCorner(
        radius = 4.dp
    )
}