import java.util.Arrays;

public class Board {

    private int gameBoard[][];
    private int gameBoardStatus[][];
    private static int columns;
    private static int rows;

    public Board(int c, int r, int mines){
        columns = c;
        rows = r;

        gameBoard = new int[columns][rows];
        gameBoardStatus = new int[columns][rows];

        generateMines(mines);
    }

    public void generateMines(int mines){
        for (int col = 0; col < columns; col++){ // initialize all cells on board to empty (0)
            for (int row = 0; row < rows; row++){
                gameBoard[col][row] = 0;
            }
        }

        for (int i = 0; i < mines; i++){ // randomly plant specified num of mines
            int col = (int)(Math.random() * columns);
            int row = (int)(Math.random() * rows);

            while (gameBoard[col][row] == 1){ // check if randomly generated cell is already a mine, generate again if so
                col = (int)(Math.random() * columns);
                row = (int)(Math.random() * rows);
            }

            gameBoard[col][row] = 1; // 1 means mine is planted at cell
        }
    }

    public String toString(){
        String str = "";
        for (int col = 0; col < columns; col++){
            for (int row = 0; row < rows; row++){
                str += gameBoard[col][row] + " ";
            }
            str += "\n";
        }

        return str;
    }

}