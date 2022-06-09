package com.ximikat.mafiarating.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ximikat.mafiarating.model.domain.Game

@Composable
fun GamesListCompose(
    games: List<Game>,
    selectedGame: Game?,
    onGameClick: (Game) -> Unit,
    onDeleteClick: (Game) -> Unit
) {

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        // .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        items(games) {
            GameItem(game = it, it == selectedGame, {
                onGameClick(it)
            }, {
                onDeleteClick(it)
            })
        }
    }

}