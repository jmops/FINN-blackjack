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
                cards[i] = "C" + (i+1).toString()
            else if(i < 26)// Diamonds
                cards[i] = "D" + ((i % 13)+1).toString()
            else if(i < 39) // Hearts
                cards[i] = "H" + ((i % 26)+1).toString()
            else            // Spades
                cards[i] = "S" + ((i % 39)+1).toString()
        }
        cards.shuffle() // Shuffles the cards, randomizing the order.
        cardsDealt = 0  // The cards are now shuffled.
        /*for(card in cards)
            println(card)*/
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