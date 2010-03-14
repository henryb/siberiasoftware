package Siberia;


import Siberia.ChessBoard;


public class Driver {
	
	public static void main(String[] args) {	
			
			System.out.println("~~~~~~~SIBERIA CHESS 1.2~~~~~~~\n");
									
			//ChessBoard myGame = new ChessBoard();			
			//myGame.configureBoard();
			//myGame.printBoard();			
			
//			int[] current = new int[]{6, 4};
//			int[] target = new int[]{4, 4};
//			
//			if (myGame.validateMove(current, target))
//				System.out.println("Works\n");			
//			else
//				System.out.println("Invalid move\n");
//			
//			myGame.printBoard();
			
			int[][] board = new int[8][8];
			
			board[0] = new int[]{10,12,13,9,8,13,12,11};
			board[1] = new int[]{14,14,14,14,14,14,14,14};
			
			// 0 corresponds to empty space
			for(int i = 2; i <= 5; i++){
				for(int j = 0; j < board[i].length; j++){
					board[i][j] = 0;			
				}
			}
			board[6] = new int[]{7,7,7,7,7,7,7,7};
			board[7] = new int[]{3,5,6,2,1,6,5,4};			
			
			
			int[][] test_board = new int[8][8];
			
			//deep copy of board array			
	        for (int x = 0; x < board.length; x++)
	        	for (int y = 0; y < board[x].length; y++)
	        		test_board[x][y] = board[x][y];
	        
	        test_board[3][3] = 69;
	        for (int i=0; i < board.length ; i++){
	            System.out.print("\n");
	            for (int j=0; j < board[i].length ; j++){                	
	            	if (board[i][j] < 10){
	            		System.out.print("  " + board[i][j] + " ");
	            	}
	            	else{
	                    System.out.print(" " + board[i][j] + " ");
	            	}
	            }
	        }
	        System.out.print("\n blaaa");
	        
	        for (int i=0; i < test_board.length ; i++){
	            System.out.print("\n");
	            for (int j=0; j < test_board[i].length ; j++){                	
	            	if (test_board[i][j] < 10){
	            		System.out.print("  " + test_board[i][j] + " ");
	            	}
	            	else{
	                    System.out.print(" " + test_board[i][j] + " ");
	            	}
	            }
	        }
	        System.out.print("\n");
	}
}
