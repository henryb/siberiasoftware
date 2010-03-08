package siberia.pieces;

public class Bishop extends ChessPiece {

	public Bishop(String color, String image) {
		super(color, image);
	}

	public boolean verify_move(int[][] board, int[] position, int[] next) {

		if (!canDefeat(color, board[next[0]][next[1]])) {
			return false;
		}

		int RowDiff = next[0] - position[0];
		int ColDiff = next[1] - position[1];

		if (RowDiff == 0 || ColDiff == 0) {
			return false;
		}
		if (Math.abs(RowDiff / ColDiff) != 1) {
			return false;
		}

		int RowIncr;
		int ColIncr;

		if (RowDiff > 0) {
			RowIncr = 1;
		} else {
			RowIncr = -1;
		}

		if (ColDiff > 0) {
			ColIncr = 1;
		} else {
			ColIncr = -1;
		}

		int testRow = position[0] + RowIncr;
		int testCol = position[1] + ColIncr;
		while (!(testRow == next[0] && testCol == next[1]) && board[testRow][testCol] == 0) {
			testRow = testRow + RowIncr;
			testCol = testCol + ColIncr;
		}
		if (testRow == next[0] && testCol == next[1]) {
			return true;
		}

		return false;

	}
}
