package materialcomponent.containment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import materialcomponent.common.BorderBox

fun LazyListScope.cards(
    onClickButton: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        BorderBox(
            modifier = modifier,
            label = "Buttons",
        ) {
            FlowRow(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CardSample()
                ClickableCardSample { onClickButton("ClickableCardSample") }
                ElevatedCardSample()
                ClickableElevatedCardSample { onClickButton("ClickableElevatedCardSample") }
                OutlinedCardSample()
                ClickableOutlinedCardSample { onClickButton("ClickableOutlinedCardSample") }
            }
        }
    }
}

@Composable
fun CardSample() {
    Card(Modifier.size(width = 180.dp, height = 100.dp)) {
        Box(Modifier.fillMaxSize()) {
            Text("FilledCard", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ClickableCardSample(onClickButton: () -> Unit) {
    Card(
        onClick = onClickButton,
        modifier = Modifier.size(width = 180.dp, height = 100.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            Text("Clickable", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ElevatedCardSample() {
    ElevatedCard(Modifier.size(width = 180.dp, height = 100.dp)) {
        Box(Modifier.fillMaxSize()) {
            Text("ElevatedCard", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ClickableElevatedCardSample(onClickButton: () -> Unit) {
    ElevatedCard(
        onClick = onClickButton,
        modifier = Modifier.size(width = 180.dp, height = 100.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            Text("Clickable", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun OutlinedCardSample() {
    OutlinedCard(Modifier.size(width = 180.dp, height = 100.dp)) {
        Box(Modifier.fillMaxSize()) {
            Text("OutlinedCard", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ClickableOutlinedCardSample(onClickButton: () -> Unit) {
    OutlinedCard(
        onClick = onClickButton,
        modifier = Modifier.size(width = 180.dp, height = 100.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            Text("Clickable", Modifier.align(Alignment.Center))
        }
    }
}
