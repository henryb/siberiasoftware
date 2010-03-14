package Siberia;


public class Queen extends ChessPiece {

	public Queen(String BorW){	
		color = BorW;		
	}
	
	public boolean verify_move(int[][] board, int[] position, int[] next){
			
		if(!canDefeat(color, board[next[0]][next[1]])){
			return false;
		}
		
		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];
			
		int RowIncr = 0;
		int ColIncr = 0;
		boolean RookVector = false;
		boolean BishopVector = false;
			
		//going up/down
		if (ColDiff == 0){
			if (RowDiff > 0){
				RowIncr = 1;
				ColIncr = 0;
			}
			else{
				RowIncr = -1;
				ColIncr = 0;
			}
			RookVector = true;
		}			
		//going left/right
		if (RowDiff == 0){
			if (ColDiff > 0){
				RowIncr = 0;
				ColIncr = 1;
			}
			else{
				RowIncr = 0;
				ColIncr = -1;
			}
			RookVector = true;
		}
		if (!RookVector){
										
			if (Math.abs(RowDiff/ColDiff) != 1)
				return false;			
			BishopVector = true;
			
			if (RowDiff > 0)
				RowIncr = 1;
			else
				RowIncr = -1;
			
			if (ColDiff > 0)
				ColIncr = 1;
			else
				ColIncr = -1;
		}
			
		if (RookVector || BishopVector){
			int testRow = position[0] + RowIncr;
			int testCol = position[1] + ColIncr;
			while (!(testRow == next[0] && testCol == next[1]) && board[testRow][testCol] == 0)
			{	
				testRow = testRow + RowIncr;
				testCol = testCol + ColIncr;				
			}
			if (testRow == next[0] && testCol == next[1])
				return true;
		}
		return false;
	}
					
}
