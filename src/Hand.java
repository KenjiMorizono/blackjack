import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private int handVal;
    private int aceCount;
    private ArrayList<Card> hand;

    public Hand(){
        this.handVal = 0;
        this.aceCount = 0;
        hand = new ArrayList<Card>();
    }

    public void drawCard(Deck gameDeck){
        this.hand.add(gameDeck.drawCard());
        this.calculateHandVal();

    }

    public void initHandPlayer(Deck gameDeck){
        this.hand.add(gameDeck.drawCard());
        this.hand.add(gameDeck.drawCard());
        this.calculateHandVal();

    }

    public void initHandDealer(Deck gameDeck){
        this.hand.add(gameDeck.drawCard());
        this.hand.get(0).setFaceDown();
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
        I am using a separate method instead of calculating the hand value at the start
        and the hand value as a card is added to account for aces, though this might be a bad reason and there
        may be a solution I am not seeing (most likely as recalculating every-time is costly), if so I am sorry
         */
        int cardVal = 0;

        this.setAceCount();
        for (int i = 0; i < hand.size(); i++){
            cardVal += hand.get(i).getIntCardVal();

        }
        if (cardVal > 21 && aceCount > 0){
            for (int i = 0; i < aceCount; i++){
                cardVal -= 10;

            }

        }

        this.handVal = cardVal;
    }

    public void setAceCount(){
        int numAces = 0;

        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).getCardName().equals("Ace")){
                numAces += 1;

            }

        }
        this.aceCount = numAces;
    }

    public int getHandValue(){

        return handVal;
    }

}
