public class Player {
    private Hand playerHand;
    private Hand splitHand;
    private int playerMoney;
    private boolean isDealer;

    public Player(boolean isDealer, Deck gameDeck){
        this.isDealer = isDealer;
        playerHand = new Hand();
        splitHand = new Hand();

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

    public int getHandValue(){
        return playerHand.getHandValue();
    }

    public void drawCard(Deck gameDeck){
        playerHand.drawCard(gameDeck);

    }

}
