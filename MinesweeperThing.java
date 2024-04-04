import java.util.Scanner;

public class MinesweeperThing {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Java Minesweeper Thing\n" + 
                           "v0.0.1 Beta\n" + 
                           "Right Angle Productions, 2024\n");

        String usrInpt = "A";
        while ( !(usrInpt.equals("E") || usrInpt.equals("M") || usrInpt.equals("H") || usrInpt.equals("C")) ){
            System.out.println("Choose a gamemode:\n" + 
                               "[E]asy: 8x8 grid, 10 mines\n" + 
                               "[M]edium: 16x16 grid, 40 mines\n" + 
                               "[H]ard: 16x30 grid, 99 mines\n" + 
                               "[C]ustom");
            usrInpt = scan.nextLine();
            usrInpt = usrInpt.toUpperCase();
        }

        Board game = new Board(8, 8, 10); // default board bc java is a bitch and wont compile
        if (usrInpt.equals("E")){
            game = new Board(8, 8, 10);
        }
        else if (usrInpt.equals("M")){
            game = new Board(16, 16, 40);
        }
        else if (usrInpt.equals("H")){
            game = new Board(16, 30, 99);
        }
        else if (usrInpt.equals("C")){
            System.out.println("custom boards dont exist yet lol sorry");
        }

        
        int playContinue; // 0 = lost game, 1 = continue, 2 = quit game, 3 = won game
        System.out.println(game.toString());
        playContinue = play(true, game);

        while (playContinue == 1){
            System.out.println(game.toString());
            playContinue = play(false, game);
        }

        if (playContinue == 0){ // game ended because player lost
            game.showAllCells();
            System.out.println(game.toString());
            System.out.println("\nYou lost!");
        }
        else if (playContinue == 2){ // game ended bc player quit
            System.out.println("\nGame has been ended.");
        }
        else if (playContinue == 3){ // game ended bc player won
            System.out.println(game.toString());
            System.out.println("\nYou won!");
        }

    }

    public static int play(boolean firstTime, Board game){
        int col;
        int row;
        int result;
        String usrInpt;
        
        if (firstTime){
            System.out.print("Enter a cell's column and row, Ex: \"A1\"\t"); 
        }
        else {
            System.out.print("Enter cell: ");
        }
        usrInpt = scan.nextLine();
        System.out.println("");
        if (usrInpt.equals("END")){
            return 2; // quit game
        }
        else {
            col = (usrInpt.substring(0, 1).toUpperCase().toCharArray())[0] - 65; // really convoluted way of converting the column char to an index
            row = Integer.parseInt(usrInpt.substring(1));

            game.setSelectedCell(row, col);
            System.out.println(game.toString()); // display gameboard with + in place of selected cell
            game.setSelectedCell(-1, -1); // set gameboard's selected cell back to (-1,-1) (unselected)
        }

        usrInpt = "";
        while ( !(usrInpt.equals("C") || usrInpt.equals("F") || usrInpt.equals("?") || usrInpt.equals("U")) ){
            if (firstTime){
                System.out.println("Enter the action you'd like to commit: [C]lick, [F]lag as mine, [?] Flag as possible mine, [U]nflag");
            }
            else {
                System.out.print("[C]lick, [F]lag as mine, [?] Flag as possible mine, [U]nflag: ");
            }
            usrInpt = scan.nextLine();
            usrInpt = usrInpt.toUpperCase();
            System.out.println("");
            
            if (usrInpt.equals("END")){
                return 2; // quit game
            }
            else if (usrInpt.equals("C")){
                result = game.click(row, col);
                if (result == 0){ // if player clicked on a mine
                    return 0; // lost game
                }
            }
            else if (usrInpt.equals("F")){
                game.flag(row, col);
            }
            else if (usrInpt.equals("?")){
                game.maybeFlag(row, col);
            }
            else if (usrInpt.equals("U")){
                game.unflag(row, col);
            }

            if (game.isBoardCleared()){ // if game board has been cleared (all cells clicked or flagged)
                return 3; // player wins
            }
        }

        return 1;
    }
        

}