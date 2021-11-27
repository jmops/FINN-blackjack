/**
 * This application lets a player (Sam)  play against the dealer in a game of blackjack (21).
 *
 * @author Jørgen Mo Opsahl
 */

/**
 * Main:
 * @param args Arguments
 */
fun main(args : Array<String>){
    var game : Blackjack
    try{
        game = if(args.isNotEmpty()) // Filename given
            Blackjack(args[0])
         else                        // No filename given
            Blackjack()

        game.playGame()
    }
    catch (e : Exception){
        println("Something went wrong:\n${e.message}")
    }



}