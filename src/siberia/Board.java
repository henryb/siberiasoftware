package siberia;

import java.awt.GridLayout;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {

	private BoardLocation[][] board;
	private PlayerController controller;
	private Image img;
	private boolean status;

	public Board(PlayerController pc, boolean white) {
		this.setLayout(new GridLayout(8, 8));
		controller = pc;
		board = new BoardLocation[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int ni = (white) ? i : 7 - i;
				int nj = (white) ? j : 7 - j;
				board[i][j] = new BoardLocation(this, ni, nj);
				this.add(board[i][j]);
			}
		}
		img = new ImageIcon("/home/tradams/NetBeansProjects/SiberiaSoftware/resources/board.jpg").getImage();
		status = white;

	}

	public BoardLocation getLocation(int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			return board[x][y];
		} else {
			return null;
		}
	}

	public void setDragBoard(DragBoard db) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].addActionListener(db);
			}
		}
	}

	public void update(boolean white) {
		if (status != white) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					int ni = (white) ? i : 7 - i;
					int nj = (white) ? j : 7 - j;
					board[i][j].setLoc(ni,nj);
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int ni = (white) ? i : 7 - i;
				int nj = (white) ? j : 7 - j;
				board[i][j].setPiece(controller.getBoard().getPieceAt(ni, nj));
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
