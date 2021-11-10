/**
 * Deck of cards.
 */
class Cards{
    var cards :  Array<String> = Array<String>(52){""}
    var cardsDealt = 0 // Index of the next card
    init {
        //println("${this.test}")
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
            else if(i < 39) // Heart
                cards[i] = "H" + ((i % 26)+1).toString()
            else            // Spades
                cards[i] = "S" + ((i % 39)+1).toString()
        }
        cards.shuffle() // Shuffles the cards, randomizing it.

        for(card in cards)
            println(card)
    }

    /**
     * Pull a card from the deck.
     * @return the card OR null if there are no more cards.
     */
    fun pullCard() : String{
        if(cardsDealt < 52){
                var card : String = cards[cardsDealt++]
                return card
        }
        else
            fillDeckOfCardsAndShuffle()
            return pullCard()
    }
}