import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.circle_24px
import tictactoe.composeapp.generated.resources.close_24px
import tictactoe.composeapp.generated.resources.compose_multiplatform
import tictactoe.composeapp.generated.resources.empty_24px

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val viewModel = GameViewModel(7,8)
    AppWithParams(
        viewModel,
        cross = {
            Image(
                painter = painterResource(Res.drawable.close_24px),
                contentDescription = "Cross"
            )
        },
        circle = {
            Image(
                painter = painterResource(Res.drawable.circle_24px),
                contentDescription = "Circle"
            )
        },
        empty = {
            Image(
                painter = painterResource(Res.drawable.empty_24px),
                contentDescription = "Empty"
            )
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun AppWithParams(viewModel: GameViewModel = GameViewModel(), cross: @Composable () -> Unit, circle: @Composable () -> Unit, empty: @Composable () -> Unit ) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            val borderModifier = Modifier.border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Blue)
            )
            Text("count ${viewModel.tapCount}")

            Row {
                repeat(viewModel.sizeX) { x ->
                    Column {
                        repeat(viewModel.sizeY) { y ->
                            //var isClicked by remember { mutableStateOf(false) }

                            IconButton(
                                onClick = {
                                    var nextMark = 1
                                    if (viewModel.latestTappedMark == 1) {
                                        nextMark = 2
                                    }
                                    viewModel.tapped(x, y, nextMark)
                                          },
                                modifier = borderModifier)
                            {
                                val index = x + y * viewModel.sizeX
                                if (viewModel.ticArray[index] == 1) {
                                    cross()
                                }
                                else if (viewModel.ticArray[index] == 2) {
                                    circle()
                                }
                                else {
                                    empty()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}