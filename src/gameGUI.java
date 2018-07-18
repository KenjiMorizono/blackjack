import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
enum BetValue{
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100);

    private int betValue;

    BetValue(int value){
        this.betValue = value;

    }
    public int getValue(){

        return betValue;
    }

}
public class gameGUI extends JFrame {
    private int xPos; // Default x position of game window
    private int yPos; // Default y position of game window
    private final int width = 800; // Desired width dimension of game window
    private final int height = 600; // Desired height dimension of game window
    private Toolkit tools;
    private Dimension dim;

    private JPanel buttonPanel = new JPanel();
    private JPanel betPanel = new JPanel();
    private JPanel playButtonPanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JPanel playerArea = new JPanel();
    private JPanel dealerArea = new JPanel();

    private JLabel betAreaText = new JLabel("Bet Amount:");

    /*
     Did not create these buttons using createButton() because their layout is properly spaced and formatted
     While the alignment for the betting buttons used need rigid areas and their sizes clearly need to be equalized
      */
    private JButton hitButton = new JButton("Hit");
    private JButton standButton = new JButton("Stand");
    private JButton doubleButton = new JButton("Double");
    private JButton splitButton = new JButton("Split");

    private final Border blackBorder = BorderFactory.createLineBorder(Color.black);



    public gameGUI(){
        this.setSize(width, height);
        this.tools = Toolkit.getDefaultToolkit();
        this.dim = tools.getScreenSize();
        this.setLayout(new BorderLayout());
        displayPanel.setLayout(new FlowLayout());
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));

        xPos = (dim.width / 2) - (this.getWidth() / 2);
        yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setResizable(false);
        this.setLocation(xPos, yPos);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setVisible(true);

        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);
        this.add(displayPanel, BorderLayout.EAST);
        displayPanel.add(playerArea);
        displayPanel.add(dealerArea);
        playerArea.setBorder(blackBorder);
        dealerArea.setBorder(blackBorder);
        betPanel.add(betAreaText, BorderLayout.NORTH);
        this.add(betPanel, BorderLayout.WEST);
        for (int i = 0; i < BetValue.values().length; i++){
            betPanel.add(createButton("$" + Integer.toString(BetValue.values()[i].getValue()), new Dimension(75,30)));
            betPanel.add(Box.createRigidArea(new Dimension(0,22)));

        }


        playerArea.setPreferredSize(new Dimension(width/ 2 - 55, height / 2));
        dealerArea.setPreferredSize(new Dimension(width / 2 - 55, height / 2));

    }
    private JButton createButton(String text, Dimension size){
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        return button;
    }

}