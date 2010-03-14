package Siberia;


public class King extends ChessPiece {
	
	public boolean hasMoved;
	
	public King(String BorW){
		color = BorW;
		hasMoved = false;
	}	
	
	
	public boolean verify_move(int[][] board, int[] position, int[] next){	
	
		if (!canDefeat(color, board[next[0]][next[1]])){						
			return false;
		}
		
		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];		
		if (Math.abs(RowDiff) > 1 || Math.abs(ColDiff) > 1)
			return false;
		
		if(isThereCheck(board, next))
			return false;
		
		hasMoved = true;
		return true;		
	}
	
	public boolean isThereCheck(int[][] board, int[]target){
		
		int myKing = 1;
		int enemyKing = 8;
		int enemyQueen = 9;
		int enemyRook1 = 10;
		int enemyRook2 = 11;
		int enemyKnight = 12;
		int enemyBishop = 13;
		int enemyPawn = 14;
		
		if (color == "black"){
			myKing = 8;
			enemyKing = 1;
			enemyQueen = 2;
			enemyRook1 = 3;
			enemyRook2 = 4;
			enemyKnight = 5;
			enemyBishop = 6;
			enemyPawn = 7;
		}
		
		int row = target[0];
    	int column = target[1];		
		
    	//evaluating threat from a Rook or Queen
		int[][] RVectors = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
		
		for (int i=0; i < RVectors.length ; i++){
			int testRow = row + RVectors[i][0];
			int testCol = column + RVectors[i][1];
			while (validSquare(new int[]{testRow, testCol}) && (board[testRow][testCol] == 0 || board[testRow][testCol] == myKing))
			{	
				testRow = testRow + RVectors[i][0];
				testCol = testCol + RVectors[i][1];				
			}
			if (validSquare(new int[]{testRow, testCol})){
				if(board[testRow][testCol] == enemyRook1 || board[testRow][testCol] == enemyRook2 || board[testRow][testCol] == enemyQueen)
					return true;
			}
		}
		
		//evaluating threat from a Bishop or Queen
		int[][] BVectors = new int[][]{{1,1}, {1,-1}, {-1,1}, {-1,-1}};
		
		for (int i=0; i < BVectors.length ; i++){
			int testRow = row + BVectors[i][0];
			int testCol = column + BVectors[i][1];
			while (validSquare(new int[]{testRow, testCol}) && (board[testRow][testCol] == 0 || board[testRow][testCol] == myKing))
			{	
				testRow = testRow + BVectors[i][0];
				testCol = testCol + BVectors[i][1];				
			}
			if (validSquare(new int[]{testRow, testCol})){
				if(board[testRow][testCol] == enemyBishop || board[testRow][testCol] == enemyQueen)
					return true;
			}
		}
		
		//evaluating threat from a Knight
		int[][] Kvectors = new int[][]{{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
		
		for (int i=0; i < Kvectors.length ; i++){
			 int[] move = {Kvectors[i][0]+ row, Kvectors[i][1]+ column};
	         if(validSquare(move) && board[move[0]][move[1]] == enemyKnight)
	        	return true;	        	
		}
		
		//evaluating threat from a Pawn
		int[][] pawnThreats;
		if(color == "white")
			pawnThreats = new int[][]{{-1, 1}, {-1, -1}};
		else
			pawnThreats = new int[][]{{1, 1}, {1, -1}};	
		
		for (int i=0; i < pawnThreats.length ; i++){				
        	int[] move = {pawnThreats[i][0]+ row, pawnThreats[i][1]+ column};
        	if(validSquare(move) && board[move[0]][move[1]] == enemyPawn)
		        return true;
		}
		
		//evaluating threat from a King
		int[][] directions = new int[][]{{1,0}, {1,1}, {1,-1}, {-1,0}, {-1,1}, {-1,-1}, {0,1}, {0,-1}};
		
		for (int i=0; i < directions.length ; i++){				
        	int[] move = {directions[i][0]+ row, directions[i][1]+ column};
        	if(validSquare(move) && board[move[0]][move[1]] == enemyKing)
		        return true;
		}

		return false;			  
		
	}
	
		public boolean isThereCheckMate(int[][] board, int[] position){
			
			int[][] directions = new int[][]{{1,0}, {1,1}, {1,-1}, {-1,0}, {-1,1}, {-1,-1}, {0,1}, {0,-1}};
			
			for (int i=0; i < directions.length ; i++){				
	        	int[] move = {directions[i][0]+ position[0], directions[i][1]+ position[1]};
	        	if(validSquare(move) && canDefeat(color, board[move[0]][move[1]]) && !isThereCheck(board, move))
			        return false;
			}
			//exited for-loop, means every possible move is under check
			return true;
		}

		
		private boolean validSquare(int[] position){
			if(position[0] >= 0 && position[0] <= 7){
				if (position[1] >= 0 && position[1] <= 7){
					return true;
				}
			}
			return false;
		}
}
