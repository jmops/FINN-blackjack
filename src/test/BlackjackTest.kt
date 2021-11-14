import org.junit.Test
import kotlin.test.assertEquals

class BlackjackTest {
    @Test
    fun playerWinsAfterInitialDeal(){
        var game = Blackjack("src/test/PlayerWinsAfterInitialDeal.dta")
        assertEquals(Winner.PLAYER, game.returnWinner())
    }
    @Test
    fun playerWins(){
        var game = Blackjack("src/test/PlayerWins.dta")
        assertEquals(Winner.PLAYER, game.playGame())
    }

    @Test
    fun dealerWins(){
        var game = Blackjack("src/test/DealerWins.dta")
        assertEquals(Winner.DEALER, game.playGame())
    }


}