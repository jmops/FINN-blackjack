/**
 * The participants of the game.
 */
class Player(name : String) {
    var playerName : String = name ///< Name of player
    var cardsOnHand : MutableList<String> = arrayListOf() ///< The cards a player is holding
    var playerScore : Int = 0   ///< The player's score

    /**
     * Draw a new card and add to hand.
     * @param card the new card
     */
    fun drawCard(card : String){
        cardsOnHand.add(card)
    }

    /**
     * Print all the cards the player is holding.
     */
    fun printCardsOnHandAndScore(){
        print("\n$playerName: ")
        for(card in cardsOnHand){
            print(" $card,")
        }
        print("\nScore: $playerScore")
    }
}