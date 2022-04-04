//Alec Lienhard
//Project 1
//February 6, 2020
//Hayhurst-111-010

import java.util.Scanner;

public class Life {

private int[][] theBoard;

private int row;

private int col;

private int dying;

private int beingBorn;

private int occupied;

private int free;

public Life() {

free = 0;

occupied = 1;

dying = 2;

beingBorn = 3;

this.row = 10;

this.col = 10;

this.theBoard = new int[this.row][col];

}
//Purpose:	A non-default constructor who's main purpose is to create the 10x10 board
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public Life(int row, int col) {

drawBoard();

setCanvas();

clearBoard();

occupied = 1;

free = 0;

dying = 2;

beingBorn = 3;

this.row = 10;

this.col = 10;

this.theBoard = new int[this.row][col]; 

this.row = row;

this.col = col;

}
//Purpose:	Draws the board to the screen and colors the cells blue and white for occupied and free.
//Parameters:	None
//Preconditions:   None
//Postconditions: None
//Exceptions:  None
private void drawBoard() {
// this assumes “cell[0][0] if the array is the upper 
	
//left corner”
	
int x, y;

for (int i = 0; i < this.row; i++) {

for (int j = 0; j < this.col; j++) {


if (this.isOccupied(i, j))

StdDraw.setPenColor(StdDraw.BLUE);

else

StdDraw.setPenColor(StdDraw.WHITE);

x = j * 10 + 5;

y = (10 * (this.row - i)) - 5;

StdDraw.filledSquare(x, y, 5);

} //inner
}

}// end drawBoard

//Purpose: Uses for loops and if statements to scan through the board and determine how many neighbors are in the current generation
//and tally's the number of occupied spaces.
//Parameters:	int r = row, int c = column
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public int countNeighbors(int r, int c) {

int count = 0;

for (int i = r - 1; i <= r + 1; i++) {

if (i >= 0 && i < theBoard.length)

for (int j = c - 1; j <= c + 1; j++)

if (j >= 0 && j < this.theBoard[i].length)

if (i != r || j != c)

if (theBoard[i][j] == occupied || theBoard[i][j] == dying)

count++;

}

return count;

}

//Purpose:	Checks to see if the board has a "1" value to determine occupancy.
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public boolean isOccupied(int r, int c) {

return this.theBoard[r][c] == occupied;

}
//Purpose:	Checks to see if the board has a "0" value to determine if the space is free.
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public boolean isFree(int r, int c) {

return this.theBoard[r][c] == free;

}


//Purpose: Uses countNeighbors method to determine if the cell is "beingBorn".
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public boolean isBorn(int r, int c) {

if (countNeighbors(r, c) == 3) {

return true;

}

else {

return false;

}

}
//Purpose: uses countNeighbors method to determine if the cell is either overcrowded, or lonely and will die.
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public boolean isDying(int r, int c) {

if (countNeighbors(r, c) >= 4) {

return true;

}

else if (countNeighbors(r, c) <= 1) {

return true;

}

else {

return false;

}

}


//Purpose:sets the board location to the state of "beingBorn".
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void setBorn(int r, int c) {

this.theBoard[r][c] = beingBorn;

}
//Purpose: sets the board location to "occupied".
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void setOccupied(int r, int c) {

this.theBoard[r][c] = occupied;

}
//Purpose: sets the cell to the state of "dying".
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void setDying(int r, int c) {

this.theBoard[r][c] = dying;

}
//Purpose: sets the cell to the state of "Free".
//Parameters:	Row and Column integer values
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void setFree(int r, int c) {

this.theBoard[r][c] = free;

}
//Purpose: Sets all the board values to "0" meaning free.
//Parameters: None
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void clearBoard() {

for (int i = 0; i < theBoard.length; i++)

for (int j = 0; j < theBoard[i].length; j++) {

this.theBoard[i][j] = free;

}

}
//Purpose: This method uses a scanner to read the contents of a text file 
//and provides the values to begin the game.
//Parameters: Uses a Scanner with the name of inputFile
//Preconditions:  None 
//Postconditions: None
//Exceptions:  None
public void fillBoard(Scanner inputFile) {

while (inputFile.hasNextLine()) {

int a = inputFile.nextInt();

int b = inputFile.nextInt();

setOccupied(a, b);

}

setCanvas();

drawBoard();

}
//Purpose: This initializes the game and creates the next generation of cells.
//Parameters: None
//Preconditions:  None 
//Postconditions: None
//Exceptions: Thrown when a thread is waiting, sleeping, or otherwise occupied,and the thread is interrupted,
//either before or during the activity.
public void playGame() throws InterruptedException {

boolean change = false;

do {

for (int i = 0; i < row; i++) {

for (int j = 0; j < col; j++) {

int neighbor = countNeighbors(i, j);

if (neighbor == 3 && !(theBoard[i][j] == occupied)) {

setBorn(i, j);

change = true;

}

else if (theBoard[i][j] == occupied && neighbor >= 4) {

setDying(i, j);

change = true;

}

else if (theBoard[i][j] == occupied && neighbor <= 1) {

setDying(i, j);

change = true;

}

else {

}

}

}

nextGeneration();

drawBoard();

Thread.sleep(7000);

}

while (change);

}
//Purpose: Takes the cells that are "beingBorn" and sets them to Occupied.
//And if the cell is dying, it's value is changed to Free.
//Parameters:	None
//Preconditions:   None
//Postconditions: None
//Exceptions:  None
private void nextGeneration() { 

for (int i = 0; i < row; i++)

for (int j = 0; j < col; j++) {

if (theBoard[i][j] == beingBorn) {

theBoard[i][j] = occupied;

}

else if (theBoard[i][j] == dying) {

theBoard[i][j] = free;

}

else {

}

}
}
//Purpose: Called after the board is filled and initializes StdDraw.
//Parameters:	None
//Preconditions:   None
//Postconditions: None
//Exceptions:  None
private void setCanvas() {

StdDraw.setScale(0.0, 10 * this.row);

StdDraw.setScale(0.0, 10 * this.col);

}



}