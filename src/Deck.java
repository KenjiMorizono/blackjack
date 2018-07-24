import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){ // Creates and shuffles deck
        this.deck = new ArrayList<Card>();
        for (int i = 0; i < 13; i++){
            Value cardVal = Value.values()[i]; // Gets i value from enumerated value array Value in Card.java
            for (int j = 0; j < 4; j++){
                // Gets j value from enumerated value array Suit in Card.java
                Suit cardSuit = Suit.values()[j];
                Card cardToAdd = new Card(cardVal, cardSuit);
                this.deck.add(cardToAdd);
            }
        }

        Collections.shuffle(this.deck);
    }

    public Card drawCard(){
        Card drawnCard;
        drawnCard = deck.get(0);
        this.deck.remove(0);

        return drawnCard;
    }

    public ArrayList<Card> getDeck(){

        return deck;
    }

}
