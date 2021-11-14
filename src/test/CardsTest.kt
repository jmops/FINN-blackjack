import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CardsTest {

    @Test
    fun fillDeckOfCardsAndShuffle() {
        var testCards : MutableList<String> = arrayListOf()
        val cards : Cards = Cards("")
        for(i in 1..52){
            testCards.add(cards.pullCard())
        }

        assertEquals(52, testCards.distinct().size) // Only unique cards
    }

    @Test
    fun testFillDeckOfCardsFromFile() {
        val cards : Cards = Cards("src/test/TestDataFile.dta")
        var testCards : MutableList<String> = arrayListOf()

        for(i in 1..7){
            testCards.add(cards.pullCard())
        }
        assertEquals("D3", testCards[0])
        assertEquals("SA", testCards[1])
        assertEquals("D10", testCards[2])
        assertEquals("HJ", testCards[3])
        assertEquals("CQ", testCards[4])
        assertEquals("S8", testCards[5])
        assertEquals("DK", testCards[6])

    }

    @Test
    fun testInvalidFilename(){
        assertFailsWith<Exception> {
            var ExceptionCards : Cards = Cards(" ") // blank
        }
    }


}