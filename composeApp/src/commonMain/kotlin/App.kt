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

@Composable
@Preview
fun AppWithParams(viewModel: GameViewModel = GameViewModel(), cross: @Composable () -> Unit, circle: @Composable () -> Unit, empty: @Composable () -> Unit ) {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { viewModel.initialize() }) {
                Text("New game!")
            }

            val borderModifier = Modifier.border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Blue)
            )
            Text("Next turn: ${viewModel.nextTurn}")

            Row {
                repeat(viewModel.sizeX) { x ->
                    Column {
                        repeat(viewModel.sizeY) { y ->
                            IconButton(
                                onClick = { viewModel.tapped(x, y) },
                                modifier = borderModifier)
                            {
                                val mark = viewModel.get(x,y)
                                when (mark) {
                                    TicMark.X -> {
                                        cross()
                                    }
                                    TicMark.O -> {
                                        circle()
                                    }
                                    else -> {
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
}