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

    public void addCard(Card drawnCard){ // Add card drawn from deck, should be using deck.drawCard()
        this.hand.add(drawnCard);

    }

    public void initHand(ArrayList<Card> deck){
        this.hand.add(deck.get(0));
        this.handVal += deck.get(0).getCardValue();
        deck.remove(0);
        this.hand.add(deck.get(0));
        this.handVal += deck.get(0).getCardValue();
        deck.remove(0);

    }

    public void printHand(){ // For debug because text should go to GUI until I add images.
        for (int i = 0; i < hand.size(); i++){
            System.out.print(hand.get(i).getCardValue() + " of ");
            System.out.println(hand.get(i).getCardSuit());
        }
        System.out.println();

    }

    public int getHandValue(){

        return handVal;
    }

}
