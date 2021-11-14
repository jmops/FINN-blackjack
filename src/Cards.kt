import java.io.File
import java.io.InputStream
import java.time.temporal.Temporal

/**
 * Deck of cards.
 */
class Cards(cardsFileName : String){
    /**
     * Each card is a string. The string C13 would be the king of clubs etc.
     */
    private var cards :  MutableList<String> = arrayListOf() ///< The deck of cards
    private var cardsDealt = 0 ///< Index of the next card

    init {
        fillDeckOfCardsAndShuffle(cardsFileName)
    }

    /**
     * Fill the list with cards. Fill up a complete deck, or create a deck from a file.
     * @param cardsFileName A possible filename for cards.
     */
    private fun fillDeckOfCardsAndShuffle(cardsFileName : String){

        if(cardsFileName.isNotEmpty()){
            fillDeckOfCardsFromFile(cardsFileName)
        }
        else{
            fillNewDeckOfCards()
            cards.shuffle() // Shuffles the cards, randomizing the order.
        }
        cardsDealt = 0
    }


    /**
     * Retrieve the deck of cards from a file instead of a full random deck.
     * @param cardsFileName the name of the file containing the deck
     * @see getCard(...)
     */
    private fun fillDeckOfCardsFromFile(cardsFileName : String){
        var fileContent = ""
        var cardsFromFile : MutableList<String> = arrayListOf()
        try{
            File(cardsFileName).forEachLine {fileContent = it} // Everything is on one line
        }
        catch (e : Exception) {
            throw Exception("Error reading file: $cardsFileName")
        }
        for(card in fileContent.split(",", "\n"))
            cardsFromFile.add(getCard(card.replace("\n", "")
                            .filter { !it.isWhitespace()}))
        cards = cardsFromFile
    }

    /**
     * Retrieve the card if it is a valid card.
     * @param card the possible card
     * @throws Exception if the card is invalid
     */
    private fun getCard(card : String) : String{
        var possibleCard : String = card
        var tempCard = ""
        try{
            if(card.isNotEmpty() && (possibleCard[0] == 'C' || possibleCard[0] == 'D' ||
                        possibleCard[0] == 'H' || possibleCard[0] == 'S')){ // Correct suit

                tempCard += possibleCard[0]

                if(possibleCard.length > 2 && possibleCard.substring(1,3) == "10"){ // 10 digit

                    tempCard += "10"

                }
                else if(possibleCard.length == 2){ // Is either a knight, queen, king, ace or a digit in the 2-9 range

                    if (possibleCard[1] == 'J' || possibleCard[1] == 'Q' ||
                        possibleCard[1] == 'K' || possibleCard[1] == 'A' ||
                        possibleCard[1].digitToInt() in 2..9){
                        tempCard += possibleCard[1]
                    }
                }
            }
            if(tempCard.length < 2){ // The card is not complete
                throw Exception()
            }
        }catch (e : Exception){
            throw Exception("Invalid card: $card")
        }
        return tempCard // Either of length 2 or 3 (tens)
    }

    /**
     * Fill up the deck of cards with 52 cards.
     */
    private fun fillNewDeckOfCards(){
        /**
         * There are 52 cards, 13 clubs, diamonds, heart and spades, in total 52 cards. Each interval of 13 is one suit.
         */
        for(i in 1..52){
            if(i < 14) // Clubs
                cards.add("C" + getFaceOfCard(i).toString())
            else if(i < 27)// Diamonds
                cards.add("D" + getFaceOfCard((i % 14)+1).toString())
            else if(i < 40) // Hearts
                cards.add("H" + getFaceOfCard((i % 27)+1).toString())
            else            // Spades
                cards.add("S" + getFaceOfCard((i % 40)+1).toString())
        }
    }

    /**
     * Get the face of the card.
     * @param face which card
     * @return face
     * @throws exception if the card number is invalid.
     */
    private fun getFaceOfCard(face : Int) : String{
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
            throw Exception("Invalid card: $face")
        }
    }

    /**
     * Pull a card from the deck.
     * @return the card.
     * @throws Exception if there's no more cards.
     */
    fun pullCard() : String {
        if(cardsDealt < cards.size){
                return cards[cardsDealt++] // Retrieve the next card, increment counter
        }
        else
            throw Exception("No more cards in the deck. ($cardsDealt used.")
    }
}