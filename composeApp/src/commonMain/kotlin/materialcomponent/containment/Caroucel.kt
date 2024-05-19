package materialcomponent.containment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmpmateriallab.composeapp.generated.resources.Res
import kmpmateriallab.composeapp.generated.resources.carousel_image_1
import kmpmateriallab.composeapp.generated.resources.carousel_image_10
import kmpmateriallab.composeapp.generated.resources.carousel_image_1_description
import kmpmateriallab.composeapp.generated.resources.carousel_image_2
import kmpmateriallab.composeapp.generated.resources.carousel_image_2_description
import kmpmateriallab.composeapp.generated.resources.carousel_image_3
import kmpmateriallab.composeapp.generated.resources.carousel_image_3_description
import kmpmateriallab.composeapp.generated.resources.carousel_image_4
import kmpmateriallab.composeapp.generated.resources.carousel_image_4_description
import kmpmateriallab.composeapp.generated.resources.carousel_image_5
import kmpmateriallab.composeapp.generated.resources.carousel_image_5_description
import kmpmateriallab.composeapp.generated.resources.carousel_image_6
import kmpmateriallab.composeapp.generated.resources.carousel_image_7
import kmpmateriallab.composeapp.generated.resources.carousel_image_8
import kmpmateriallab.composeapp.generated.resources.carousel_image_9
import materialcomponent.common.BorderBox
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class CarouselItem(
    val id: Int,
    val imageResId: DrawableResource,
    val contentDescriptionResId: StringResource
)

val items = listOf(
    CarouselItem(0, Res.drawable.carousel_image_1, Res.string.carousel_image_1_description),
    CarouselItem(1, Res.drawable.carousel_image_2, Res.string.carousel_image_2_description),
    CarouselItem(2, Res.drawable.carousel_image_3, Res.string.carousel_image_3_description),
    CarouselItem(3, Res.drawable.carousel_image_4, Res.string.carousel_image_4_description),
    CarouselItem(4, Res.drawable.carousel_image_5, Res.string.carousel_image_5_description),
    CarouselItem(5, Res.drawable.carousel_image_6, Res.string.carousel_image_1_description),
    CarouselItem(6, Res.drawable.carousel_image_7, Res.string.carousel_image_2_description),
    CarouselItem(7, Res.drawable.carousel_image_8, Res.string.carousel_image_3_description),
    CarouselItem(8, Res.drawable.carousel_image_9, Res.string.carousel_image_4_description),
    CarouselItem(9, Res.drawable.carousel_image_10, Res.string.carousel_image_5_description),
)

@Composable
expect fun HorizontalMultiBrowseCarouselContent(
    items: List<CarouselItem>,
    modifier: Modifier = Modifier
)

@Composable
expect fun HorizontalUncontainedCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier = Modifier
)

@Composable
expect fun FadingHorizontalMultiBrowseCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier = Modifier
)

fun LazyListScope.carousel(
    modifier: Modifier = Modifier,
    onClickButton: (String) -> Unit = {},
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "Carousel"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("MultiBrowse")
                HorizontalMultiBrowseCarouselContent(
                    items = items,
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Uncontained")
                HorizontalUncontainedCarouselSample(
                    items = items,
                    modifier = Modifier.fillMaxWidth()
                )
                Text("--")
                FadingHorizontalMultiBrowseCarouselSample(
                    items = items,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}