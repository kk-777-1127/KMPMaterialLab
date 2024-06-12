package materialcomponent.containment

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import materialcomponent.common.BorderBox

fun LazyListScope.sideSheet(
    modifier: Modifier = Modifier,
    onClickButton: (String) -> Unit = {},
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "SideSheet",
        ) {
            Text("Planed")
        }
    }
}
