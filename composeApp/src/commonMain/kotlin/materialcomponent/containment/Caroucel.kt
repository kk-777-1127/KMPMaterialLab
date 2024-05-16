package materialcomponent.containment

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import materialcomponent.common.BorderBox

fun LazyListScope.carousel(
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "Buttons"
        ) {

        }
    }
}

data class CarouselItem(
    val id: Int,
    val imageResId: Int,
    val contentDescriptionResId: Int
)