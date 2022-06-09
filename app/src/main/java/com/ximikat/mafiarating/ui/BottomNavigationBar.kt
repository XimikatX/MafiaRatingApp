package com.ximikat.mafiarating.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ximikat.mafiarating.R

@Composable
fun BottomNavigationBar(navigationController: NavHostController) {

    val items = listOf(
        BottomNavigationItem.GamesScreen to R.drawable.ic_gamepad_square,
        BottomNavigationItem.PlayersScreen to R.drawable.ic_account_multiple
    )

    BottomNavigation {

        items.forEach { (it, iconId) ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = iconId), it.title) },
                label = { Text(text = it.title) },
                selected = navigationController.currentDestination?.route == it.route,
                onClick = {
                    navigationController.navigate(it.route) { launchSingleTop = true }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f)
            )
        }

    }

}