/**
 * The game blackjack.
 *
 */
class Blackjack {
    var playerName : String = "Sam"
    var cards : Cards = Cards()
    //var dealer : String = "Dealer"
    init {
        playGame()
    }

    /**
     * Start a new game of blackjack.
     */
    fun playGame(){
        var playerScore : Int = 0
        var dealerScore : Int = 0
        var numberOfCardsDealt : Int = 0
        var cardDealt : String = ""
        while (playerScore < 17){
            playerScore += getScoreFromCard(cards.pullCard())
            numberOfCardsDealt++
        }
        // Loop as long as the dealer's score is less than 17, less than the player's score and
        while (dealerScore < 17 && dealerScore < playerScore && playerScore <= 21){
            dealerScore += getScoreFromCard(cards.pullCard())
            numberOfCardsDealt++

        }
    }

    /**
     * Retrieve the score from the card.
     * @param card the card dealt to either the player or dealer
     */
    fun getScoreFromCard(card : String) : Int {
        var digits = card.substring(1, card.length-1).toInt() // Retrieve the digits from the card.
        if(digits > 9)
            return 10
        else if(digits == 1)
            return 11
        return digits
    }
}