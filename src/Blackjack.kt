/**
 * The game blackjack.
 *
 */
class Blackjack(cardsFileName : String){
    var cards : Cards = Cards(cardsFileName) ///< The deck of cards used
    var player : Player = Player("Sam")     ///< The player trying his luck
    var dealer : Player = Player("Dealer")  ///< The dealer

    init {

            player = Player("Sam")
            dealer = Player("Dealer")
            initialDeal()
    }

    /**
     * Start a new game of blackjack.
     * @return The winner of the game
     */
    fun playGame() : Winner{
        if(returnWinner() == Winner.NOBODY){
            /**
             * The player goes first
             */
            playPlayersTurn()
            /**
             * Dealer is next
             **/
            playDealersTurn()
        }

        printWinnerAndCardOnHands(returnWinner())

        return returnWinner()



    }

    /**
     * Print out the name of the winner, and all cards on hand.
     * @param winner
     */
    private fun printWinnerAndCardOnHands(winner: Winner){
        when(winner){
            Winner.PLAYER -> print(player.playerName)
            Winner.DEALER -> print(dealer.playerName)
        }
        player.printCardsOnHand()
        dealer.printCardsOnHand()
    }

    /**
     * Retrieve the score from the card.
     * @param card the card dealt to either the player or dealer
     * @return  score of the card
     * @throws exception if the card face is invalid.
     */
    private fun getScoreFromCard(card : String) : Int {
        var digits : Int
        var cardFace = card.substring(1, card.length)

        return if(cardFace == "J" || cardFace == "Q" || cardFace == "K")
            10
        else if(cardFace == "A")
            11
        else{
            digits = cardFace.toInt()
            if(digits in 2..10)
                digits
            else
                throw Exception("Invalid card!")
        }

    }

    /**
     * Update the score of the player based on what cards the player hold.
     */
    private fun updateParticipantScore(participant : Player){
        participant.playerScore = 0
        for(card in participant.cardsOnHand){
            participant.playerScore += getScoreFromCard(card)
        }
    }

    /**
     * the player draws cards.
     */
    private fun playPlayersTurn(){
        while(player.playerScore < Consts.PLAYERSTOPDRAWINGSCORE){
            player.drawCard(cards.pullCard())
            updateParticipantScore(player)
        }
    }

    /**
     * The dealer draws cards.
     */
    private fun playDealersTurn(){
        while (player.playerScore < Consts.WINNINGSCORE && dealer.playerScore <= player.playerScore && dealer.playerScore <= Consts.WINNINGSCORE){
            dealer.drawCard(cards.pullCard())
            updateParticipantScore(dealer)
        }
    }

    /**
     * Deal out the initial hand of cards to both the dealer and the player.
     */
    private fun initialDeal(){
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
         * Update score
         */
        updateParticipantScore(player)
        updateParticipantScore(dealer)
    }

    /**
     * Return the winner of the game, if someone has won.
     * @return Winner.PLAYER the player has won
     * @return Winner.DEALER the dealer has won
     * @return Winner.NOBODY No one has won
     */
    fun returnWinner() : Winner {
        if(player.cardsOnHand.size + dealer.cardsOnHand.size > 4){
            if(player.playerScore > Consts.WINNINGSCORE || (dealer.playerScore > player.playerScore && dealer.playerScore <= Consts.WINNINGSCORE))
                return Winner.DEALER
            else
                return Winner.PLAYER
        }
        else{ // Initial deal. Two cards each.
            if(player.playerScore == Consts.WINNINGSCORE)
                return Winner.PLAYER
            else if(player.playerScore + dealer.playerScore == 22*2 || dealer.playerScore == Consts.WINNINGSCORE)
                return Winner.DEALER
        }
        return Winner.NOBODY
    }
}