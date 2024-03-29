package siberia;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChessApplet extends JFrame implements Player, ActionListener {

	private static final long serialVersionUID = 1L;
	private JLayeredPane layers;
	private DisplayBoard dboard;
	private StatusPanel status;
	private PlayerController pc;
	private InstructionsPanel ip;
	private StartWaitPanel swp;

	public ChessApplet() {

		setSize(600, 800);
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
		JButton jb = new JButton("status panel");
		jb.addActionListener(this);
		jb.setActionCommand("status");
		jb.setBounds(0, 550, 150, 50);
		status = new StatusPanel(pc);
		layers.add(jb, JLayeredPane.DEFAULT_LAYER);
		layers.add(status, new Integer(-1));
		final JOptionPane optionPane = new JOptionPane(
				"Are you sure you wish to forfeit the game?\n",
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION);


		ip = new InstructionsPanel();
		layers.add(ip, new Integer(-2));
		jb = new JButton("Instructions");
		jb.setBounds(150, 550, 150, 50);
		jb.addActionListener(this);

		layers.add(jb, JLayeredPane.DEFAULT_LAYER);
		jb = new JButton("forfeit");
		optionPane.setVisible(true);
		final ChessApplet f = this;
		jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pc.isOver()) {
					return;
				}
				int x = JOptionPane.showConfirmDialog(f, "Are you sure you wish to forfeit the game?");
				if (x == 0) {
					// confirm forfeit
					pc.forfeit(f);
					//pc.makeMove(f, "1 1 1 1");
					//pc.over();
				} else {
				//	JOptionPane.showMessageDialog(f, "You may be able to still win this, if your name is Kasparov");
				}
			}
		});

		jb.setBounds(300, 550, 150, 50);
		layers.add(jb, JLayeredPane.DEFAULT_LAYER);


		swp = new StartWaitPanel(np, pc);
		layers.add(swp, new Integer(50));
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


	}

	public void checkMove(String move) {
		//boolean success = pc.getBoard().movePiece(begin,end);

		boolean garbled = pc.getBoard().isGarbled(move);
		if (!garbled) {
			int[][] movei = pc.getBoard().stringToMove(move);
			String color = pc.getWhite().equals(this) ? "white" : "black";
			System.out.println(color);
			boolean success = pc.getBoard().validateMove(movei[0], movei[1], color);
			if (!success) {
				JOptionPane.showMessageDialog(this, "I am sorry to inform you. That move was invalid");
			} else {
				pc.makeMove(this, move);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Garbled");
		}
		dboard.update();

	}

	public void putMove(String move) {
		dboard.update();
		String color = pc.getWhite().equals(this) ? "white" : "black";
		if (pc.getBoard().isThereCheck(color)) {
			JOptionPane.showMessageDialog(this, "You are in check");
		}
	}

	public boolean ready() {
		return true;

	}

	public void gameStart() {
		layers.setLayer(swp, new Integer(-9999));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("status")) {
			if (layers.getLayer(status) > layers.getLayer(dboard)) {
				layers.setLayer(status, new Integer(-1));
			} else {
				layers.setLayer(status, JLayeredPane.POPUP_LAYER);
			}
			status.update();
		} else {
			if (layers.getLayer(ip) > layers.getLayer(dboard)) {
				layers.setLayer(ip, new Integer(-1));
			} else {
				layers.setLayer(ip, JLayeredPane.POPUP_LAYER);
			}
		}
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				ChessApplet stupid = new ChessApplet();
				stupid.setVisible(true);
			}
		});

	}

	public void gameOver(boolean win) {
		dboard.update();
		if(win){
			JOptionPane.showMessageDialog(this, "Congrats, you win");
		} else {
			JOptionPane.showMessageDialog(this, "You have lost");
		}
	}
}
