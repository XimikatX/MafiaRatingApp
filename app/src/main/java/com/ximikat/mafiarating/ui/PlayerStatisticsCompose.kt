package com.ximikat.mafiarating.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ximikat.mafiarating.model.domain.Team
import com.ximikat.mafiarating.ui.viewmodel.PlayerStatisticsViewModel

@Composable
fun PlayerStatisticsCompose(viewModel: PlayerStatisticsViewModel) {

    val state = viewModel.mainState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.value.player.nickname)
                }
            )
        }
    ) {

        Column(
            Modifier.scrollable(rememberScrollState(), Orientation.Vertical)
        ) {

            Row(
                Modifier.align(Alignment.CenterHorizontally)
            ) {

                val player = state.value.player
                val games = state.value.games.filter { game ->
                    game.containsPlayer(player)
                }

                val numWonGames = games.count { game ->
                    game.indexOfPlayer(player)?.let {
                        val isMafia = game.maf1 == it || game.maf2 == it || game.don == it
                        return@let isMafia == (game.winningTeam == Team.BLACK)
                    } ?: false
                }
                val numLostGames = games.size - numWonGames

                val winRate = (numWonGames.toDouble() / games.size) * 100

                Column {

                    if (games.isNotEmpty()) {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start),
                        ) {
                            Box(Modifier.padding(16.dp)) {
                                DonutChart(
                                    values = numWonGames to numLostGames, // #
                                    colors = Color(0xFFFFD700) to Color.Gray,
                                    size = 64.dp
                                )
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.CenterEnd)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(start = 8.dp),
                                    text = "Winrate ${winRate.toInt()}%",
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }

                }

            }

            GamesListCompose(
                games = state.value.games.filter { game ->
                    game.containsPlayer(state.value.player)
                },
                selectedGame = state.value.selectedGame,
                { viewModel.toggleGameSelection(it) },
                { viewModel.deleteGame(it) }
            )

        }

    }

}