import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Start{
    private Player player1;
    private Player dealer;
    private Deck blackJackDeck;
    private int betAmount;
    private boolean splitHandExists;
    private boolean mainHandDone;
    private boolean splitHandDone;
    /*
    Using a hand state map because I originally had a multitude of if else statements in the gameGUI.java class to check
    if a player won, loss, had a draw, or busted with regard to the dealer. The addition of function for split hand made
    the readability and bug checking worse so I implemented this. However I realized that this was because I tried to
    have separate messages depending on which hand the player won, lost, tied, or busted within only one dialogue
    window But as I had already spent the time to learn and implement maps, I decided to keep the new implementation for
    future reference.
     */
    private Map<String, String> handState;

    public Start(){
        blackJackDeck = new Deck();
        player1 = new Player(false, blackJackDeck);
        dealer = new Player(true, blackJackDeck);
        betAmount = 1;
        splitHandExists = false;
        mainHandDone = false;
        splitHandDone = false;
        handState = new HashMap<>();

        gameGUI gameWindow = new gameGUI(this);

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

    public int getPlayerMoney(){

        return player1.getPlayerMoney();
    }

    public void addBetAmount(int betIncrease){

        this.betAmount += betIncrease;
    }

    public void doubleBetAmount(){

        this.betAmount *= 2;
    }

    public void resetBetAmount(){

        this.betAmount = 1;
    }

    public int getBetAmount(){

        return betAmount;
    }

    public ArrayList<Card> getPlayerHand(){

        return player1.getHandArray();
    }

    public ArrayList<Card> getPlayerSplitHand(){

        return player1.getSplitHandArray();
    }

    public ArrayList<Card> getDealerHand(){

        return dealer.getHandArray();
    }

    public void setMainHandDone(boolean doneBool){

        this.mainHandDone = doneBool;
    }

    public boolean isMainHandDone(){

        return mainHandDone;
    }

    public void setSplitHandDone(boolean doneBool){

        this.splitHandDone = doneBool;
    }

    public boolean isSplitHandDone(){

        return splitHandDone;
    }

    public boolean splitHandExists(){

        return splitHandExists;
    }

    public Card hit(){
        Card drawnCard = player1.drawCard(blackJackDeck);
        return drawnCard;
    }

    public Card hitSplitHand(){
        Card drawnCard = player1.drawCardSplitHand(blackJackDeck);
        return drawnCard;
    }

    public void stand(){
        dealerPlay();
        updateHandState();
    }

    public void updateHandState(){
        /*
        Key P1 = Player main hand state
        Key P2 = Player split hand state
        Key D1 = Dealer hand state (needed only to know if dealer busted.)
         */

        // Checks for if any hand busted
        if (checkHandLossPlayer()){
            handState.put("P1", "B");

        }
        if (checkSplitHandLossPlayer()){
            handState.put("P2", "B");

        }
        if (checkHandLossDealer()){
            handState.put("D1", "B");

        }

        if (handState.get("P1") == null){
            if (handState.get("D1") != null) {
                if (handState.get("D1").equals("B")) { // Redundant probably, but helps me when coming back to code
                    handState.put("P1", "W");
                }
            }
            else if (isPlayerGreaterThanDealer()){
                handState.put("P1", "W");
            }
            else if (checkGameDraw()){
                handState.put("P1", "D");
            }
            else if (!isPlayerGreaterThanDealer()){
                handState.put("P1", "L");
            }

        }

        if (splitHandExists()){
            if (handState.get("P2") == null){
                if (handState.get("D1") != null) {
                    if (handState.get("D1").equals("B")) { // Redundant probably, but helps me when coming back to code
                        handState.put("P2", "W");
                    }
                }
                else if (isSplitHandPlayerGreaterThanDealer()){
                    handState.put("P2", "W");
                }
                else if (checkSplitGameDraw()){
                    handState.put("P2", "D");
                }
                else if (!isSplitHandPlayerGreaterThanDealer()){
                    handState.put("P2", "L");
                }
            }

        }
    }

    public String getMainHandState(){
        if (handState.get("P1") == null){
            System.out.println("Main hand state Null");
            return "N";
        }
        return handState.get("P1");
    }

    public String getSplitHandState(){
        if (handState.get("P2") == null){
            System.out.println("Main hand state Null");
            return "N";
        }
        return handState.get("P2");
    }

    public String getDealerHandState(){
        if (handState.get("D1") == null){

            return "N";
        }
        return handState.get("D1");
    }


    public boolean checkSplittable(){
        if (player1.handIsSplittable()){
            splitHandDone = false;
            return true;
        }
        splitHandDone = true;
        return false;
    }

    public void splitPlayerHand(){
        player1.createSplitHand();

        this.splitHandExists = true;
    }

    public void dealerPlay(){
        // Dealer will stand if the hand value is more than 16
        while (dealer.getHandValue() < 16){
            dealer.drawCard(blackJackDeck);
            dealer.calculateHandValue();

        }
    }

    public void playerSplitWin(){ // Player gains bet value, same as playerWin(), only used to distinguish for debug
        System.out.println("SPLIT HAND WIN");
        player1.addPlayerMoney(betAmount);
        player1.printSplitHand();
        dealer.printHand();
        System.out.println("Done");

    }

    public void playerWin(){
        System.out.println("REGULAR WIN");
        player1.addPlayerMoney(betAmount);
        player1.printHand();
        dealer.printHand();
        System.out.println("Done");

    }

    public void playerBlackJackWin(){
        System.out.println("BLACK JACK WIN");
        player1.addPlayerMoney(betAmount * 2);
        player1.printHand();
        dealer.printHand();
        System.out.println("Done");
    }

    public void playerSplitLoss(){ // Player loses bet value, same as playerLoss(), only used to distinguish for debug
        System.out.println("SPLIT HAND LOSS");
        player1.removePlayerMoney(betAmount);
        player1.printSplitHand();
        dealer.printHand();
        System.out.println("Done");
    }

    public void playerLoss(){
        System.out.println("LOSS");
        player1.removePlayerMoney(betAmount);
        player1.printHand();
        dealer.printHand();
        System.out.println("Done");

    }

    public boolean checkSplitGameDraw(){ // Draw check for split hand please give tips on naming if you see this
        if (player1.getSplitHandValue() == dealer.getHandValue()){
            System.out.println("SPLIT HAND DRAW");
            player1.printHand();
            dealer.printHand();
            System.out.println("Done");
            return true;
        }
        return false;
    }

    public boolean checkGameDraw(){
        if (player1.getHandValue() == dealer.getHandValue()){
            System.out.println("DRAW");
            player1.printHand();
            dealer.printHand();
            System.out.println("Done");

            return true;
        }
        return false;
    }


    public void resetHandState(){
        handState.clear();

    }

    public void resetPlayerState(){
        blackJackDeck = new Deck();
        player1.resetHandPlayer(blackJackDeck);
        player1.resetSplitHandPlayer(blackJackDeck);
        dealer.resetHandDealer(blackJackDeck);
        resetHandState();
        splitHandExists = false;
        mainHandDone = false;
        splitHandDone = false;
    }

    public boolean isPlayerGreaterThanDealer(){
        if (player1.getHandValue() > dealer.getHandValue()){

           return true;
        }

        return false;
    }

    public boolean isSplitHandPlayerGreaterThanDealer(){
        if (player1.getSplitHandValue() > dealer.getHandValue()){

            return true;
        }
        return false;
    }

    public boolean checkHandLossPlayer(){
        if (player1.getHandValue() > 21){

            return true;
        }
        return false;
    }

    public boolean checkSplitHandLossPlayer(){
        if (player1.getSplitHandValue() > 21){

            return true;
        }
        return false;
    }

    public boolean checkHandLossDealer(){
        if (dealer.getHandValue() > 21){

            return true;
        }
        return false;
    }

    public boolean checkGameLoss(){ // Player money hits 0
        if (player1.getPlayerMoney() == 0){
            return true;
        }

        return false;
    }

    public boolean checkPlayerHand21(){ // Used to check if player has reached 21
        if (player1.getHandValue() == 21){

            return true;
        }
        return false;
    }

}
