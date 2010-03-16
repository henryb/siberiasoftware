package siberia;

public class PlayerController {

	private boolean over = false;
	private Player white;
	private Player black;
	private ChessBoard game;
	private boolean whiteToPlay = true;
	// resets the timer and stuff.

	public PlayerController() {
		game = new ChessBoard();
		game.configureBoard();
	}

	public void forfeit(Player p) {
		Player op = (white.equals(p)) ? black : white;
		p.gameOver(false);
		op.gameOver(true);
		over();
	}

	public void makeMove(Player p, String move) {
		if(p == null || move == null) return;
		if(white == null || black == null) return;
		if (move.equals("1 1 1 1") || move.equals("2 2 2 2")) {
			//forfeit
			Player op = (white.equals(p)) ? black : white;
			boolean pwin = move.equals("1 1 1 1") ? false : true;
			op.gameOver(!pwin);
			over();
		}


		if (over) {
			return;
		}
		System.out.println(p.toString() + " " + move + " " + Boolean.toString(whiteToPlay));
		if (whiteToPlay && p.equals(white)) {
			if (!game.isGarbled(move)) {

				whiteToPlay = false;
				game.decodeMove(move);
				black.putMove(move);
				game.printBoard();
				if (game.isThereCheck("black") && game.isThereCheckMate("black")) {
					over = true;
					white.gameOver(true);
					black.gameOver(false);
				}
			} else {
				System.err.println("Garbled move recieved: " + move);
			}
		} else if (p.equals(black) && !whiteToPlay) {
			if (!game.isGarbled(move)) {

				whiteToPlay = true;
				game.decodeMove(move);
				white.putMove(move);
				game.printBoard();
				if (game.isThereCheck("white") && game.isThereCheckMate("white")) {
					over = true;
					white.gameOver(false);
					black.gameOver(true);
				}
			} else {
				System.err.println("Garbled move recieved: " + move);
			}
		}
	}

	public ChessBoard getBoard() {
		return game;
	}

	public void start() {
		if (white != null && black != null) {
			white.gameStart();
			black.gameStart();
		}
	}

	public void addPlayer(Player x) {
		if (white == null) {
			white = x;
		} else {
			black = x;
		}
	}

	public void setWhitePlayer(Player x) {
		if (x == null) {
			return;
		}
		if (white != null && black != null) {
			if (black.equals(x)) {
				black = white;
				white = x;
			}
			white.putMove("");
			black.putMove("");
		}
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	public void over() {
		over = true;
	}

	public boolean isOver() {
		return over;
	}
}
