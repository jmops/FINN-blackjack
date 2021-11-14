/**
 * This application lets a player play against the dealer in a game of blackjack (21).
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
        game = if(args.isNotEmpty())
            Blackjack(args[0])
         else
            Blackjack("")

        game.playGame()
    }
    catch (e : Exception){
        println("Something went wrong:\n$e")
    }



}