import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalProject extends JFrame implements ActionListener {
    private JPanel gameBoard;
    private JButton[][] buttons;
    private String[][] data;
    // Tracks the turns of players
    private boolean player1Turn;
    private GridLayout myManager;
    private boolean flag = false;
    private int justMovedRow=-1, justMovedCol=-1;
    private ImageIcon grid = new ImageIcon(getClass().getResource("/grid.PNG")); // load the image to a imageIcon
    private ImageIcon black = new ImageIcon(getClass().getResource("/black.png"));
    private ImageIcon white = new ImageIcon(getClass().getResource("/white.png"));
    private ImageIcon black_win = new ImageIcon(getClass().getResource("/black_win.png"));
    private ImageIcon white_win = new ImageIcon(getClass().getResource("/white_win.png"));
    private ImageIcon justMovedBlack = new ImageIcon(getClass().getResource("/justMovedBlack.png"));
    private ImageIcon justMovedWhite = new ImageIcon(getClass().getResource("/justMovedWhite.png"));
    private ImageIcon[] icons = {grid, black, white, black_win, white_win, justMovedBlack, justMovedWhite};
    private Image image;
    private Image newimg;
    
    public FinalProject() {
        // configuring settings for window
        for(int i=0;i<icons.length;i++){
            image = icons[i].getImage(); // transform it
            newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icons[i] = new ImageIcon(newimg);
        }
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
                buttons[row][col] = new JButton();
                buttons[row][col].setIcon(icons[0]);
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
    public boolean didWin(boolean turn, String[][] board) {
        String whosTurn = "";
        if (turn) {
            whosTurn += "w";
        } else {
            whosTurn += "b";
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if(didConnect5(row, col, board, whosTurn)==5){
                    return true;
                }
            }
        }
        return false;
    }
    public int didConnect5(int row, int col, String[][] board, String turn) {
        int[] methods = new int[4];
        int whichMethod=-1;
        for(int i=0;i<5;i++){
            if(row+i < 19){
                if(row+i <board.length && board[row+i][col].equals(turn)){
                    methods[0]++;//vertical
                }
            }
            if(col +i < 19){
                if(col+i <board.length && board[row][col+i].equals(turn)){
                    methods[1]++;//horizontal 
                }
            }
            if(col + i < 19 && row + i <19){
                if(col+i <board.length && row+i <board.length && board[row+i][col+i].equals(turn)){
                    methods[2]++;//right up to left down diagnal
                }
            }
            if(col- i >= 0 && row + i < 19){
                if(col-i >=0 && row+i <board.length && board[row+i][col-i].equals(turn)){
                    methods[3]++;//right down to left up diagnal
                }
            }
        }
        int howLong=-1;
        for(int i=0;i<4;i++){
            if(methods[i]==5){
                whichMethod=i;
                howLong=methods[i];
            }
        }
        ImageIcon temp;
        if(turn.equals("w")){
            temp=icons[4];
        }
        else if(turn.equals("b")){
            temp=icons[3];
        }
        else{
            return howLong;
        }
        if(whichMethod!=-1){
            if(whichMethod==0){
                for(int i=0;i<5;i++){
                    buttons[row+i][col].setIcon(temp);
                }
            }
            else if(whichMethod==1){
                for(int i=0;i<5;i++){
                    buttons[row][col+i].setIcon(temp);
                }
            }
            else if(whichMethod==2){
                for(int i=0;i<5;i++){
                    buttons[row+i][col+i].setIcon(temp);
                }
            }
            else{
                for(int i=0;i<5;i++){
                    buttons[row+i][col-i].setIcon(temp);
                }
            }
        }
        return howLong;
    }


    public void restart() {
        justMovedCol=-1;
        justMovedRow=-1;
        for (int row = 0; row < 19; row++) {
            for (int col = 0; col < 19; col++) {

                data[row][col] = "";
                
                buttons[row][col].setIcon(icons[0]);
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
        Integer col = Integer.parseInt(event.getActionCommand().substring(event
        .getActionCommand().indexOf("-") + 1));

        if (data[row][col].equals("") && flag == false) {
            if (player1Turn) {
                if(justMovedRow!=-1){
                    buttons[justMovedRow][justMovedCol].setIcon(icons[1]);
                }
                justMovedCol=col;
                justMovedRow=row;
                buttons[row][col].setIcon(icons[6]);
                data[row][col] += "w";
                if (didWin(player1Turn, data)) {
                    flag = true;
                }
                player1Turn = !player1Turn;
            } else {
                if(justMovedRow!=-1){
                    buttons[justMovedRow][justMovedCol].setIcon(icons[2]);
                }
                justMovedCol=col;
                justMovedRow=row;
                buttons[row][col].setIcon(icons[5]);
                data[row][col] += "b";
                if (didWin(player1Turn, data)) {
                    flag = true;
                }
                player1Turn = !player1Turn;
            }
        } else if (flag) {
            restart();
        }
    }

    public static void main(String[] args) {
        FinalProject myWindow = new FinalProject();
    }
}
