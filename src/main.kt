
/**
 * This application lets a player play against the dealer in a game of blackjack (21).
 *
 * @author Jørgen Mo Opsahl
 */
fun main(){
    var game : Blackjack
    try{
        game = Blackjack()
    }
    catch (e : Exception){
        println(e)
    }

}