Omok Game (Gomoku) - Final Project
This project implements a GUI-based Omok (Gomoku) game in Java using Swing. Omok is a traditional two-player strategy game where players alternate turns placing black or white stones on a 19x19 board. The goal is to form a consecutive line of five of one’s own stones horizontally, vertically, or diagonally. This project supports game mechanics, including visual highlights for the last move and reset functionality.

Features
Player Turns and Graphics: Displays turn-based moves using black and white stones, with visual indicators for each player’s last move.
Win Detection: Checks for a consecutive sequence of five stones in four directions (horizontal, vertical, and two diagonals). Winning stones are highlighted to indicate victory.
Game Reset: Automatically resets the board when a win is detected, allowing players to start a new game seamlessly.
Resizable Interface: GUI is scalable and adjusts for different screen sizes, ensuring a consistent 19x19 game board view.
Game Rules
Objective: The first player to form a line of five consecutive stones (horizontally, vertically, or diagonally) wins.
Turns: Players alternate turns, starting with black stones.
Winning Highlight: The winning line of stones will be highlighted to signify the end of the game.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher.
An IDE like IntelliJ IDEA, Eclipse, or any text editor.
Installation
Clone the repository:
git clone https://github.com/your-username/omok-game.git
Open the project in your preferred IDE.
Add the grid.PNG, black.png, white.png, black_win.png, white_win.png, justMovedBlack.png, and justMovedWhite.png images to the resources directory. These images are used for displaying the board, stones, and highlights.
Running the Game
Run the FinalProject class to start the game.

cd src
javac FinalProject.java
java FinalProject
Code Structure
FinalProject: The main class implementing JFrame and ActionListener to create the game window and handle player interactions.
Game Board (JPanel): 19x19 grid layout representing the Omok board, with individual buttons for each cell.
Event Handling: actionPerformed method handles player moves and win detection.
Icons and Resources: Various icons are used to represent stones and winning sequences visually.
Customization
Board Size: Modify GridLayout(19, 19) in the constructor to change the board size.
Stone Icons: Change the images in the resources folder to use custom stone designs or colors.
