import java.util.Arrays;

public class Board {

    private int gameBoard[][];
    private int gameBoardStatus[][];
    private static int columns;
    private static int rows;

    public Board(int r, int c, int mines){
        columns = c;
        rows = r;

        gameBoard = new int[rows][columns];
        gameBoardStatus = new int[rows][columns];

        generateMines(mines);
    }

    public void generateMines(int mines){
        for (int row = 0; row < rows; row++){ // initialize all cells on board to empty (0) and status to not clicked (0)
            for (int col = 0; col < columns; col++){
                gameBoard[row][col] = 0;
                gameBoardStatus[row][col] = 0;
            }
        }

        for (int i = 0; i < mines; i++){ // randomly plant specified num of mines
            int col = (int)(Math.random() * columns);
            int row = (int)(Math.random() * rows);

            while (gameBoard[row][col] == 1){ // check if randomly generated cell is already a mine, generate again if so
                col = (int)(Math.random() * columns);
                row = (int)(Math.random() * rows);
            }

            gameBoard[row][col] = 1; // 1 means mine is planted at cell
        }
    }

    public String toString(){
        gameBoard[2][3] = 5;
        String str = "\t";
        for (int c = 0; c < columns; c++){
            str += ((char)(c + 65) + " "); // "    A B C ..."
        }
        str += "\n\n";
        for (int row = 0; row < rows; row++){
            str += (row + "\t");
            for (int col = 0; col < columns; col++){
                str += gameBoard[row][col] + " ";
            }
            str += "\n";
        }

        return str;
    }

}