# blackjack      
Objective:
  The player tries to beat the dealer in getting as close to 21 as possible without going over

Card Values:
  Numbered cards are their respective values
  Face cards are all worth 10
  An Ace card can be a value of 1 or 11 depending on the total value of the player's hand

Betting:
  The player will start with $500
  The player can increase their bet in increments of $1, $5, $10, $20, $50, and $100

Doubling Down:
  The player will be able to double down if their total hand value is between 9 and 11 inclusively
  The player will be given one more card and will stand on their final hand value (e.g Hand value is 10, player doubles down and        draws a 8 then stands on 18)

Splitting:
  Players will be able to split pairs playing them each as seperate hands
  

Card images are from the Google Code Archive under vector-playing-cards which can be found at:
https://code.google.com/archive/p/vector-playing-cards/


TODO:
  Make use of flip boolean to display flipped card image for dealer (not included in vector google assets likely because the card resources were meant to be used for poker or some similar game, either will find new assets to make use of that have a card back image to prevent inconsistency in art style or I will use the joker as a flipped card image
  
  Delay display of cards for some amount of time to simulate the cards being placed in front of the player
  
  Add sfx perhaps
  
  Definitely improve how the GUI looks, I don't know a lot about making stuff pretty though, might follow tutorials/take a course when I create and finish enough projects
