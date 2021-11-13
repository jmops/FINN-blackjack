import java.lang.NumberFormatException

/**
 * Deck of cards.
 */
class Cards{
    /**
     * Each card is represented by a string. e.g. the string C13 would be the king of clubs.
     */
    var cards :  Array<String> = Array<String>(52){""}
    var cardsDealt = 0 // Index of the next card

    init {
        fillDeckOfCardsAndShuffle()
    }

    /**
     * Fill the array holding all the cards.
     */
    fun fillDeckOfCardsAndShuffle(){
        /**
         * There are 52 cards, 13 clubs, diamonds, heart and spades, in total 52 cards. Each interval of 13 is one suit.
         */
        for(i in this.cards.indices){
            if(i < 13) // Clubs
                cards[i] = "C" + getFaceOfCard(i+1).toString()
            else if(i < 26)// Diamonds
                cards[i] = "D" + getFaceOfCard((i % 13)+1).toString()
            else if(i < 39) // Hearts
                cards[i] = "H" + getFaceOfCard((i % 26)+1).toString()
            else            // Spades
                cards[i] = "S" + getFaceOfCard((i % 39)+1).toString()

        }
        shuffleCards() // Randomize the cards
        cardsDealt = 0
    }

    /**
     * Get the face of the card.
     * @param face which card
     * @return face
     * @throws exception if the card number is invalid.
     */
    fun getFaceOfCard(face : Int) : String{
        if(face in 2..10){
            return face.toString()
        }
        else{
            when(face){
                11 -> return "J"
                12 -> return "Q"
                13 -> return "K"
                1 -> return "A"
            }
            throw Exception("Invalid card")
        }
    }

    /**
     * Shuffle the cards.
     */
    fun shuffleCards(){
        cards.shuffle() // Shuffles the cards, randomizing the order.
    }

    /**
     * Pull a card from the deck.
     * @return the card OR null if there are no more cards.
     */
    fun pullCard() : String {
        if(cardsDealt < 52){
                return cards[cardsDealt++] // Retrieve the next card, increment counter
        }
        else
            fillDeckOfCardsAndShuffle() // All cards are spent, re-shuffle
            return pullCard()           // Try again
    }
}