/**
 * This application lets a player play against the dealer in a game of blackjack (21).
 *
 * @author Jørgen Mo Opsahl
 */

fun main(args : Array<String>){
    var game : Blackjack

    try{
        if(args.isNotEmpty()){
            game = Blackjack(args[0])
        }
        else{
            game = Blackjack("")
        }
    }
    catch (e : Exception){
        println(e)
    }

}