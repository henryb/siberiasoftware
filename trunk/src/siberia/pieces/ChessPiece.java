package siberia.pieces;

public abstract class ChessPiece {

    public String color;
    public boolean hasMoved;
    private String image;

    public ChessPiece(String color, String image) {
        this.image = image;
        this.color = color;
        hasMoved = false;
    }

    public String getImage() {
        return image;
    }

	public abstract boolean verify_move(int[][] board, int[] position, int[] next);

    protected boolean canDefeat(String color, int pieceID) {
        if (color == "white") {
            if (pieceID > 8 || pieceID == 0) {
                return true;
            }
        }
        if (color == "black") {
            if ((pieceID > 1 && pieceID < 8) || pieceID == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean validate_passant(int[][] board, int[] position, int[] next, int[][] lastMove) {
        return false;
    }

    public boolean isThereCheck(int[][] board, int[] target) {
        return false;
    }

    public boolean isThereCheckMate(int[][] board, int[] position) {
        return false;
    }
}
