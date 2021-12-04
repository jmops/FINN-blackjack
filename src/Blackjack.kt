/**
 * The game blackjack.
 *
 */
class Blackjack(cardsFileName : String? = null){
    private var cards : Cards = Cards(cardsFileName)              ///< The deck of cards used
    private var player : Player = Player(Consts.PLAYERNAME)       ///< The player trying his luck
    private var dealer : Player = Player("Dealer")          ///< The dealer
    private var initialCardsDealt = false


    /**
     * Start a new game of blackjack.
     * @return The winner of the game
     */
    fun playGame() : Winner{
        initialDeal()
        if(returnWinner() == Winner.NOBODY){
            initialCardsDealt = true // The initial stage is done
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
        print("And the winner is: ")
        when(winner){
            Winner.PLAYER -> print(player.playerName)
            Winner.DEALER -> print(dealer.playerName)
            else -> {
                throw Exception("Ohh no, there should be a winner at this point...")
            }
        }
        printCardsOnHandsAndScore()
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
        while (player.playerScore < Consts.PERFECTSCORE && dealer.playerScore <= player.playerScore && dealer.playerScore <= Consts.PERFECTSCORE){
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
     * Print out the cards for both the player and the dealer.
     */
    private fun printCardsOnHandsAndScore(){
        player.printCardsOnHandAndScore()
        dealer.printCardsOnHandAndScore()
    }

    /**
     * Return the winner of the game, if someone has won.
     * @return Winner.PLAYER the player has won
     * @return Winner.DEALER the dealer has won
     * @return Winner.NOBODY No one has won
     */
    private fun returnWinner() : Winner {
        if(initialCardsDealt){
            /**
             * Initial cards are already dealt.
             * If the player's score is higher than the perfect score, or the dealer has a higher score than the player (but not above the perfect score) the dealer wins, otherwise the player wins.
             */
            return if(player.playerScore > Consts.PERFECTSCORE || (dealer.playerScore > player.playerScore && dealer.playerScore <= Consts.PERFECTSCORE))
                Winner.DEALER
            else
                Winner.PLAYER
        }
        else{ // Initial deal. Two cards each.
            if(player.playerScore == Consts.PERFECTSCORE)
                return Winner.PLAYER
            /**
             * If the dealer and player both got two aces as their initial cards, or the dealer got a perfect score, the dealer wins.
             */
            else if(player.playerScore + dealer.playerScore == 22*2 || dealer.playerScore == Consts.PERFECTSCORE)
                return Winner.DEALER
        }
        return Winner.NOBODY // No winner yet, only applicable during the initial card dealing.
    }
    fun numberOfCardsDealt() : Int{
        return player.cardsOnHand.size + dealer.cardsOnHand.size
    }
}