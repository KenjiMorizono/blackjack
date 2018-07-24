public class Start{
    private Hand playerHand;
    private Hand dealerHand;
    private Deck gameDeck;
    private int playerMoney = 100;

    public Start(){
        gameDeck = new Deck();
        playerHand = new Hand(false);
        dealerHand = new Hand(true);
        gameGUI gameWindow = new gameGUI(this);
        playerHand.initHand(gameDeck.getDeck());
        dealerHand.initHand(gameDeck.getDeck());






    }

    public static void main(String[] args){
        new Start();

    }

    public int getPlayerMoney(){

        return playerMoney;
    }

    public void hit(){


    }



}
