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

        if (usrInpt.equals("E")){
            Board game = new Board(8, 8, 10);

            System.out.println("DEBUG:\n" + game.getMineLocations());
            System.out.println("DEBUG:\n" + game.toString());
        }
        else if (usrInpt.equals("M")){
            Board game = new Board(16, 16, 40);

            System.out.println("DEBUG:\n" + game.getMineLocations());
        }
        else if (usrInpt.equals("H")){
            Board game = new Board(16, 30, 99);

            System.out.println("DEBUG:\n" + game.getMineLocations());
        }
        else if (usrInpt.equals("C")){
            System.out.println("custom boards dont exist yet lol sorry");
        }

        
        

    }
}