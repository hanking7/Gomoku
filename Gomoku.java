import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gomoku extends JFrame implements ActionListener {
    private JPanel gameBoard;
    private JPanel winPanel;
    private JLabel winLabel;
    private JButton[][] buttons;
    private String[][] data;
    private Icon myIcon = new ImageIcon("grid.PNG");
    // Tracks the turns of players
    private boolean player1Turn;
    private GridLayout myManager;
    private boolean flag = false;

    /**
     * 
     */
    public Gomoku() {
        // configuring settings for window
        this.setTitle("Let's play Omok!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        myManager = new GridLayout(19, 19);
        gameBoard = new JPanel();
        gameBoard.setLayout(myManager);
        this.add(gameBoard);
        buttons = new JButton[19][19];
        data = new String[19][19];
        for (int row = 0; row < 19; row++) {
            for (int col = 0; col < 19; col++) {
                data[row][col] = "";
                buttons[row][col] = new JButton(myIcon);
                ImageIcon imageIcon = new ImageIcon("grid.PNG"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                buttons[row][col].setIcon(imageIcon);
                buttons[row][col].setPreferredSize(new Dimension(50, 50));
                buttons[row][col].setBorder(BorderFactory.createEmptyBorder());
                buttons[row][col].setActionCommand(row + "-" + col);
                buttons[row][col].addActionListener(this);
                gameBoard.add(buttons[row][col]);
            }

        }

        this.add(gameBoard); // Add the gameboard to JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(950, 950);

    }



    public void restart() {
        ImageIcon imageIcon = new ImageIcon("grid.PNG"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        for (int row = 0; row < 19; row++) {
            for (int col = 0; col < 19; col++) {

                data[row][col] = "";
                
                buttons[row][col].setIcon(imageIcon);
                flag = false;
                player1Turn = false;
            }

        }
        this.setResizable(true);
        this.setSize(950, 950);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent event) {
        Integer row = Integer.parseInt(event.getActionCommand().substring(0, event.getActionCommand().indexOf("-")));
        Integer col = Integer.parseInt(event.getActionCommand().substring(event.getActionCommand().indexOf("-") + 1));

        if (data[row][col].equals("") && flag == false) {
            if (player1Turn) {
                ImageIcon imageIcon = new ImageIcon("image1.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                buttons[row][col].setIcon(imageIcon);
                data[row][col] += "w";
                if (WinCondition.didWin(player1Turn, data)) {
                    imageIcon = new ImageIcon("whitewin"); // load the image to a imageIcon
                    image = imageIcon.getImage(); // transform it
                    newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);
                    if(WinCondition.getWinType()==0){
                        for(int i=0;i<5;i++){
                            buttons[row+i][col].setIcon(imageIcon);
                        }
                    }
                    else if(WinCondition.getWinType()==1){
                        for(int i=0;i<5;i++){
                            buttons[row][col+i].setIcon(imageIcon);
                        }
                    }
                    
                    flag = true;
                }
                player1Turn = !player1Turn;
            } else {
                ImageIcon imageIcon = new ImageIcon("image2.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                buttons[row][col].setIcon(imageIcon);
                data[row][col] += "b";
                if (WinCondition.didWin(player1Turn, data)) {
                    winPanel = new JPanel();
                    winPanel.setLayout(null);

                    winLabel = new JLabel("Black Win!");
                    winLabel.setFont(new Font("Arial", Font.BOLD, 50));
                    winLabel.setBounds(350, 470, 950, 950);
                    winPanel.add(winLabel);
                    this.add(winPanel);
                    winPanel.setVisible(true);
                    winLabel.setVisible(true);
                    this.setResizable(true);
                    this.setSize(950, 1050);
                    this.setResizable(false);
                    flag = true;
                }
                player1Turn = !player1Turn;
            }
        } else if (flag) {
            restart();
        }
    }

    public static void main(String[] args) {
        Gomoku myWindow = new Gomoku();
    }
}
