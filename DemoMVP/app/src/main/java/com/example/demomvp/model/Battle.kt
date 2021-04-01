package com.example.demomvp.model

data class Battle(
     var name : String = "",
     var location: String = "",
     var attackerKing: String = "",
     var defenderKing: String = ""
)

object BattleEntry{
    const val NAME = "name"
    const val LOCATION = "location"
    const val ATTACKERKING = "attacker_king"
    const val DEFENDERKING = "defender_king"
}


