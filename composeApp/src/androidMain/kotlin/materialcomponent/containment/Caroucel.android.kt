package materialcomponent.containment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.max

@Composable
actual fun HorizontalMultiBrowseCarouselContent(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier =
            modifier
                .widthIn(min = 420.dp)
                .heightIn(min = 240.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        val item = items[it]
        Image(
            modifier = Modifier.height(205.dp),
//                .maskClip(MaterialTheme.shapes.extraLarge) TODO 対応したら入れる
            painter = painterResource(resource = item.imageResId),
            contentDescription = stringResource(item.contentDescriptionResId),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
actual fun FadingHorizontalMultiBrowseCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    val state = rememberCarouselState { items.count() }
    HorizontalMultiBrowseCarousel(
        state = state,
        modifier =
            Modifier
                .widthIn(412.dp)
                .height(221.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { i ->
        val item = items[i]
        // For item 1 and 4, create a stacked item layout that clips two images independently
        // to the item's mask
        if (i == 1 || i == 4) {
            Column(
                modifier = Modifier.height(205.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Image(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f),
                    // TODO
//                        .maskClip(MaterialTheme.shapes.extraLarge)
//                        .maskBorder(
//                            BorderStroke(3.dp, Color.Magenta),
//                            MaterialTheme.shapes.extraLarge
//                        ),
                    painter = painterResource(item.imageResId),
                    contentDescription = stringResource(item.contentDescriptionResId),
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier =
                        Modifier
                            .fillMaxSize(),
//                        .maskClip(RoundedCornerShape(8.dp))
//                        .maskBorder(
//                            BorderStroke(5.dp, Color.Green),
//                            RoundedCornerShape(8.dp)
//                        ),
                    painter = painterResource(item.imageResId),
                    contentDescription = stringResource(item.contentDescriptionResId),
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            // Mask using a generic path shape
            val pathShape =
                remember {
                    object : Shape {
                        override fun createOutline(
                            size: Size,
                            layoutDirection: LayoutDirection,
                            density: Density,
                        ): Outline {
                            val roundRect =
                                RoundRect(0f, 0f, size.width, size.height, CornerRadius(30f))
                            val shapePath =
                                Path().apply {
                                    addRoundRect(roundRect)
                                }
                            return Outline.Generic(shapePath)
                        }
                    }
                }
            Box(
                modifier =
                    Modifier
                        .height(205.dp),
//                    .maskClip(pathShape)
//                    .maskBorder(BorderStroke(5.dp, Color.Red), pathShape),
            ) {
                Image(
                    painter = painterResource(item.imageResId),
                    contentDescription = stringResource(item.contentDescriptionResId),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                ElevatedAssistChip(
                    onClick = { /* Do something! */ },
                    label = { Text("Image $i") },
                    modifier =
                        Modifier.graphicsLayer {
                            // Fade the chip in once the carousel item's size is large enough to
                            // display the entire chip
                            alpha =
                                lerp(
                                    0f,
                                    1f,
                                    max(
                                        size.width - (carouselItemInfo.maxSize) + carouselItemInfo.size,
                                        0f,
                                    ) / size.width,
                                )
                            // Translate the chip to be pinned to the left side of the item's mask
                            translationX = carouselItemInfo.maskRect.left + 8.dp.toPx()
                        },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Image,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize),
                        )
                    },
                )
            }
        }
    }
}

@Composable
actual fun HorizontalUncontainedCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    HorizontalUncontainedCarousel(
        state = rememberCarouselState { items.count() },
        modifier =
            Modifier
                .widthIn(412.dp)
                .heightIn(221.dp),
        itemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier.height(205.dp),
//                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(item.imageResId),
            contentDescription = stringResource(item.contentDescriptionResId),
            contentScale = ContentScale.Crop,
        )
    }
}
