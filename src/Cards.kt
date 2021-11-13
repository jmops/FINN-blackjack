import java.io.File
import java.io.InputStream
import java.time.temporal.Temporal

/**
 * Deck of cards.
 */
class Cards(cardsFileName : String){
    /**
     * Each card is represented by a string. e.g. the string C13 would be the king of clubs.
     */
    var cards :  MutableList<String> = arrayListOf()
    var cardsDealt = 0 // Index of the next card

    init {
        fillDeckOfCardsAndShuffle(cardsFileName)
    }

    /**
     * Fill the array holding all the cards.
     */
    fun fillDeckOfCardsAndShuffle(cardsFileName : String){

        if(cardsFileName.isNotEmpty()){
            fillDeckOfCardsFromFile(cardsFileName)
        }
        else{
            fillNewDeckOfCards()
        }
        shuffleCards() // Randomize the cards
        cardsDealt = 0
    }

    /**
     * Retrieve the deck of cards from a file instead of a full random deck.
     * @param cardsFileName the name of the file containing the deck
     * @see getCard(...)
     */
    fun fillDeckOfCardsFromFile(cardsFileName : String){
        var fileContent : String = ""
        var cardsFromFile : MutableList<String> = arrayListOf()
        try{
            File(cardsFileName).forEachLine {fileContent = it}
        }
        catch (e : Exception) {
            throw Exception("Error reading file: $cardsFileName")
        }
        for(card in fileContent.split(",", "\n"))
            cardsFromFile.add(getCard(card.replace("\n", "")
                            .filter { !it.isWhitespace()}))
        cards = cardsFromFile
        //println(fileContent)

    }

    /**
     * Retrieve the card if it is a valid card.
     * @param card the possible card
     * @throws Exception if the card is invalid
     */
    fun getCard(card : String) : String{

        var possibleCard : String = card //.filter { !it.isWhitespace()}

        var tempCard = ""
        try{
            if(card.isNotEmpty() && (possibleCard[0] == 'C' || possibleCard[0] == 'D' ||
                        possibleCard[0] == 'H' || possibleCard[0] == 'S')){

                tempCard += possibleCard[0]

                if(possibleCard.length > 2 && possibleCard.substring(1,3) == "10"){

                    tempCard += "10"
                }
                else if(possibleCard.length == 2){
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


        println("$tempCard")
        return tempCard // Either of length 2 or 3 (tens)
    }

    /**
     * Fill up the deck of cards with 52 cards.
     */
    fun fillNewDeckOfCards(){
        /**
         * There are 52 cards, 13 clubs, diamonds, heart and spades, in total 52 cards. Each interval of 13 is one suit.
         */
        for(i in 0..51){
            if(i < 13) // Clubs
                cards.add("C" + getFaceOfCard(i+1).toString())
            else if(i < 26)// Diamonds
                cards.add("D" + getFaceOfCard((i % 13)+1).toString())
            else if(i < 39) // Hearts
                cards.add("H" + getFaceOfCard((i % 26)+1).toString())
            else            // Spades
                cards.add("S" + getFaceOfCard((i % 39)+1).toString())

        }
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
            fillDeckOfCardsAndShuffle("") // All cards are spent, re-shuffle
            return pullCard()           // Try again
    }
}