package siberia;

import java.util.*;

public class PlayerController extends TimerTask {

	private Player white;
	private Player black;
	private ChessBoard game;
	private boolean whiteToPlay = true;
	// resets the timer and stuff.

	public PlayerController() {
		game = new ChessBoard();
		game.configureBoard();
	}

	public void makeMove(Player p, String move) {
		System.out.println(p.toString() + " " + move + " " + Boolean.toString(whiteToPlay));
		if (whiteToPlay && p.equals(white)) {


			if (!game.isGarbled(move)) {
				whiteToPlay = !whiteToPlay;
				black.putMove(move);
				game.decodeMove(move);
			} else {
				System.err.println("Garbled move recieved: " + move);
			}
		} else if (p.equals(black)) {

			if (!game.isGarbled(move)) {
				game.decodeMove(move);
				white.putMove(move);
				whiteToPlay = !whiteToPlay;
			} else {
				System.err.println("Garbled move recieved: " + move);
			}
		}
	}

	public ChessBoard getBoard() {
		return game;
	}

	public void addPlayer(Player x) {
		if (white == null) {
			white = x;
		} else {
			black = x;
		}
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	@Override
	public void run() {
		if (whiteToPlay) {
			//white forfeits
		} else {
			//black forfeits
		}

	}
}
