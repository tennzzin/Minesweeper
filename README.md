Minesweeper Game
Overview
This project is a console-based Minesweeper game implemented in Java. It challenges players to uncover all non-mine cells on a dynamic grid while strategically flagging mines. The game features multiple difficulty levels, a debug mode for testing, and a robust implementation of core game mechanics.

Features
Dynamic Difficulty Levels: Choose from easy (5x5), medium (9x9), and hard (20x20) grids.
Flagging Mechanism: Safely mark suspected mines with flags.
Debug Mode: Visualize the entire board during gameplay for testing.
User-Friendly Controls: Intuitive prompts for navigation and inputs.
Victory/Loss Detection: Detects win or loss conditions based on game progress.
Technology Stack
Language: Java
Core Concepts:
Object-Oriented Programming (OOP)
Data Structures: Stacks and Queues for grid traversal
ANSI Color Formatting for visual clarity
Randomized Mine Placement
Classes
Cell: Represents individual cells on the game board with attributes like status and visibility.
Minefield: Core logic for:
Dynamic mine placement and board evaluation.
Cell revealing with zero-cell cascading using stacks and queues.
Game state management.
Stack1Gen & Q1Gen: Generic implementations of stack and queue data structures.
main: Entry point that handles user input, game initialization, and the main game loop.
How to Play
Clone the repository:
bash
Copy code
git clone https://github.com/yourusername/minesweeper-game.git
cd minesweeper-game
Compile and run the game:
bash
Copy code
javac *.java
java main
Follow the on-screen instructions:
Select difficulty level.
Enter starting coordinates.
Choose cells to reveal or flag until you win or hit a mine!
Skills Demonstrated
Algorithm Development: Efficient traversal and evaluation of a 2D grid.
Data Structures: Leveraged stacks and queues for cascading reveals and initial area generation.
Debugging and Testing: Implemented a debug mode for enhanced visibility during development.
Future Improvements
Add a graphical user interface (GUI) for better user experience.
Implement a timer and scoring system for competitive play.
Add custom difficulty levels and configurations.
Authors:

Tenzin Chonyi (LinkedIn)
Tenzin Gendun
