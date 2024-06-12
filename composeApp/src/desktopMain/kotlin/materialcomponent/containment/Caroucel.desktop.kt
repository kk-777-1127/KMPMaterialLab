package materialcomponent.containment

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun HorizontalMultiBrowseCarouselContent(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    Text("Not Supported on Desktop?")
//    HorizontalMultiBrowseCarousel(
//        state = rememberCarouselState { items.count() },
//        modifier = Modifier
//            .width(412.dp)
//            .height(221.dp),
//        preferredItemWidth = 186.dp,
//        itemSpacing = 8.dp,
//        contentPadding = PaddingValues(horizontal = 16.dp)
//    ) {
//        val item = items[it]
//        Image(
//            modifier = Modifier.height(205.dp)
// //                .maskClip(MaterialTheme.shapes.extraLarge) TODO 対応したら入れる
//            ,
//            painter = painterResource(resource = item.imageResId),
//            contentDescription = stringResource(item.contentDescriptionResId),
//            contentScale = ContentScale.Crop
//        )
//    }
}

@Composable
actual fun FadingHorizontalMultiBrowseCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    Text("Not Supported on Desktop?")
}

@Composable
actual fun HorizontalUncontainedCarouselSample(
    items: List<CarouselItem>,
    modifier: Modifier,
) {
    Text("Not Supported on Desktop?")
}
