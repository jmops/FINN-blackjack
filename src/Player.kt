class Player(name : String) {
    var playerName : String = name
    var cardsOnHand : MutableList<String> = arrayListOf() ///< The cards a player is holding
    var playerScore : Int = 0   ///< The player's score


    fun drawCard(card : String){
        cardsOnHand.add(card)
        printCardsOnHand()
    }

    fun printCardsOnHand(){
        println("$playerName cards: ")
        for(card in cardsOnHand){
            println(card)
        }
        println(playerScore)
    }
}