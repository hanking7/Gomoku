public class WinCondition {
    private static int[] methods = new int[4];
    public static boolean didWin(boolean turn, String[][] board) {
        String whosTurn = "";
        if (turn) {
            whosTurn += "w";
        } else {
            whosTurn += "b";
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                    if(didConnect5(row, col, board, whosTurn)!=-1){
                        return true;
                    }
            }
        }
        return false;
    }

    public static int didConnect5(int row, int col, String[][] board, String turn) {
        for(int i=0;i<4;i++){
            methods[i]=0;
        }
        for(int i=0;i<5;i++){
            if(row+i <board.length && board[row+i][col].equals(turn)){
                methods[0]++;//vertical
            }
            if(col+i <board.length && board[row][col+i].equals(turn)){
                methods[1]++;//horizontal 
            }
            if(col+i <board.length && row+i <board.length && board[row+i][col+i].equals(turn)){
                methods[2]++;//right up to left down diagnal
            }
            if(col-i >=0 && row+i <board.length && board[row+i][col-i].equals(turn)){
                methods[3]++;//right down to left up diagnal
            }
        }
        int howLong =-1;
        for(int i=0;i<4;i++){
            if(methods[i]==5){
                howLong = i;
                
            }
        }
        return howLong;
    }
    public static int getWinType(){
        for(int i=0;i<4;i++){
            if(methods[i]==5){
                return methods[i];
                
            }
        }
        return -1;
    }
}