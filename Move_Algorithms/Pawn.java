package Siberia;


public class Pawn  extends ChessPiece  {
	
	public Pawn(String BorW){	
		color = BorW;		
	}
	
	public boolean validate_move(int[][] board, int[] position, int[] next){
	
		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];
		int target = board[next[0]][next[1]];
		
		if (color == "white"){			
			if (RowDiff == -1 && ColDiff == 0 && target == 0)
				return true;			
			if (RowDiff == -1 && Math.abs(ColDiff) == 1 && target > 7)
				return true;			
			if (position[0] == 6 && RowDiff == -2 && ColDiff == 0) {
					if ( board[5][position[1]] == 0 && target == 0)
						return true;
			}			
			return false;
		}
		else{			
			if (RowDiff == 1 && ColDiff == 0 && target == 0)
				return true;			
			if (RowDiff == 1 && Math.abs(ColDiff) == 1 && target > 1 && target < 8)
				return true;			
			if (position[0] == 1 && RowDiff == 2 && ColDiff == 0) {
					if ( board[2][position[1]] == 0 && target == 0)
						return true;
			}
			return false;
		}
	}
	
	public boolean validate_passant(int[][] board, int[] position, int[] next, int[][] lastMove){
		int[] lastMoveStart = lastMove[0];
		int[] lastMoveEnd = lastMove[1];
		int lastPiece = board[lastMoveEnd[0]][lastMoveEnd[1]];
		
		if (color == "white"){	
			if (lastPiece == 14 && lastMoveStart[0] == 1 && lastMoveEnd[0] == 3){
				if(position[0] == 3 && Math.abs(lastMoveEnd[1] - position[1]) == 1){
					if (next[0] - position[0] == -1 && next[1] == lastMoveEnd[1])
						return true;
					} 
				}
		}
		else {
			 if (lastPiece == 7 && lastMoveStart[0] == 6 && lastMoveEnd[0] == 4){
				 if(position[0] == 4 && Math.abs(lastMoveEnd[1] - position[1]) == 1){
					 if (next[0] - position[0] == 1 && next[1] == lastMoveEnd[1])
						 return true;
				 	} 
			 }
		 }
		return false;
	}
}















