package Siberia;


public class Rook extends ChessPiece {
	
	public boolean hasMoved;
	
	public Rook(String BorW){	
		color = BorW;
		hasMoved = false;
	}
	
	public boolean validate_move(int[][] board, int[] position, int[] next){
		
		if(!canDefeat(color, board[next[0]][next[1]])){
			return false;
		}
		
		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];
			
		int RowIncr = 0;
		int ColIncr = 0;
		boolean validVector = false;
			
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
				validVector = true;
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
				validVector = true;
			}
			
			if (validVector){
				int testRow = position[0] + RowIncr;
				int testCol = position[1] + ColIncr;
				while (!(testRow == next[0] && testCol == next[1]) && board[testRow][testCol] == 0)
				{	
					testRow = testRow + RowIncr;
					testCol = testCol + ColIncr;				
				}
				if (testRow == next[0] && testCol == next[1])
					hasMoved = true;
					return true;
			}
			return false;
	}
		
}
