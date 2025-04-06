# Minesweeper

## Overview

This project is a console-based Minesweeper game implemented in Java. It challenges players to uncover all non-mine cells on a dynamic grid while strategically flagging mines. The game features multiple difficulty levels, a debug mode for testing, and a robust implementation of core game mechanics.

## Features
- **Dynamic Difficulty Levels**: Choose from easy (5x5), medium (9x9), and hard (20x20) grids.
- **Flagging Mechanism**: Safely mark suspected mines with flags.
- **Debug Mode**: Visualize the entire board during gameplay for testing.
- **User-Friendly Controls**: Intuitive prompts for navigation and inputs.
- **Victory/Loss Detection**: Detects win or loss conditions based on game progress. 

## Technology Stack
- **Language**: Java
- **Core Concepts**:
  - **Object-Oriented Programming (OOP)**
  - **Data Structures**: Stacks and Queues for grid traversal.
  - **ANSI Color Formatting**: For visual clarity.
  - **Randomized Mine Placement**: Adds unpredictability to gameplay.

## Classes
- **Cell**: Represents individual cells on the game board with attributes like status and visibility.
- **Minefield**: Core logic for:
  - Dynamic mine placement and board evaluation.
  - Cell revealing with zero-cell cascading using stacks and queues.
  - Game state management.
- **Stack1Gen & Q1Gen**: Generic implementations of stack and queue data structures.
- **main**: Entry point that handles user input, game initialization, and the main game loop.

## How to Play
1. Clone the repository:
   - `git clone https://github.com/tennzzin/minesweeper.git`
   - `cd minesweeper`
2. Compile and run the game:
   - `javac *.java`
   - `java main`
3. Follow the on-screen instructions:
   - Select difficulty level.
   - Enter starting coordinates.
   - Choose cells to reveal or flag until you win or hit a mine!

## Skills Demonstrated
- **Algorithm Development**  
  Efficient traversal and evaluation of a 2D grid.
  
- **Data Structures**  
  Leveraged stacks and queues for cascading reveals and initial area generation.
  
- **Debugging and Testing**  
  Implemented a debug mode for enhanced visibility during development.

## Authors
- **Tenzin Chonyi** - [LinkedIn](https://www.linkedin.com/in/tenzin-chonyi)
- **Tenzin Gendun** - [LinkedIn](https://www.linkedin.com/in/tenzingendun/)
