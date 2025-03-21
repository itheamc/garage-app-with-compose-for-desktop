package ui.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spacer2() {
    Spacer(modifier = Modifier.size(2.dp))
}

@Composable
fun Spacer4() {
    Spacer(modifier = Modifier.size(4.dp))
}

@Composable
fun Spacer8() {
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
fun Spacer12() {
    Spacer(modifier = Modifier.size(12.dp))
}

@Composable
fun Spacer16() {
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun Spacer20() {
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun Spacer24() {
    Spacer(modifier = Modifier.size(24.dp))
}

@Composable
fun Spacer36() {
    Spacer(modifier = Modifier.size(36.dp))
}

@Composable
fun SpacerX(x: Int) {
    Spacer(modifier = Modifier.size(x.dp))
}