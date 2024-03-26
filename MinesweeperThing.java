import java.util.Scanner;

public class MinesweeperThing {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Java Minesweeper Thing\n" + 
                           "v0.0.1 Alpha Mar 11 2024\n" + 
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

            System.out.println("DEBUG:\n" + game.getMineLocations());
            System.out.println("DEBUG:\n" + game.toString());
        }
        else if (usrInpt.equals("M")){
            game = new Board(16, 16, 40);

            System.out.println("DEBUG:\n" + game.getMineLocations());
            System.out.println("DEBUG:\n" + game.toString());
        }
        else if (usrInpt.equals("H")){
            game = new Board(16, 30, 99);

            System.out.println("DEBUG:\n" + game.getMineLocations());
            System.out.println("DEBUG:\n" + game.toString());
        }
        else if (usrInpt.equals("C")){
            System.out.println("custom boards dont exist yet lol sorry");
        }

        
        boolean playContinue;
        System.out.println(game.toString());
        playContinue = play(true, game);

        while (playContinue){
            System.out.println(game.toString());
            playContinue = play(false, game);
        }

    }

    public static boolean play(boolean firstTime, Board game){
        int col;
        int row;
        int result;
        String usrInpt;
        
        if (firstTime){
            System.out.println("Enter a cell's column and row, Ex: \"A 1\""); 
        }
        else {
            System.out.print("Enter cell: ");
        }
        usrInpt = scan.nextLine();
        if (usrInpt.equals("END")){
            return false;
        }
        else {
            col = (usrInpt.substring(0, 1).toUpperCase().toCharArray())[0] - 65; // really convoluted way of converting the column char to an index
            row = Integer.parseInt(usrInpt.substring(2));
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
            
            if (usrInpt.equals("END")){
                return false;
            }
            else if (usrInpt.equals("C")){
                result = game.click(row, col);
                if (result == 0){
                    return false;
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
        }

        return true;
    }
        

}