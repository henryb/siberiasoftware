package siberia;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import Network.*;
import java.awt.event.ActionListener;

public class ChessApplet extends JApplet implements MoveListener, Player, ActionListener {

	ChatClient cc;
	private JLayeredPane layers;
	private DisplayBoard dboard;
	private StatusPanel status;
	private PlayerController pc;

	@Override
	public void init() {

		setSize(544, 544);

		//content.setBackground(Color.GRAY);
		//content.setLayout(null);
		pc = new PlayerController();
		NetworkPlayer np = new NetworkPlayer(pc);
			//pc.addPlayer(np); // black
				pc.addPlayer(this); //white
				pc.addPlayer(np); // black





		//cc = new ChatClient();
	//	cc.init();
	//	cc.addListener(this);


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

	}

	@Override
	public void destroy() {
		cc.send("DEATH");
		System.out.println("FOOBAR");
		//cc.disconnect();
	}

	public void checkMove(String move) {
		System.out.println("Move received: " + move);
		//boolean success = pc.getBoard().movePiece(begin,end);
		boolean garbled = pc.getBoard().isGarbled(move);
		if (!garbled) {
			int[][] movei = pc.getBoard().stringToMove(move);
			boolean success = pc.getBoard().validateMove(movei[0], movei[1]);
			if (!success) {
				JOptionPane.showMessageDialog(this, "You may be retarded, what makes you think that move is valid?");
			} else {
				pc.makeMove(this,move);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Garbled");
		}

		dboard.update();
	}

	@Override
	public void move(String move) {
		System.out.println("Move received: " + move);
	}

	public void putMove(String move) {
		//trigger update of board
		if(move.equals("1 1 1 1")){
			JOptionPane.showMessageDialog(this, "Game over?");
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
