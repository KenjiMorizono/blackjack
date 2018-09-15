import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class gameGUI extends JFrame {
    private Start main;
    private int xPos; // Default x position of game window
    private int yPos; // Default y position of game window
    private final int width = 1080; // Desired width dimension of game window
    private final int height = 800; // Desired height dimension of game window
    private Toolkit tools;
    private Dimension dim;

    //--PANELS--
    private JPanel buttonPanel = new JPanel();
    private JPanel betPanel = new JPanel();
    private JPanel playButtonPanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JPanel playerArea = new JPanel();
    private JPanel playerSplitArea = new JPanel();
    private JPanel dealerArea = new JPanel();
    //--LABELS--
    private JLabel betAreaText = new JLabel("Add Bet:");
    private JLabel moneyLabel = new JLabel("Money: $100");
    private JLabel betAmountLabel = new JLabel("Bet Amount: $1");
    //--BUTTONS--
    private JButton playButton = new JButton("Play");
    private JButton hitButton = new JButton("Hit");
    private JButton standButton = new JButton("Stand");
    private JButton doubleButton = new JButton("Double");
    private JButton splitButton = new JButton("Split");
    private JButton betButton1 = new JButton("$1");
    private JButton betButton5 = new JButton("$5");
    private JButton betButton10 = new JButton("$10");
    private JButton betButton20 = new JButton("$20");
    private JButton betButton50 = new JButton("$50");
    private JButton betButton100 = new JButton("$100");
    private JButton resetBetButton = new JButton("Reset Bet");
    //--LISTENERS--
    private ListenForActionButton actionButtonListener = new ListenForActionButton();
    private ListenForBetButton betButtonListener = new ListenForBetButton();
    //--DIMENSIONS--
    private final Dimension playArea = new Dimension (width / 2 - 60, height / 4);
    private final Dimension infoBox = new Dimension(60, 40);
    private final Dimension rigidAreaDim = new Dimension(0, 20);
    private final Dimension betButtonSize = new Dimension (70, 25);
    private final Dimension inputButtonSize = new Dimension(75, 25);
    private final Dimension cardLabelSize = new Dimension(60, 83);
    //--OTHER VALUES--
    private final Border blackBorder = BorderFactory.createLineBorder(Color.black);
    private final Border noBorder = BorderFactory.createEmptyBorder();
    private final String betLabelText = "Bet Amount: $";




    public gameGUI(Start parentGame){
        main = parentGame;
        this.setSize(width, height);
        this.tools = Toolkit.getDefaultToolkit();
        this.dim = tools.getScreenSize();
        this.setLayout(new BorderLayout());
        displayPanel.setLayout(new GridBagLayout());
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));

        xPos = (dim.width / 2) - (this.getWidth() / 2);
        yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setVisible(true);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4, 4 , 4, 4);


        playButton.addActionListener(actionButtonListener);

        // Panel for player input buttons
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);
        buttonPanel.add(playButton);
        inputButtonSetup(hitButton);
        inputButtonSetup(standButton);
        inputButtonSetup(doubleButton);
        inputButtonSetup(splitButton);

        //Panel for information display to player
        this.add(displayPanel, BorderLayout.CENTER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        displayPanel.add(playerArea, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        displayPanel.add(playerSplitArea, gridBagConstraints);
        playerSplitArea.setVisible(false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        displayPanel.add(dealerArea, gridBagConstraints);
        playerArea.setBorder(blackBorder);
        playerSplitArea.setBorder(blackBorder);
        dealerArea.setBorder(blackBorder);
        playerArea.setPreferredSize(playArea);
        playerArea.setMinimumSize(playArea);
        playerSplitArea.setPreferredSize(playArea);
        playerSplitArea.setMinimumSize(playArea);
        dealerArea.setPreferredSize(playArea);
        dealerArea.setMinimumSize(playArea);

        // Panel for bet amount interactions for player
        this.add(betPanel, BorderLayout.WEST);
        betPanel.add(betAreaText, BorderLayout.NORTH);
        betButtonSetup(betButton1);
        betButtonSetup(betButton5);
        betButtonSetup(betButton10);
        betButtonSetup(betButton20);
        betButtonSetup(betButton50);
        betButtonSetup(betButton100);

        betPanel.add(betButton1);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(betButton5);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(betButton10);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(betButton20);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(betButton50);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(betButton100);
        betPanel.add(Box.createRigidArea(rigidAreaDim));


        betPanel.add(moneyLabel);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betAmountLabel.setMinimumSize(new Dimension(150, 25));
        betAmountLabel.setPreferredSize(new Dimension(150, 25));
        betPanel.add(betAmountLabel);
        betPanel.add(Box.createRigidArea(rigidAreaDim));
        betPanel.add(resetBetButton);
        resetBetButton.addActionListener(actionButtonListener);

        // Initial visibility settings
        this.pack();
        gameSetup();


    }

    public void betButtonSetup(JButton button){ // Used to keep bet button size consistent and add listener
        button.setPreferredSize(betButtonSize);
        button.setMaximumSize(betButtonSize);
        button.setMinimumSize(betButtonSize);
        button.addActionListener(betButtonListener);
    }

    public void inputButtonSetup(JButton button){
        button.setPreferredSize(inputButtonSize);
        button.setMaximumSize(inputButtonSize);
        button.setMinimumSize(inputButtonSize);
        button.addActionListener(actionButtonListener);

    }


    public void hideInteractionButtons(){
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
    }

    public void hideBetArea(){
        // Used after player receives cards and show the interaction buttons
        betAreaText.setVisible(false);
        moneyLabel.setVisible(false);
        betAmountLabel.setVisible(false);
        betButton1.setVisible(false);
        betButton5.setVisible(false);
        betButton10.setVisible(false);
        betButton20.setVisible(false);
        betButton50.setVisible(false);
        betButton100.setVisible(false);
        resetBetButton.setVisible(false);

    }

    public void showBetArea(){
        betAreaText.setVisible(true);
        moneyLabel.setVisible(true);
        betAmountLabel.setVisible(true);
        betButton1.setVisible(true);
        betButton5.setVisible(true);
        betButton10.setVisible(true);
        betButton20.setVisible(true);
        betButton50.setVisible(true);
        betButton100.setVisible(true);
        resetBetButton.setVisible(true);

    }

    public void updateBetAmountText(int betAmount){
        betAmountLabel.setText(betLabelText + betAmount);

    }

    public void updateMoneyAmountText(){
        moneyLabel.setText("Money: $" + main.getPlayerMoney());

    }

    public void addCardImagePlayer(String imageName){
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(cardLabelSize);
        imageLabel.setMinimumSize(cardLabelSize);
        imageLabel.setMaximumSize(cardLabelSize);
        imageLabel.setSize(cardLabelSize);

        BufferedImage imageReader = null;
        try {
            imageReader = ImageIO.read(new File("./src/img/" + imageName));
        }
        catch (IOException ioException) {
            System.out.println("IO Exception from getting card image from path");
            System.out.println("Path is: ./src/img" + imageName);
            System.out.println("Error is from trying to get the image for player");
            System.out.println("");
            ioException.printStackTrace();

        }
        Image scaledCard = imageReader.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);


        ImageIcon cardImage = new ImageIcon(scaledCard);
        imageLabel.setIcon(cardImage);

        playerArea.add(imageLabel);
        playerArea.revalidate();

    }

    public void addCardImageSplitArea(String imageName){
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(cardLabelSize);
        imageLabel.setMinimumSize(cardLabelSize);
        imageLabel.setMaximumSize(cardLabelSize);
        imageLabel.setSize(cardLabelSize);

        BufferedImage imageReader = null;
        try {
            imageReader = ImageIO.read(new File("./src/img/" + imageName));
        }
        catch (IOException ioException) {
            System.out.println("IO Exception from getting card image from path");
            System.out.println("Path is: ./src/img" + imageName);
            System.out.println("Error is from trying to get the image for split area");
            System.out.println("");
            ioException.printStackTrace();

        }
        Image scaledCard = imageReader.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);


        ImageIcon cardImage = new ImageIcon(scaledCard);
        imageLabel.setIcon(cardImage);

        playerSplitArea.add(imageLabel);
        playerSplitArea.revalidate();

    }

    public void addCardImageDealer(String imageName){
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(cardLabelSize);
        imageLabel.setMinimumSize(cardLabelSize);
        imageLabel.setMaximumSize(cardLabelSize);
        imageLabel.setSize(cardLabelSize);

        BufferedImage imageReader = null;
        try {
            imageReader = ImageIO.read(new File("./src/img/" + imageName));
        }
        catch (IOException ioException) {
            System.out.println("IO Exception from getting card image from path");
            System.out.println("Path is: ./src/img" + imageName);
            System.out.println("Error is from trying to get the image for dealer");
            System.out.println("");
            ioException.printStackTrace();

        }
        Image scaledCard = imageReader.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);


        ImageIcon cardImage = new ImageIcon(scaledCard);
        imageLabel.setIcon(cardImage);

        dealerArea.add(imageLabel);
        dealerArea.revalidate();

    }

    public void initCardDisplayPlayer(){
        ArrayList<Card> playerHand = main.getPlayerHand();
        String imageName;
        for (int i = 0; i < playerHand.size(); i++){
            Card newCard = playerHand.get(i);
            imageName = getImageName(newCard);
            addCardImagePlayer(imageName);
        }

    }

    public void initCardDisplaySplitPlayer(){
        ArrayList<Card> playerHand = main.getPlayerSplitHand();
        String imageName;
        for (int i = 0; i < playerHand.size(); i++){
            Card newCard = playerHand.get(i);
            imageName = getImageName(newCard);
            addCardImageSplitArea(imageName);
        }
    }

    public void initCardDisplayDealer(){
        ArrayList<Card> dealerHand = main.getDealerHand();
        String imageName;
        for (int i = 0; i < dealerHand.size() - 1; i++){
            Card newCard = dealerHand.get(i);
            imageName = getImageName(newCard);
            addCardImageDealer(imageName);
        }

    }

    public void updateCardDisplayDealer(){
        /*
        I am creating this because I was not sure how to handle updating the dealer display for cards through the
        method main.stand(). I was also not sure how to make it so the update could be called for each iteration
        through the gameGUI class (i.e dealer draws, update display, and so on. As a result I will be adding all the new
        cards the dealer draws (if any) because it can be assumed that the dealer will have 2 cards at the start of the
        game. If I ever come back to this program once it is finished, this will be the first thing I will be improving
         */
        ArrayList<Card> dealerHand = main.getDealerHand();
        String imageName;
        for (int i = 1; i < dealerHand.size(); i++){
            Card newCard = dealerHand.get(i);
            imageName = getImageName(newCard);
            addCardImageDealer(imageName);
        }

    }

    public String getImageName(Card card){ // Returns image name in string following format val_of_suit.png
        String fileName;
        if (card.getIntCardVal() >= 2 && card.getIntCardVal() < 10){
            fileName = (card.getIntCardVal() + "_of_" + card.getCardSuit().toString().toLowerCase() + ".png");

        }
        else if (card.getCardName().equals("Ten")){
            fileName = (card.getIntCardVal() + "_of_" + card.getCardSuit().toString().toLowerCase() + ".png");

        }
        else {
            fileName = (card.getCardName().toLowerCase() + "_of_" + card.getCardSuit().toString().toLowerCase() + ".png");

        }
        return fileName;
    }

    public void playMenuSetup(){
        playButton.setVisible(false);
        hideBetArea();
        hitButton.setVisible(true);
        standButton.setVisible(true);
        doubleButton.setVisible(true);

    }

    public void gameSetup(){
        playButton.setVisible(true);
        showBetArea();
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);

    }

    public void resetGameState(){ // Reset the game state for another game
        main.resetPlayerState();
        main.resetBetAmount();
        playerArea.removeAll();
        playerSplitArea.removeAll();
        dealerArea.removeAll();
        playerArea.setBorder(blackBorder);
        playerSplitArea.setBorder(noBorder);
        playerSplitArea.setVisible(false);
        updateMoneyAmountText();
        updateBetAmountText(main.getBetAmount());
        gameSetup();
    }


    private class ListenForActionButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                playMenuSetup();
                initCardDisplayPlayer();
                initCardDisplayDealer();
                main.updateHandState();
                if (main.checkPlayerHand21() && main.getMainHandState().equals("W")){
                    if (main.getMainHandState().equals("D")){
                        // Player && dealer hand val == 21
                        updateCardDisplayDealer();
                        hideInteractionButtons();
                        JOptionPane.showMessageDialog(null, "It's a draw.");
                        resetGameState();
                    }
                    else {
                        // Player hand val == 21 and dealer hand val < 21
                        updateCardDisplayDealer();
                        main.playerBlackJackWin();
                        hideInteractionButtons();
                        JOptionPane.showMessageDialog(null, "You won, Blackjack win.");
                        resetGameState();
                    }
                }
                else if (main.checkSplittable()){
                    splitButton.setVisible(true);
                }
            }
            else if (e.getSource() == hitButton){
                splitButton.setVisible(false);
                doubleButton.setVisible(false);
                if (main.splitHandExists()){ // Has 2 hands, always starts with main hand
                    if (!main.isMainHandDone()){
                        Card drawnCard = main.hit();
                        addCardImagePlayer(getImageName(drawnCard));
                        main.updateHandState();
                        if (main.getMainHandState().equals("B")){
                            main.setMainHandDone(true);
                            playerArea.setBorder(noBorder);
                            playerSplitArea.setBorder(blackBorder);
                        }
                    }
                    else { // Playing split hand
                        Card drawnCard = main.hitSplitHand();
                        addCardImageSplitArea(getImageName(drawnCard));
                        main.updateHandState();
                        if (main.getSplitHandState().equals("B")){
                            standButton.doClick();
                        }
                    }
                }
                else { // Only has main hand
                    Card drawnCard = main.hit();
                    addCardImagePlayer(getImageName(drawnCard));
                    main.updateHandState();
                    if (main.getMainHandState().equals("B")){
                        main.playerLoss();
                        JOptionPane.showMessageDialog(null, "You bust.");
                        resetGameState();
                    }
                }
            }
            else if (e.getSource() == standButton){
                if (main.splitHandExists()){ // Player split hands at start
                    if (!main.isMainHandDone()){
                        main.setMainHandDone(true);
                        playerArea.setBorder(noBorder);
                        playerSplitArea.setBorder(blackBorder);
                    }
                    else {
                        main.resetHandState();
                        main.stand();
                        updateCardDisplayDealer();
                        hideInteractionButtons();
                        if (main.getDealerHandState().equals("B")){
                            switch (main.getMainHandState()){
                               case "B":
                                   main.playerLoss();
                                   JOptionPane.showMessageDialog(null, "You busted with your main hand");
                                   break;
                               case "N":
                                   System.out.println("Null main hand state");
                                   break;
                               default:
                                   main.playerWin();
                                   JOptionPane.showMessageDialog(null, "You won with your main hand, dealer bust!");
                                   break;
                           }
                           switch (main.getSplitHandState()){
                               case "B":
                                   main.playerSplitLoss();
                                   JOptionPane.showMessageDialog(null, "You busted with your split hand");
                                   break;
                               case "N":
                                   System.out.println("Null split hand state");
                                   break;
                               default:
                                   main.playerSplitWin();
                                   JOptionPane.showMessageDialog(null, "You won with your split hand, dealer bust!");
                                   break;
                           }
                           resetGameState();
                        }
                        else {
                            switch (main.getMainHandState()){
                               case "W":
                                   main.playerWin();
                                   JOptionPane.showMessageDialog(null, "You won with your main hand!");
                                   break;
                               case "D":
                                   JOptionPane.showMessageDialog(null, "You tied with your main hand.");
                                   break;
                               case "L":
                                   main.playerLoss();
                                   JOptionPane.showMessageDialog(null, "You lost with your main hand.");
                                   break;
                               case "B":
                                   main.playerLoss();
                                   JOptionPane.showMessageDialog(null, "You busted with your main hand!");
                                   break;
                               case "N":
                                   System.out.println("Null main hand state");
                                   break;
                               default:
                                   System.out.println("ERROR: SWITCH DEFAULT WITH MAIN HAND # STAND LISTENER DEALER !B");
                                   break;
                           }
                           switch (main.getSplitHandState()){
                               case "W":
                                   main.playerSplitWin();
                                   JOptionPane.showMessageDialog(null, "You won with your split hand!");
                                   break;
                               case "D":
                                   JOptionPane.showMessageDialog(null, "You tied with your split hand.");
                                   break;
                               case "L":
                                   main.playerSplitLoss();
                                   JOptionPane.showMessageDialog(null, "You lost with your split hand.");
                                   break;
                               case "B":
                                   main.playerSplitLoss();
                                   JOptionPane.showMessageDialog(null, "You busted with your split hand!");
                                   break;
                               case "N":
                                   System.out.println("Null main hand state");
                                   break;
                               default:
                                   System.out.println("ERROR: SWITCH DEFAULT WITH SPLIT HAND # STAND LISTENER DEALER !B");
                                   break;
                           }
                           resetGameState();
                        }
                    }
                }
                else { // Player has only 1 hand
                    main.resetHandState();
                    main.stand();
                    updateCardDisplayDealer();
                    hideInteractionButtons();
                    if (main.getDealerHandState().equals("B")){
                        main.playerWin();
                        JOptionPane.showMessageDialog(null, "You won, dealer busted!");
                        resetGameState();
                    }
                    else {
                        switch (main.getMainHandState()){
                            case "W":
                                main.playerWin();
                                JOptionPane.showMessageDialog(null, "You won!");
                                resetGameState();
                                break;
                            case "L":
                                main.playerLoss();
                                JOptionPane.showMessageDialog(null, "You lost.");
                                resetGameState();
                                break;
                            case "D":
                                JOptionPane.showMessageDialog(null, "You tied.");
                                resetGameState();
                                break;
                            case "N":
                                break;
                            default:
                                System.out.println("ERROR: SWITCH DEFAULT WITH ONLY 1 HAND # STAND LISTENER");
                                break;
                        }
                    }
                }
            }
            else if (e.getSource() == doubleButton){ // Can only double when playing 1 hand atm
                main.doubleBetAmount();
                Card drawnCard = main.hit();
                addCardImagePlayer(getImageName(drawnCard));
                main.stand();
                updateCardDisplayDealer();
                switch (main.getMainHandState()){
                    case "W":
                        main.playerWin();
                        JOptionPane.showMessageDialog(null, "You won!");
                        resetGameState();
                        break;
                    case "L":
                        main.playerLoss();
                        JOptionPane.showMessageDialog(null, "You lost.");
                        resetGameState();
                        break;
                    case "D":
                        JOptionPane.showMessageDialog(null, "You tied.");
                        resetGameState();
                        break;
                    case "B":
                        main.playerLoss();
                        JOptionPane.showMessageDialog(null, "You bust.");
                        resetGameState();
                        break;
                    case "N":
                        break;
                    default:
                        System.out.println("ERROR: SWITCH DEFAULT WITH ONLY 1 HAND # STAND LISTENER");
                        break;
                }

            }
            else if (e.getSource() == splitButton){
                /*
                Just wanted to note that players won't be allowed to double down after splitting their hand of course
                some places allow the player to double down per hand. So if I ever come back to improve this program,
                while this will be lower priority, I will add an option to enable or disable this. If I do enable this,
                I will need to rewrite how the bet amount is calculated for each split hand and keep track of separate
                bet amount values for win/loss (e.g 1 hand is doubled, the other is not.)
                 */
                main.splitPlayerHand();
                playerArea.removeAll();
                playerSplitArea.removeAll();
                playerSplitArea.setVisible(true);
                playerSplitArea.setBorder(noBorder);
                main.hit();
                main.hitSplitHand();
                initCardDisplayPlayer();
                initCardDisplaySplitPlayer();
                splitButton.setVisible(false);
                doubleButton.setVisible(false);


            }
            else if (e.getSource() == resetBetButton){
                main.resetBetAmount();
                updateBetAmountText(main.getBetAmount());

            }
        }
    }

    private class ListenForBetButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == betButton1){
                if (main.enoughBetMoney(1)){
                    main.addBetAmount(1);
                    updateBetAmountText(main.getBetAmount());

                }

            }
            else if (e.getSource() == betButton5){
                if (main.enoughBetMoney(5)){
                    main.addBetAmount(5);
                    updateBetAmountText(main.getBetAmount());
                }

            }
            else if (e.getSource() == betButton10){
                if (main.enoughBetMoney(10)){
                    main.addBetAmount(10);
                    updateBetAmountText(main.getBetAmount());
                }

            }
            else if (e.getSource() == betButton20){
                if (main.enoughBetMoney(20)){
                    main.addBetAmount(20);
                    updateBetAmountText(main.getBetAmount());
                }


            }
            else if (e.getSource() == betButton50){
                if (main.enoughBetMoney(50)){
                    main.addBetAmount(50);
                    updateBetAmountText(main.getBetAmount());
                }

            }
            else if (e.getSource() == betButton100){
                if (main.enoughBetMoney(100)){
                    main.addBetAmount(100);
                    updateBetAmountText(main.getBetAmount());
                }
            }
        }
    }
}