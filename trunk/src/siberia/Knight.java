package Siberia;


public class Knight extends ChessPiece {

	public Knight(String BorW){
		color = BorW;		
	}
	
	public boolean verify_move(int[][] board, int[] position, int[] next){
		
		if(!canDefeat(color, board[next[0]][next[1]])){
			return false;
		}
		
		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];
		
		int[][] possibleMoves = new int[][]{{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
		
		for (int i=0; i < possibleMoves.length ; i++){
        	int[] move = possibleMoves[i];        	
        	if (RowDiff == move[0] && ColDiff == move[1]){
        		return true;
        	}
		}
		
		return false;
		 
		}
	
}
