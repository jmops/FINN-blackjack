# A simplified game of blackjack implemented in Kotlin, based on a task given to me. 

## How the game works:
- Both participants are dealt two cards each initially.
- The cards are checked to see if there is a winner.
- If there is no winner yet, the player starts drawing cards. 
- If the score is 17 or higher, the player stop drawing cards.
- If the player haven't won or lost yet, the dealer starts drawing cards.
- If the dealer has passed the players score or the perfect score, the dealer stop drawing cards.
- A winner is crowned.

## Winning conditions (in order):
### Initial dealing
- If the player got a perfect score (21), the player wins. 
- If both participants are dealt two aces (22), or the dealer got a perfect score, the dealer wins.
### After the initial dealing
- If the player has passed the perfect score (21), the dealer wins. 
- If the dealer has a higher score than the player, but haven't passed the perfect score (21), the dealer wins.
- Otherwise, the player wins.

## Cards
The user can provide their own deck of cards by passing a filename containing the deck as a command line argument. (See cards.txt for format)
A fresh deck of card is created and shuffled if the user did not "bring" their own deck. 

## Score
Numbered cards are their own point value
Jack, Queen and King counts as 10 points
Ace counts as 11 points. 