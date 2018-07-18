enum Value {
    Ace(1),
    Two(2),
    Three (3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(11),
    Queen(12),
    King(13);

    private int cardValue;

    Value(int value){
        this.cardValue = value;

    }

    public int getCardValue(){

        return cardValue;
    }

}

enum Suit {
    Hearts,
    Spades,
    Clubs,
    Diamonds;

}

public class Card {
    private Value cardValue;
    private Suit cardSuit;
    private boolean isFaceDown;

    public Card (Value cardValue, Suit cardSuit){
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
        this.isFaceDown = true;

    }

    public void setCardValue(Value cardValue){
        this.cardValue = cardValue;

    }

    public void setCardSuit(Suit cardSuit){
        this.cardSuit = cardSuit;

    }

    public Value getCardValue(){

        return cardValue;
    }

    public Suit getCardSuit(){

        return cardSuit;
    }

    public void flipCard(){
        this.isFaceDown = false;

    }
}
