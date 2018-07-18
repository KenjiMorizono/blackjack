import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private boolean isDealer = false;
    private int handVal;
    private int aceCount;
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Hand(boolean isDealer){
        this.isDealer = isDealer;
        this.handVal = 0;
        this.aceCount = 0;

    }

    public void drawCard(Card drawnCard){ // Add card drawn from deck, should be using deck.drawCard()
        this.hand.add(drawnCard);

    }


}
