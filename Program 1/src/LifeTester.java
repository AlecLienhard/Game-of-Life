import java.io.*;
import java.util.Scanner;

public class LifeTester{
public static void main(String[] args){
  Scanner input = new Scanner(System.in);
  String fileName;
  boolean goodName = false;
  
  Scanner numberFile = null;
  FileReader infile;
  Life life1 = new Life();
  
  System.out.println("Please input the name of the data file");
  fileName = input.nextLine();
  
  while (!goodName){
   try{
	infile = new FileReader(fileName);
	numberFile = new Scanner(infile);
	
	goodName = true;
 }//end try
 catch(IOException e){
	System.out.println("invalid file name, please enter another name");
	fileName = input.nextLine();
 }// end catch
 }//end while
 life1.fillBoard(numberFile);
 try {
	life1.playGame();
} catch (InterruptedException e) {
	
	e.printStackTrace();
}



}// end main
}// end class
 



