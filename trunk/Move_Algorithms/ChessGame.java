package Siberia;


public class ChessGame {
	
		private int[][] board;		
		private ChessPiece[] gamePieces;
		private int[] WKingPos;
		private int[] BKingPos;
		
		// lastMove[0]: start coordinates of the last move
		// lastMove[1]: end coordinates of the last move
		private int[][] lastMove;	

		
		public ChessGame(){
			board = new int[8][8];
			
			// initialize 'lastMove' array, which will have valid values after 1st move is made
			lastMove = new int[][]{{0,0}, {0,0}};
			
			gamePieces = new ChessPiece[15];
			gamePieces[1] = new King("white");
			gamePieces[2] = new Queen("white");
			gamePieces[3] = new Rook("white");
			gamePieces[4] = new Rook("white");
			gamePieces[5] = new Knight("white");
			gamePieces[6] = new Bishop("white");
			gamePieces[7] = new Pawn("white");
			gamePieces[8] = new King("black");
			gamePieces[9] = new Queen("black");
			gamePieces[10] = new Rook("black");
			gamePieces[11] = new Rook("black");
			gamePieces[12] = new Knight("black");
			gamePieces[13] = new Bishop("black");
			gamePieces[14] = new Pawn("black");
			
		}
		
		public void configureBoard(){
			
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
			
			BKingPos = new int[]{0, 4};
			WKingPos = new int[]{7, 4};
			
		}

		public void printBoard(){
			System.out.print("Current Configuration is: ");
	    	
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
	        System.out.print("\n");
		}

		
		public boolean movePiece(int[] current, int[] next){			
			
			if (current == next){
				return false;
			}
			int pieceID = board[current[0]][current[1]];
			ChessPiece myPiece = gamePieces[pieceID];
			
			if (myPiece.validate_move(board, current, next)){
				board[next[0]][next[1]] = pieceID;
				board[current[0]][current[1]] = 0;
				lastMove[0] = current;
				lastMove[1] = next;
				
				// Promotion
				if(pieceID == 7 && next[0] == 0)
					board[next[0]][next[1]] = 2;
				if(pieceID == 14 && next[0] == 7)
					board[next[0]][next[1]] = 9;
				
				if(pieceID == 1)
					WKingPos = next;
				if(pieceID == 8)
					BKingPos = next;
				
				return true;
			}			
			// En Passant
			if (pieceID == 7 || pieceID == 14){
				if (current[1] != next[1] && board[next[0]][next[1]] == 0){					
					if (myPiece.validate_passant(board, current, next, lastMove)){
						board[next[0]][next[1]] = pieceID;
						board[current[0]][current[1]] = 0;
						board[current[0]][next[1]] = 0;						
						lastMove[0] = current;
						lastMove[1] = next;
						return true;
					}
				}				
			}			
			//Castling
			if ((pieceID == 1 || pieceID == 8 ) && Math.abs(current[1] - next[1]) == 2)
			{
				if(check_castling(pieceID, next))
					return true;
			}			
			return false;
		}


		// Set 'color' argument to "white" to see if White King is threatened, set to "black" to see if Black King is threatened
		public boolean isThereCheck(String color){
			
			if(color == "white")			
				return gamePieces[1].isThereCheck(board, WKingPos);
			else 
				return gamePieces[8].isThereCheck(board, BKingPos);			
		}
		
		
		// This function should be called after the 'isThereCheck' function returns true
		// Set 'color' argument to "white" to see if White King is threatened, set to "black" to see if Black King is threatened
		public boolean isThereCheckMate(String color){
			
			if(color == "white")
				return gamePieces[1].isThereCheckMate(board, WKingPos);			
			else
				return gamePieces[8].isThereCheckMate(board, BKingPos);			
		}
		
		
		private boolean check_castling(int pieceID, int[] next){
			ChessPiece myKing = gamePieces[pieceID];
			// White King, Queenside castling
			if (pieceID == 1 && next[0] == 7 && next[1] == 2){
				if (myKing.hasMoved == false && gamePieces[3].hasMoved == false){
					if(board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0){
						int[] passThrough = new int[]{7,3};
						if(!myKing.isThereCheck(board, WKingPos) && !myKing.isThereCheck(board, passThrough)){
							board[7][2] = 1;
							board[7][4] = 0;
							board[7][3] = 3;
							board[7][0] = 0;
							
							myKing.hasMoved = true;							
							lastMove[0] = WKingPos;
							lastMove[1] = next;
							WKingPos = next;
							return true;
						}
					}
				}
			}
			// White King, Kingside castling
			if (pieceID == 1 && next[0] == 7 && next[1] == 6){
				if (myKing.hasMoved == false && gamePieces[4].hasMoved == false){
					if(board[7][5] == 0 && board[7][6] == 0){
						int[] passThrough = new int[]{7,5};
						if(!myKing.isThereCheck(board, WKingPos) && !myKing.isThereCheck(board, passThrough)){
							board[7][6] = 1;
							board[7][4] = 0;
							board[7][5] = 4;
							board[7][7] = 0;
							
							myKing.hasMoved = true;
							lastMove[0] = WKingPos;
							lastMove[1] = next;
							WKingPos = next;
							return true;
						}
					}
				}
			}
			// Black King, Queenside castling
			if (pieceID == 8 && next[0] == 0 && next[1] == 2){
				if (myKing.hasMoved == false && gamePieces[10].hasMoved == false){
					if(board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0){
						int[] passThrough = new int[]{0,3};
						if(!myKing.isThereCheck(board, BKingPos) && !myKing.isThereCheck(board, passThrough)){
							board[0][2] = 8;
							board[0][4] = 0;
							board[0][3] = 10;
							board[0][0] = 0;
							
							myKing.hasMoved = true;
							lastMove[0] = BKingPos;
							lastMove[1] = next;
							BKingPos = next;
							return true;
						}
					}
				}
			}
			// Black King, Kingside castling
			if (pieceID == 8 && next[0] == 0 && next[1] == 6){
				if (myKing.hasMoved == false && gamePieces[11].hasMoved == false){
					if(board[0][5] == 0 && board[0][6] == 0){
						int[] passThrough = new int[]{0,5};
						if(!myKing.isThereCheck(board, BKingPos) && !myKing.isThereCheck(board, passThrough)){
							board[0][6] = 8;
							board[0][4] = 0;
							board[0][5] = 11;
							board[0][7] = 0;
							
							myKing.hasMoved = true;
							lastMove[0] = BKingPos;
							lastMove[1] = next;
							BKingPos = next;
							return true;
						}
					}
				}
			}
			return false;
		}
}
	