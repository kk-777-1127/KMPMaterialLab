package materialcomponent.communication

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import materialcomponent.common.BorderBox

fun LazyListScope.snackBar(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "snackBar"
        ) {
            Button(onClick = onClickButton) {
                Text("showSnackBar")
            }
        }
    }
}