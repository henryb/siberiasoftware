package siberia;

import siberia.pieces.ChessPiece;
import siberia.pieces.King;
import siberia.pieces.Knight;
import siberia.pieces.Pawn;
import siberia.pieces.Queen;
import siberia.pieces.Rook;
import siberia.pieces.Bishop;

public class ChessBoard {

	private String path = "/home/tradams/NetBeansProjects/SiberiaSoftware/resources/";
	private int[][] board;
	private ChessPiece[] gamePieces;
	private int[] WKingPos;
	private int[] BKingPos;
	// lastMove[0]: start coordinates of the last move
	// lastMove[1]: end coordinates of the last move
	private int[][] lastMove;

	public ChessBoard() {
		board = new int[8][8];

		// initialize 'lastMove' array, which will have valid values after 1st move is made
		lastMove = new int[][]{{0, 0}, {0, 0}};

		gamePieces = new ChessPiece[15];
		gamePieces[1] = new King("white", path + "whiteking.jpg");
		gamePieces[2] = new Queen("white", path + "whitequeen.jpg");
		gamePieces[3] = new Rook("white", path + "whiterook.jpg");
		gamePieces[4] = new Rook("white", path + "whiterook.jpg");
		gamePieces[5] = new Knight("white", path + "whiteknight.jpg");
		gamePieces[6] = new Bishop("white", path + "whitebishop.jpg");
		gamePieces[7] = new Pawn("white", path + "whitepawn.jpg");
		gamePieces[8] = new King("black", path + "blackking.jpg");
		gamePieces[9] = new Queen("black", path + "blackqueen.jpg");
		gamePieces[10] = new Rook("black", path + "blackrook.jpg");
		gamePieces[11] = new Rook("black", path + "blackrook.jpg");
		gamePieces[12] = new Knight("black", path + "blackknight.jpg");
		gamePieces[13] = new Bishop("black", path + "blackbishop.jpg");
		gamePieces[14] = new Pawn("black", path + "blackpawn.jpg");

	}

	public ChessPiece getPieceAt(int x, int y) {
		int pieceID = board[x][y];
		return gamePieces[pieceID];
	}

	public void configureBoard() {

		board[0] = new int[]{10, 12, 13, 9, 8, 13, 12, 11};
		board[1] = new int[]{14, 14, 14, 14, 14, 14, 14, 14};

		// 0 corresponds to empty space
		for (int i = 2; i <= 5; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		board[6] = new int[]{7, 7, 7, 7, 7, 7, 7, 7};
		board[7] = new int[]{3, 5, 6, 2, 1, 6, 5, 4};

		BKingPos = new int[]{0, 4};
		WKingPos = new int[]{7, 4};

	}

	public void printBoard() {
		System.out.print("Current Configuration is: ");

		for (int i = 0; i < board.length; i++) {
			System.out.print("\n");
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] < 10) {
					System.out.print("  " + board[i][j] + " ");
				} else {
					System.out.print(" " + board[i][j] + " ");
				}
			}
		}
		System.out.print("\n");
	}

	public void applyMove(int[] current, int[] next) {

		int pieceID = board[current[0]][current[1]];

		// Special Move: En Passant
		if (pieceID == 7 || pieceID == 14) {
			if (current[1] != next[1] && board[next[0]][next[1]] == 0) {
				board[current[0]][next[1]] = 0;
			}
		}

		// Special Move: Castling
		if ((pieceID == 1 || pieceID == 8) && Math.abs(current[1] - next[1]) == 2) {
			// White King, Queenside castling
			if (pieceID == 1 && next[1] == 2) {
				board[7][3] = 3;
				board[7][0] = 0;
			}
			// White King, Kingside castling
			if (pieceID == 1 && next[1] == 6) {
				board[7][5] = 4;
				board[7][7] = 0;
			}
			// Black King, Queenside castling
			if (pieceID == 8 & next[1] == 2) {
				board[0][3] = 10;
				board[0][0] = 0;
			}
			// Black King, Kingside castling
			if (pieceID == 8 && next[1] == 6) {
				board[0][5] = 11;
				board[0][7] = 0;
			}
		}

		// Common for all moves
		board[next[0]][next[1]] = pieceID;
		board[current[0]][current[1]] = 0;
		lastMove[0] = current;
		lastMove[1] = next;

		// Promotion
		if (pieceID == 7 && next[0] == 0) {
			board[next[0]][next[1]] = 2;
		}
		if (pieceID == 14 && next[0] == 7) {
			board[next[0]][next[1]] = 9;
		}

		// Set King Position
		if (pieceID == 1) {
			WKingPos = next;
		}
		if (pieceID == 8) {
			BKingPos = next;
		}

		// Set hasMoved Boolean for King or Rook
		if (pieceID == 1 || pieceID == 8 || pieceID == 3 || pieceID == 4 || pieceID == 10 || pieceID == 11) {
			gamePieces[pieceID].hasMoved = true;
		}
	}

	public boolean validateMove(int[] current, int[] next) {

		if (current == next) {
			return false;
		}
		this.printBoard();
		ChessPiece myPiece = this.getPieceAt(current[0], current[1]);
		if (myPiece.verify_move(board, current, next)) {
			return true;
		}

		if (this.verifySpecialMove(current, next)) {
			return true;
		}

		return false;
	}

	// Set 'color' argument to "white" to see if White King is threatened, set to "black" to see if Black King is threatened
	public boolean isThereCheck(String color) {

		if (color.equals("white")) {
			return gamePieces[1].isThereCheck(board, WKingPos);
		} else {
			return gamePieces[8].isThereCheck(board, BKingPos);
		}
	}

	// This function should be called after the 'isThereCheck' function returns true
	// Set 'color' argument to "white" to see if White King is threatened, set to "black" to see if Black King is threatened
	public boolean isThereCheckMate(String color) {

		if (color.equals("white")) {
			return gamePieces[1].isThereCheckMate(board, WKingPos);
		} else {
			return gamePieces[8].isThereCheckMate(board, BKingPos);
		}
	}

	private boolean verifySpecialMove(int[] current, int[] next) {

		int pieceID = board[current[0]][current[1]];

		// En Passant
		if (pieceID == 7 || pieceID == 14) {
			if (current[1] != next[1] && board[next[0]][next[1]] == 0) {
				if (gamePieces[pieceID].validate_passant(board, current, next, lastMove)) {
					return true;
				}
			}
		}
		//Castling
		if ((pieceID == 1 || pieceID == 8) && Math.abs(current[1] - next[1]) == 2) {
			if (check_castling(pieceID, next)) {
				return true;
			}
		}
		return false;
	}

	public String convertMove() {
		int[] startPos = lastMove[0];
		int[] endPos = lastMove[1];

		String startRow = Integer.toString(startPos[1]);
		String startCol = Integer.toString(startPos[2]);
		String endRow = Integer.toString(endPos[1]);
		String endCol = Integer.toString(endPos[2]);

		String formatted = startRow + " " + startCol + " " + endRow + " " + endCol;
		return formatted;
	}

	public Boolean decodeMove(String move) {

		String[] tokens = move.split("[ ]");
		int startRow = Integer.parseInt(tokens[0]);
		int startCol = Integer.parseInt(tokens[1]);
		int endRow = Integer.parseInt(tokens[2]);
		int endCol = Integer.parseInt(tokens[3]);

		if (startRow == 1 & startCol == 1 & endRow == 1 & endCol == 1) {
			return false;
		} else {
			int[] startPos = new int[]{startRow, startCol};
			int[] endPos = new int[]{endRow, endCol};
			this.applyMove(startPos, endPos);

			lastMove[0] = startPos;
			lastMove[1] = endPos;
			return true;
		}
	}

	public int[][] stringToMove(String move) {

		String[] tokens = move.split("[ ]");
		int startRow = Integer.parseInt(tokens[0]);
		int startCol = Integer.parseInt(tokens[1]);
		int endRow = Integer.parseInt(tokens[2]);
		int endCol = Integer.parseInt(tokens[3]);

		int[] startPos = new int[]{startRow, startCol};
		int[] endPos = new int[]{endRow, endCol};
		int[][] ret = {startPos, endPos};
		return ret;
	}

	public Boolean isGarbled(String move) {

		try {
			String[] tokens = move.split("[ ]");
			int startRow = Integer.parseInt(tokens[0]);
			int startCol = Integer.parseInt(tokens[1]);
			int endRow = Integer.parseInt(tokens[2]);
			int endCol = Integer.parseInt(tokens[3]);

			if (startRow >= 0 & startRow <= 7 & startCol >= 0 & startCol <= 7 & endRow >= 0 & endRow <= 7 & endCol >= 0 & endCol <= 7) {
				return false;
			} else {
				return true;
			}

		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean check_castling(int pieceID, int[] next) {
		ChessPiece myKing = gamePieces[pieceID];
		// White King, Queenside castling
		if (pieceID == 1 && next[0] == 7 && next[1] == 2) {
			if (myKing.hasMoved == false && gamePieces[3].hasMoved == false) {
				if (board[7][1] == 0 && board[7][2] == 0 && board[7][3] == 0) {
					int[] passThrough = new int[]{7, 3};
					if (!myKing.isThereCheck(board, WKingPos) && !myKing.isThereCheck(board, passThrough)) {
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
		if (pieceID == 1 && next[0] == 7 && next[1] == 6) {
			if (myKing.hasMoved == false && gamePieces[4].hasMoved == false) {
				if (board[7][5] == 0 && board[7][6] == 0) {
					int[] passThrough = new int[]{7, 5};
					if (!myKing.isThereCheck(board, WKingPos) && !myKing.isThereCheck(board, passThrough)) {
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
		if (pieceID == 8 && next[0] == 0 && next[1] == 2) {
			if (myKing.hasMoved == false && gamePieces[10].hasMoved == false) {
				if (board[0][1] == 0 && board[0][2] == 0 && board[0][3] == 0) {
					int[] passThrough = new int[]{0, 3};
					if (!myKing.isThereCheck(board, BKingPos) && !myKing.isThereCheck(board, passThrough)) {
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
		if (pieceID == 8 && next[0] == 0 && next[1] == 6) {
			if (myKing.hasMoved == false && gamePieces[11].hasMoved == false) {
				if (board[0][5] == 0 && board[0][6] == 0) {
					int[] passThrough = new int[]{0, 5};
					if (!myKing.isThereCheck(board, BKingPos) && !myKing.isThereCheck(board, passThrough)) {
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
	