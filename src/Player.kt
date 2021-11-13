/**
 * The participants of the game.
 */
class Player(name : String) {
    var playerName : String = name
    var cardsOnHand : MutableList<String> = arrayListOf() ///< The cards a player is holding
    var playerScore : Int = 0   ///< The player's score

    /**
     * Draw a new card and add to hand.
     * @param card the new card
     */
    fun drawCard(card : String){
        cardsOnHand.add(card)
        //printCardsOnHand()
    }

    /**
     * Print all the cards the player is holding.
     */
    fun printCardsOnHand(){
        println("$playerName cards:")
        for(card in cardsOnHand){
            print(" $card,")
        }
        println(playerScore)
    }
}