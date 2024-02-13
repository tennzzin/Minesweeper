// Tenzin Chonyi, chony003
// Tenzin Gendun, gendu002

import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    /* 
     * Class Variable Section
     * 
    */
    private Cell[][] minefield;
    private int mines;


    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */
    
    /**
     * Minefield
     * 
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */
    public Minefield(int rows, int columns, int flags) {
        minefield = new Cell[rows][columns];
        for (int i = 0; i < minefield.length; i++) {
            for (int j = 0; j < minefield[0].length; j++) {
                minefield[i][j] = new Cell(false, "0");    // Set each cell unrevealed, with status 0
            }
        }
        mines = flags;
    }

    /**
     * evaluateField
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     */
    public void evaluateField() {
        for (int i = 0; i < minefield.length; i++) {
            for (int j = 0; j < minefield[0].length; j++) {
                // Check if the cell is a mine
                if (minefield[i][j].getStatus().equals("M"))
                    continue;
                // Check cell above
                if (i - 1 >= 0 && minefield[i-1][j].getStatus().equals("M")) {
                    int status = Integer.parseInt(minefield[i][j].getStatus());
                    status++;
                    minefield[i][j].setStatus(""+status);
                }
                // Check cell below
                if (i + 1 < minefield.length && minefield[i+1][j].getStatus().equals("M")) {
                    int status = Integer.parseInt(minefield[i][j].getStatus());
                    status++;
                    minefield[i][j].setStatus(""+status);
                }
                // Check left cell
                if (j - 1 >= 0 && minefield[i][j-1].getStatus().equals("M")) {
                    int status = Integer.parseInt(minefield[i][j].getStatus());
                    status++;
                    minefield[i][j].setStatus(""+status);
                }
                // Check right cell
                if (j + 1 < minefield[0].length && minefield[i][j+1].getStatus().equals("M")) {
                    int status = Integer.parseInt(minefield[i][j].getStatus());
                    status++;
                    minefield[i][j].setStatus(""+status);
                }
            }
        }
    }

    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        // Loop for amount of mines
        int i = 0;
        while (i < mines) {
            // Create coordinates
            Random r = new Random();
            int xCor = r.nextInt(minefield.length);
            int yCor = r.nextInt(minefield[0].length);
            // Coordinate check
            if (!minefield[xCor][yCor].getStatus().equals("M") && (xCor != x) && (yCor != y)) {
                minefield[xCor][yCor].setStatus("M");   // Place mine
                i++;
            }
        }
    }

    /**
     * guess
     * 
     * Check if the guessed cell is inbounds (if not done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     *
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        // Check bounds on user guess
        if (x < 0 || x >= minefield.length || y < 0 || y >= minefield[0].length) {
            return false;
        }
        // Placing a flag
        if (flag && mines > 0) {
            minefield[x][y].setStatus("F");
            mines--;
        // If the cell is 0
        } else if (minefield[x][y].getStatus().equals("0")) {
            revealZeroes(x, y);
        // If the cell is a mine
        } else if (minefield[x][y].getStatus().equals("M")) {
            return true;
        }
        minefield[x][y].setRevealed(true);     // Reveal cell to user

        return minefield[x][y].getStatus().equals("M");
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        // Check for any mines left
        for (int i = 0; i < minefield.length; i++){
            for (int j = 0; j < minefield[0].length; j++){
                if (minefield[i][j].getStatus().equals("M") && !minefield[i][j].getRevealed()) {
                    return false; // Unrevealed mine found, game not over
                }
            }
        }
        // Check for any cells left
        for (int i = 0; i < minefield.length; i++){
            for (int j = 0; j < minefield[0].length; j++){
                if (!minefield[i][j].getStatus().equals("M") && !minefield[i][j].getRevealed()) {
                    return false; // Unrevealed cell found, game not over
                }
            }
        }
        return true;    // No cells or mines left, game over
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        // Initialize stack
        Stack1Gen<int[]> stack = new Stack1Gen<>();
        int[] start = {x, y};
        stack.push(start);
        // Loop until stack empty
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int xCor = curr[0];
            int yCor = curr[1];
            // Check the cell above
            if ((xCor - 1 >= 0) && (xCor < minefield.length) && (yCor >= 0) && (yCor < minefield[0].length) &&
                    (!minefield[xCor-1][yCor].getRevealed()) && (minefield[xCor-1][yCor].getStatus().equals("0"))) {
                minefield[xCor-1][yCor].setRevealed(true);   // Reveal cell to user
                stack.push(new int[]{xCor - 1, yCor});
            }
            // Check the cell below
            if ((xCor >= 0) && (xCor + 1 < minefield.length) && (yCor >= 0) && (yCor < minefield[0].length) &&
                    (!minefield[xCor+1][yCor].getRevealed()) && (minefield[xCor+1][yCor].getStatus().equals("0"))) {
                minefield[xCor+1][yCor].setRevealed(true);
                stack.push(new int[]{xCor + 1, yCor});
            }
            // Check cell to left
            if ((xCor >= 0) && (xCor < minefield.length) && (yCor - 1 >= 0) && (yCor < minefield[0].length) &&
                    (!minefield[xCor][yCor-1].getRevealed()) && (minefield[xCor][yCor-1].getStatus().equals("0"))) {
                minefield[xCor][yCor-1].setRevealed(true);
                stack.push(new int[]{xCor, yCor - 1});
            }
            // Check cell to right
            if ((xCor >= 0) && (xCor < minefield.length) && (yCor >= 0) && (yCor + 1 < minefield[0].length) &&
                    (!minefield[xCor][yCor+1].getRevealed()) && (minefield[xCor][yCor+1].getStatus().equals("0"))) {
                minefield[xCor][yCor+1].setRevealed(true);
                stack.push(new int[]{xCor, yCor + 1});
            }
        }
    }

    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y){
        // Initialize queue
        Q1Gen<int[]> queue = new Q1Gen<>();
        int[] start = {x, y};
        queue.add(start);
        // Loop until queue empty
        while (queue.length() != 0) {
            int[] curr = queue.remove();
            int xCor = curr[0];
            int yCor = curr[1];
            minefield[xCor][yCor].setRevealed(true);    // Reveal cell to user
            // Check if cell is a mine
            if (minefield[xCor][yCor].getStatus().equals("M")) {
                break;
            } else {
                // Check the cell above
                if ((xCor - 1 >= 0) && (xCor < minefield.length) && (yCor >= 0) && (yCor < minefield[0].length) &&
                        (!minefield[xCor-1][yCor].getRevealed())) {
                    queue.add(new int[]{xCor - 1, yCor});
                }
                // Check the cell below
                if ((xCor >= 0) && (xCor + 1 < minefield.length) && (yCor >= 0) && (yCor < minefield[0].length) &&
                        (!minefield[xCor+1][yCor].getRevealed())) {
                    queue.add(new int[]{xCor + 1, yCor});
                }
                // Check cell to left
                if ((xCor >= 0) && (xCor < minefield.length) && (yCor - 1 >= 0) && (yCor < minefield[0].length) &&
                        (!minefield[xCor][yCor-1].getRevealed())) {
                    queue.add(new int[]{xCor, yCor - 1});
                }
                // Check cell to right
                if ((xCor >= 0) && (xCor < minefield.length) && (yCor >= 0) && (yCor + 1 < minefield[0].length) &&
                        (!minefield[xCor][yCor+1].getRevealed())) {
                    queue.add(new int[]{xCor, yCor + 1});
                }
            }
        }
    }

    /**
     * For both printing methods utilize the ANSI colour codes provided!
     *
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < minefield.length; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < minefield.length; i++) {
            out.append(i);
            out.append(" ");
            for (int j = 0; j < minefield[0].length; j++) {
                out.append(colorCell(minefield[i][j], true));
                out.append(" ");
            }
            out.append("\n");
        }
        System.out.println(out);
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < minefield.length; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < minefield.length; i++) {
            out.append(i);
            out.append(" ");
            for (int j = 0; j < minefield[0].length; j++) {
                out.append(colorCell(minefield[i][j], false));
                out.append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }

// Helper function for toString and debug
    public String colorCell(Cell cell, boolean wantReveal) {
        //Check if all cells are to be revealed
        if (!wantReveal) {
            if (!cell.getRevealed()) {
                return "-";
            }
        }
        String status = cell.getStatus();
        switch (status) {
            case "0":
                return ANSI_YELLOW + status + ANSI_GREY_BACKGROUND;
            case "1":
                return ANSI_BLUE + status + ANSI_GREY_BACKGROUND;
            case "2":
                return ANSI_GREEN + status + ANSI_GREY_BACKGROUND;
            case "3":
                return ANSI_RED + status + ANSI_GREY_BACKGROUND;
            case "4":
                return ANSI_PURPLE + status + ANSI_GREY_BACKGROUND;
            case "5":
                return ANSI_CYAN + status + ANSI_GREY_BACKGROUND;
            case "6":
                return ANSI_YELLOW + status + ANSI_GREY_BACKGROUND;
            case "7":
                return ANSI_BLUE + status + ANSI_GREY_BACKGROUND;
            case "8":
                return ANSI_GREEN + status + ANSI_GREY_BACKGROUND;
            case "9":
                return ANSI_RED + status + ANSI_GREY_BACKGROUND;
            case "M":
                return ANSI_RED_BRIGHT + status + ANSI_GREY_BACKGROUND;
            case "F":
                return ANSI_YELLOW_BRIGHT + status + ANSI_GREY_BACKGROUND;
            default:
                return status;
        }
    }

// Helper function to get flag count (for main)
    public int getFlags() {
        return mines;
    }
}

