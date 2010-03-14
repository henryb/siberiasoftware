package Siberia;


public abstract class ChessPiece {
	
	public String color;
	public boolean hasMoved;

	public boolean verify_move(int[][] board, int[] position, int[] next){
		return false;
	}
	
	protected boolean canDefeat(String color, int pieceID){
		if (color == "white"){
			if ( pieceID > 8 || pieceID == 0 ){
				return true;
			}
		}
		if (color == "black"){
			if ( (pieceID > 1 && pieceID < 8) || pieceID == 0 ){
				return true;
			}
		}
		return false;		
	}
	
	public boolean validate_passant(int[][] board, int[] position, int[] next, int[][] lastMove){
		return false;
	}
	
	public boolean isThereCheck(int[][] board, int[]target){
		return false;
	}
	
	public boolean isThereCheckMate(int[][] board, int[] position){
		return false;
	}
	
	public String getColor(){
		return color;
	}

}
