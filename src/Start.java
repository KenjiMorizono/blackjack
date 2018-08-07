public class Start{
    private Hand playerHand;
    private Hand dealerHand;
    private Deck blackJackDeck;
    private int playerMoney;
    private int betAmount;

    public Start(){
        blackJackDeck = new Deck();
        playerHand = new Hand(false);
        dealerHand = new Hand(true);
        playerMoney = 100;
        betAmount = 1;

        gameGUI gameWindow = new gameGUI(this);
        playerHand.initHand(blackJackDeck);
        dealerHand.initHand(blackJackDeck);

        playerHand.printHand();
        dealerHand.printHand();




    }

    public static void main(String[] args){
        new Start();

    }

    public void addPlayerMoney(int moneyIncrease){
        this.playerMoney += moneyIncrease;

    }

    public int getPlayerMoney(){

        return playerMoney;
    }

    public boolean enoughBetMoney(int bet){
        if (betAmount + bet <= playerMoney){

            return true;
        }

        return false;
    }

    public void addBetAmount(int betIncrease){

        this.betAmount += betIncrease;
    }

    public void resetBetAmount(){

        this.betAmount = 1;
    }

    public int getBetAmount(){

        return betAmount;
    }

    public int getPlayerHandVal(){

        return this.playerHand.getHandValue();
    }



    public void hit(){
        playerHand.drawCard(blackJackDeck);
        playerHand.printHand();


    }


}
