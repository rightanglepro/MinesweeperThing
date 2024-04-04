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
                gameBoardStatus[row][col] = 1;
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

    public int calculateSurroundingMines(int row, int col){ // calculate how many mines are surrounding a certain cell
        int mineSum = 0;
        for (int r = row-1; r <= row+1; r++){ 
            // if r outside of the gameboard
            if (r >= rows){ 
                break;
            }
            if (r < 0){
                r = 0;
            }

            for (int c = col-1; c <= col+1; c++){
                // if c is outside of the gameboard
                if (c >= columns){
                    break;
                }
                if (c < 0){
                    c = 0;
                }

                if ( !(c == col && r == row) ){
                    mineSum += gameBoard[r][c];
                }
            }
        }

        return mineSum;
    }
    public int getCell(int row, int col){
        return gameBoard[row][col];
    }
    public String getMineLocations(){ // get the game board but not the status of each cell, just mines
        String str = "\t";

        for (int c = 0; c < columns; c++){
            str += ((char)(c + 65) + " "); // "    A B C ..."
        }
        str += "\n\n";

        for (int row = 0; row < rows; row++){
            str += (row + "\t"); // "0   ..."
            for (int col = 0; col < columns; col++){
                str += gameBoard[row][col] + " ";
            }
            str += "\n";
        }

        return str;
    }

    public String toString(){
        String str = "\t";

        for (int c = 0; c < columns; c++){
            str += ((char)(c + 65) + " "); // "    A B C ..."
        }
        str += "\n\n";

        for (int row = 0; row < rows; row++){
            str += (row + "\t"); // "0   ..."
            for (int col = 0; col < columns; col++){

                if (gameBoardStatus[row][col] == 0){ // if status is NOT clicked (0),
                    str += "- "; // show as a hyphen
                }
                else if (gameBoardStatus[row][col] == 1){ // if status IS clicked (1),

                    if (gameBoard[row][col] == 1){ // if the cell is a bomb,
                        str += "X "; // show as an X
                    }
                    else {
                        int mS = calculateSurroundingMines(row, col);

                        if (mS == 0){ // if there are no mines in surrounding cells,
                            str += "  "; // show as a space
                        }
                        else { // if there are mines,
                            str += mS + " "; // show number of mines
                        }
                    }
                    
                }
                else if (gameBoardStatus[row][col] == 2){ // if status is flagged (2),
                    str += "! "; // show as an !
                }
                else if (gameBoardStatus[row][col] == 3){ // if status is maybe-flagged (3),
                    str += "? "; // show as a ?
                }
            }
            str += "\n";
        }

        return str;
    }

}