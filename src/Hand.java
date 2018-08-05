import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private boolean isDealer;
    private int handVal;
    private int aceCount;
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Hand(boolean isDealer){
        this.isDealer = isDealer;
        this.handVal = 0;
        this.aceCount = 0;

    }

    public void drawCard(Deck gameDeck){ // Add card drawn from deck, should be using deck.drawCard()
        Card drawnCard;
        drawnCard = gameDeck.drawCard();
        this.hand.add(drawnCard);
        this.calculateHandVal();

    }

    public void initHand(Deck gameDeck){
        this.hand.add(gameDeck.drawCard());
        this.hand.add(gameDeck.drawCard());
        this.calculateHandVal();


    }

    public void printHand(){ // For debugging
        for (int i = 0; i < hand.size(); i++){
            System.out.println("Computer Card Value: " + hand.get(i).getIntCardVal());
            System.out.print(hand.get(i).getCardName() + " of ");
            System.out.println(hand.get(i).getCardSuit());
        }
        System.out.println("Hand Value: " + handVal);
        System.out.println();


    }

    public void calculateHandVal(){
        /*
        Method will set handVal variable based on cards in the hand
        I am using a seperate method instead of calculating the hand value at the start
        and the hand value as a card is added to account for aces, though this might be a bad reason and there
        may be a solution I am not seeing (most likely as recalculating every-time is costly), if so I am sorry
         */
        this.setAceCount();
        for (int i = 0; i < hand.size(); i++){
            handVal += hand.get(i).getIntCardVal();

        }
        if (handVal > 21 && aceCount > 0){
            for (int i = 0; i < aceCount; i++){
                handVal -= 10;

            }

        }

    }

    public void setAceCount(){
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).getCardName().equals("Ace")){
                aceCount += 1;

            }

        }

    }

    public int getHandValue(){

        return handVal;
    }

}
