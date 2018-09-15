import javax.swing.*;
import java.util.ArrayList;
public class Player {
    private Hand playerHand;
    private Hand splitHand;
    private int playerMoney;
    private boolean isDealer;
    private boolean splitHandActive;

    public Player(boolean isDealer, Deck gameDeck){
        this.isDealer = isDealer;
        playerHand = new Hand();
        splitHand = new Hand();
        splitHandActive = false;

        if (!(isDealer)){
            playerMoney = 100;
            playerHand.initHandPlayer(gameDeck);
        }
        else {
            // Dealer should not have use any methods that have to do with money count
            playerMoney = 0;
            playerHand.initHandDealer(gameDeck);
        }
    }
    public void addPlayerMoney(int moneyIncrease){
        this.playerMoney += moneyIncrease;
    }

    public void removePlayerMoney(int moneyDecrease){
        this.playerMoney -= moneyDecrease;

    }

    /*
    These methods were moved over from where they were in start as I realized that the Player should be its own class
    in itself. However these methods are redundant in cases such as drawCard where I just call the drawCard() method in
    the Hand class, I am wondering if this should be the case or if I am missing something that could be done to reduce
    the number of methods in my program.
     */


    public int getPlayerMoney(){
        return playerMoney;
    }

    public void printHand(){
        playerHand.printHand();
    }

    public void printSplitHand(){
        splitHand.printHand();

    }

    public int getHandValue(){
        return playerHand.getHandValue();
    }

    public ArrayList<Card> getHandArray(){

        return playerHand.getHand();
    }

    public void createSplitHand(){
        Card splitCard = playerHand.getHand().get(1);
        playerHand.getHand().remove(1);
        splitHand.getHand().add(0, splitCard);

    }

    public ArrayList<Card> getSplitHandArray(){

        return splitHand.getHand();
    }

    public int getSplitHandValue(){
        return splitHand.getHandValue();

    }

    public void resetHandPlayer(Deck gameDeck){
        playerHand = new Hand();
        playerHand.initHandPlayer(gameDeck);

    }

    public void resetSplitHandPlayer(Deck gameDeck){
        splitHand = new Hand();
    }

    public void resetHandDealer(Deck gameDeck){
        playerHand = new Hand();
        playerHand.initHandDealer(gameDeck);

    }

    public Card drawCard(Deck gameDeck){
        Card drawnCard = playerHand.drawCard(gameDeck);

        return drawnCard;
    }

    public Card drawCardSplitHand(Deck gameDeck){
        Card drawnCard = splitHand.drawCard(gameDeck);

        return drawnCard;
    }

    public void calculateHandValue(){
        playerHand.calculateHandVal();

    }

    public void calculateSplitHandValue(){
        splitHand.calculateHandVal();

    }

    public boolean handIsSplittable(){
        //Looks pretty bad in my opinion, can be improved
        if (playerHand.getHand().get(0).getCardName().equals(playerHand.getHand().get(1).getCardName())){

            return true;
        }
        return false;
    }

    public void setDebugHandPlayer(){ // TODO: REMOVE [DEBUG HAND FOR PLAYER SPECIFIC SITUATION]
        playerHand.getHand().set(0, new Card(Value.Two, Suit.Spades));
        playerHand.getHand().set(1, new Card(Value.Two, Suit.Hearts));
        playerHand.getHand().add(new Card(Value.Six, Suit.Diamonds));
        playerHand.getHand().add(new Card(Value.Ten, Suit.Diamonds));

    }

    public void setSplitDebugHandPlayer(){ // TODO: REMOVE [DEBUG HAND FOR SPLITTED PLAYER SPECIFIC SITUATION]
        playerHand.getHand().set(0, new Card(Value.Ace, Suit.Hearts));
        playerHand.getHand().set(1, new Card(Value.Ace, Suit.Clubs));

    }

    public void setDebugHandDealer(){ // TODO: REMOVE [DEBUG HAND FOR DEALER SPECIFIC SITUATION]
        playerHand.getHand().set(0, new Card(Value.Two, Suit.Spades));
        playerHand.getHand().set(1, new Card(Value.Two, Suit.Hearts));
        playerHand.getHand().add(new Card(Value.Six, Suit.Diamonds));
        playerHand.getHand().add(new Card(Value.Ten, Suit.Diamonds));

    }
}
