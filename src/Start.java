public class Start{
    private Player player1;
    private Player dealer;
    private Deck blackJackDeck;
    private int betAmount;

    public Start(){
        blackJackDeck = new Deck();
        player1 = new Player(false, blackJackDeck);
        dealer = new Player(true, blackJackDeck);
        betAmount = 1;

        gameGUI gameWindow = new gameGUI(this);

        player1.printHand();
        dealer.printHand();
    }

    public static void main(String[] args){
        new Start();

    }

    public boolean enoughBetMoney(int bet){
        if (betAmount + bet <= player1.getPlayerMoney()){

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

        return player1.getHandValue();
    }

    public void hit(){
        player1.drawCard(blackJackDeck);
        player1.printHand();

    }

}
