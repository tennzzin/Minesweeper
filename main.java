// Tenzin Chonyi, chony003
// Tenzin Gendun, gendu002


import java.util.Random;
import java.util.Scanner;

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */

public class main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Prompt user for debug mode
        System.out.println("Would you like to play on debug mode?: [true or false]");
        boolean debugTrue = Boolean.parseBoolean(s.nextLine());

        // Prompt user to choose difficulty
        System.out.println("Choose difficulty level: [number]");
        System.out.println("1. Easy (5 x 5)");
        System.out.println("2. Medium (9 x 9)");
        System.out.println("3. Hard (20 x 20)");
        int difficultyChoice = Integer.parseInt(s.nextLine());

        // Set minefield dimensions off of difficulty choice
        int rows, cols, mines, flags;
        switch (difficultyChoice) {
            case 1:
                rows = 5;
                cols = 5;
                mines = 5;
                flags = 5;
                break;
            case 2:
                rows = 9;
                cols = 9;
                mines = 12;
                flags = 12;
                break;
            case 3:
                rows = 20;
                cols = 20;
                mines = 40;
                flags = 40;
                break;
            default:
                System.out.println("Invalid choice. Quitting game.");
                return;
        }
        Minefield minefield = new Minefield(rows, cols, flags);    // Create minefield

        // Initialize minefield with user input
        System.out.println("Enter starting coordinates: [x] [y]");
        String[] start = s.nextLine().split(" ");
        int startX = Integer.parseInt(start[0]);
        int startY = Integer.parseInt(start[1]);

        minefield.createMines(startX, startY, mines);
        minefield.evaluateField();
        minefield.revealStartingArea(startX, startY);

        // Game loop
        while (!minefield.gameOver()) {
            if (debugTrue) {
                minefield.debug();  // Print debug board
            }
            System.out.println(minefield);  // Print game board

            // Prompt user for input
            System.out.println("Enter a coordinate and if you wish to place a flag (Remaining flags: " + minefield.getFlags() + "): [x] [y] [true or false]");
            String[] line = s.nextLine().split(" ");
            int userRow = Integer.parseInt(line[0]);
            int userCol = Integer.parseInt(line[1]);
            boolean flag = Boolean.parseBoolean(line[2]);
            boolean hitMine = minefield.guess(userRow, userCol, flag);

            // Check if user hit mine
            if (hitMine && !flag) {
                System.out.println("You hit a mine! Game Over!");
                break;
            }
        }
        // Check if user won
        if (minefield.gameOver()) {
            System.out.println("Congratulations! You won!");
        }
    }
}
