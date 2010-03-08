package siberia;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChessApplet extends JApplet implements  Player, ActionListener {

	private JLayeredPane layers;
	private DisplayBoard dboard;
	private StatusPanel status;
	private PlayerController pc;

	@Override
	public void init() {

		setSize(544, 544);
		pc = new PlayerController();
		NetworkPlayer np = new NetworkPlayer(pc);
		pc.addPlayer(this); //white
		pc.addPlayer(np); // black

		layers = new JLayeredPane();
		this.getContentPane().add(layers);

		dboard = new DisplayBoard(pc, this);

		dboard.setPreferredSize(new Dimension(544, 544));
		dboard.setBounds(0, 0, 544, 544);
		layers.add(dboard, JLayeredPane.DEFAULT_LAYER);
		JButton jb = new JButton("display status panel");
		jb.addActionListener(this);
		jb.setBounds(0, 550, 300, 50);
		status = new StatusPanel(pc);
		layers.add(jb, JLayeredPane.DEFAULT_LAYER);
		layers.add(status, new Integer(-1));
		final JOptionPane optionPane = new JOptionPane(
				"Are you sure you wish to forfeit the game?\n",
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION);
		jb = new JButton("forfeit");
		optionPane.setVisible(true);
		final ChessApplet f = this;
		jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(f, "Are you sure you wish to forfeit the game?");
				if (x == 0) {
					// confirm forfeit
					pc.makeMove(f, "1 1 1 1");
				} else {
					JOptionPane.showMessageDialog(f, "You may be able to still win this, if your name is Kasparov");
				}
			}
		});

		jb.setBounds(300, 550, 300, 50);
		layers.add(jb, JLayeredPane.DEFAULT_LAYER);

	}

	@Override
	public void destroy() {
	}

	public void checkMove(String move) {
		//boolean success = pc.getBoard().movePiece(begin,end);

		boolean garbled = pc.getBoard().isGarbled(move);
		if (!garbled) {
			int[][] movei = pc.getBoard().stringToMove(move);
			boolean success = pc.getBoard().validateMove(movei[0], movei[1]);
			if (!success) {
				JOptionPane.showMessageDialog(this, "You may be retarded, what makes you think that move is valid?");
			} else {
				pc.makeMove(this, move);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Garbled");
		}
		dboard.update();
	}


	public void putMove(String move) {

		if (move.equals("1 1 1 1")) {
			JOptionPane.showMessageDialog(this, "Game over?");
			pc.makeMove(this, move);
		}
		dboard.update();
	}

	public boolean ready() {
		return true;

	}

	public void actionPerformed(ActionEvent e) {
		if (layers.getLayer(status) > layers.getLayer(dboard)) {
			layers.setLayer(status, new Integer(-1));
		} else {
			layers.setLayer(status, JLayeredPane.POPUP_LAYER);
		}
		status.update();
	}
}
