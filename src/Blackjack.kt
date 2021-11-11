/**
 * The game blackjack.
 *
 */
class Blackjack {
    var cards : Cards = Cards() ///< The deck of cards used
    var player : Player = Player("Sam")     ///< The player trying his luck
    var dealer : Player = Player("Dealer")  ///< The dealer

    //var dealer : String = "Dealer"
    init {
        var winner = playGame()
        if(winner == 1)
            println(player.playerName)
        else
            println(dealer.playerName)
    }

    /**
     * Start a new game of blackjack.
     * @return The winner of the game
     */
    fun playGame() : Int{
        initialDeal()
        //player.printCardsOnHand()
        //dealer.printCardsOnHand()
        if(returnWinner() == 0){
            /**
             * The player goes first
             */
            playPlayersTurn()
            //player.printCardsOnHand()

            /**
             * Dealer is next
             **/
            playDealersTurn()
            //dealer.printCardsOnHand()

        }
        player.printCardsOnHand()
        dealer.printCardsOnHand()
        return returnWinner()



    }

    /**
     * Retrieve the score from the card.
     * @param card the card dealt to either the player or dealer
     * @return  score of the card
     */
    fun getScoreFromCard(card : String) : Int {
        var digits = card.substring(1, card.length).toInt() // Retrieve the digits from the card.

        if(digits in 1..13){
            if(digits > 9) // 10, Knight, queen, king
                return 10
            else if(digits == 1)    // Ace
                return 11
            return digits // 1 < digits < 10
        }
        throw Exception("Invalid card: $card") // Should never happen
    }

    /**
     * Update the score of the player based on what cards the player hold.
     */
    fun updateParticipantScore(participant : Player){
        participant.playerScore = 0
        for(card in participant.cardsOnHand){
            participant.playerScore += getScoreFromCard(card)
        }
    }

    /**
     * the player draws cards.
     */
    fun playPlayersTurn(){
        while(player.playerScore < Consts.PLAYERSTOPDRAWINGSCORE){
            player.drawCard(cards.pullCard())
            updateParticipantScore(player)
        }
    }

    /**
     * The dealer draws cards.
     */
     fun playDealersTurn(){
        while (dealer.playerScore < player.playerScore && dealer.playerScore <= Consts.ULTIMATESCORE){
            dealer.drawCard(cards.pullCard())
            updateParticipantScore(dealer)
        }
    }

    /**
     * Deal out the initial hand of cards to both the dealer and the player.
     */
    fun initialDeal(){
        /**
         * Create the initial hand of cards for both the player and dealer.
         * Pulled in the following order: player, dealer, player, dealer
         */
        for(i in 0..3){
            if(i % 2 == 0){
                player.drawCard(cards.pullCard())
            }
            else{
                dealer.drawCard(cards.pullCard())
            }
        }
        /**
         * Update all participant's score
         */
        updateParticipantScore(player)
        updateParticipantScore(dealer)
        player.printCardsOnHand()
        dealer.printCardsOnHand()
        println("-------------------------------")
    }

    /**
     * Return the winner of the game, if someone has won.
     * @return 1 the player has won
     * @return 2 the dealer has won
     * @return 0 No one has won
     */
    fun returnWinner() : Int {
        if(player.cardsOnHand.size + dealer.cardsOnHand.size > 4){
            if(player.playerScore > 21 || (dealer.playerScore > player.playerScore && dealer.playerScore <= 21))
                return 2
            else
                return 1
        }
        else{
            if(player.playerScore + dealer.playerScore == 22*2)
                return 2
        }
        return 0


    }
}