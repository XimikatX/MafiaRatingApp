package com.ximikat.mafiarating.model.domain

import java.util.*

data class Game(
    val id: Int? = null,
    val entries: List<Pair<Player, Double>>,
    val maf1: Int, val maf2: Int,
    val sheriff: Int, val don: Int,
    val winningTeam: Team,
    val date: Date
) {
    fun containsPlayer(player: Player): Boolean {
        return entries.map(Pair<Player, Double>::first).contains(player)
    }
    fun indexOfPlayer(player: Player): Int? {
        val index = entries.map { it.first }.indexOf(player)
        return if (index == -1) null else index
    }
}

enum class Role {
    CIVILIANS, MAFIA, SHERIFF, DON
}

enum class Team {
    RED, BLACK
}
