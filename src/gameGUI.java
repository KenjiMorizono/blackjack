import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

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
    private JPanel dealerArea = new JPanel();
    //--LABELS--
    private JLabel betAreaText = new JLabel("Add Bet:");
    private JLabel playerText = new JLabel(""); // Used before image implementation
    private JLabel dealerText = new JLabel(""); // Used before image implementation
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
    //--CONSTANTS--
    private final Border blackBorder = BorderFactory.createLineBorder(Color.black);
    private final Dimension playArea = new Dimension (width / 2 - 64, height / 2);
    private final Dimension infoBox = new Dimension(60, 40);
    private final Dimension rigidAreaDim = new Dimension(0, 20);
    private final Dimension betButtonSize = new Dimension (70, 25);
    private final Dimension inputButtonSize = new Dimension(75, 25);
    //--OTHER VALUES--
    private final String betLabelText = "Bet Amount: $";

    public gameGUI(Start parentGame){
        main = parentGame;
        this.setSize(width, height);
        this.tools = Toolkit.getDefaultToolkit();
        this.dim = tools.getScreenSize();
        this.setLayout(new BorderLayout());
        displayPanel.setLayout(new FlowLayout());
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));

        xPos = (dim.width / 2) - (this.getWidth() / 2);
        yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setVisible(true);

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
        displayPanel.add(playerArea, BorderLayout.WEST);
        displayPanel.add(dealerArea, BorderLayout.EAST);
        playerArea.add(playerText);
        playerArea.setBorder(blackBorder);
        dealerArea.setBorder(blackBorder);
        playerArea.setPreferredSize(playArea);
        playerArea.setMinimumSize(playArea);
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
        playMenuSetup();


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

    public void playMenuSetup(){
        betAreaText.setVisible(false);
        moneyLabel.setVisible(false);
        betAmountLabel.setVisible(false);
        betButton1.setVisible(false);
        betButton5.setVisible(false);
        betButton10.setVisible(false);
        betButton20.setVisible(false);
        betButton50.setVisible(false);
        betButton100.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        resetBetButton.setVisible(false);

    }
    public void gameSetup(){
        playButton.setVisible(false);
        betAreaText.setVisible(true);
        moneyLabel.setVisible(true);
        betAmountLabel.setVisible(true);
        betButton1.setVisible(true);
        betButton5.setVisible(true);
        betButton10.setVisible(true);
        betButton20.setVisible(true);
        betButton50.setVisible(true);
        betButton100.setVisible(true);
        hitButton.setVisible(true);
        standButton.setVisible(true);
        doubleButton.setVisible(true);
        splitButton.setVisible(true);
        resetBetButton.setVisible(true);
        this.pack();

    }

    public void updateBetAmountText(int betAmount){
        betAmountLabel.setText(betLabelText + betAmount);
        this.pack();
    }

    private class ListenForActionButton implements ActionListener{
        //TODO
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                gameSetup();

            }
            else if (e.getSource() == hitButton){
                main.hit();

            }
            else if (e.getSource() == standButton){

            }
            else if (e.getSource() == doubleButton){

            }
            else if (e.getSource() == splitButton){

            }
            else if (e.getSource() == resetBetButton){
                main.resetBetAmount();
                updateBetAmountText(main.getBetAmount());


            }

        }
    }

    private class ListenForBetButton implements ActionListener{
        //TODO
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