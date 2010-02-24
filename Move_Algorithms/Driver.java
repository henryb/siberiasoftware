package Siberia;


import Siberia.ChessGame;


public class Driver {
	
	public static void main(String[] args) {	
			
			System.out.println("~~~~~~~SIBERIA CHESS 1.2~~~~~~~\n");
									
			ChessGame myGame = new ChessGame();			
			myGame.configureBoard();
			myGame.printBoard();			
			
			int[] current = new int[]{6, 4};
			int[] target = new int[]{4, 4};
			
			if (myGame.movePiece(current, target))
				System.out.println("Works\n");			
			else
				System.out.println("Invalid move\n");
				
			
			myGame.printBoard();
			
			
	}
}
