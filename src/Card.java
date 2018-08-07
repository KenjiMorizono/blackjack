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

    public int getIntCardVal(){
        int enumVal = this.cardValue.ordinal();

        switch (enumVal){
            case 0: // ACE
                return 11;
            case 1: // TWO
                return 2;
            case 2: // THREE
                return 3;
            case 3: // FOUR
                return 4;
            case 4: // FIVE
                return 5;
            case 5: // SIX
                return 6;
            case 6: // SEVEN
                return 7;
            case 7: // EIGHT
                return 8;
            case 8: // NINE
                return 9;
            case 9: // TEN
                return 10;
            case 10: // JACK
                return 10;
            case 11: // QUEEN
                return 10;
            case 12: // KING
                return 10;
            default:
                return -1;
        }
    }

    public String getCardName(){

        return this.cardValue.name();
    }

    public Suit getCardSuit(){

        return cardSuit;
    }

    public void setFaceDown(){
        this.isFaceDown = true;
    }

    public void flipCard(){
        this.isFaceDown = false;

    }
}
