/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siberia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tradams
 */
public class DragBoard extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	private BoardLocation start;
	private Board board;
	private JLabel imageLabel;
	private ChessApplet applet;
	private Icon old;
	private boolean white;

	public DragBoard(Board brd, ChessApplet ca, boolean w) {
		white = w;
		board = brd;
		start = null;
		imageLabel = new JLabel();
		applet = ca;
		this.setBounds(0, 0, 68, 68);
		this.setVisible(false);
		this.add(imageLabel);
		this.setOpaque(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		//System.out.println("released");
		//start = null;
	}

	public void mousePressed(MouseEvent e) {
		imageLabel.setIcon(null);
		this.setVisible(false);
		start.setIcon(old);
		applet.checkMove(start.toString() + " " + board.getLocation((e.getY() / 68), (e.getX() / 68)).toString());
		start = null;
		old = null;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		//System.out.println("Entered");
	}

	public void mouseExited(MouseEvent e) {
		imageLabel.setIcon(null);
		this.setVisible(false);
		if (start != null) {
			start.setIcon(old);
		}
		old = null;
		start = null;
		//System.out.println("Exit");
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {

		imageLabel.setBounds(e.getX() - 34, e.getY() - 34, 68, 68);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof BoardLocation) {
			BoardLocation bl = (BoardLocation) source;
			if (bl.getPiece() != null && (bl.getPiece().color.equals("white") == white)) {

				start = bl;
				old = start.getIcon();
				if (bl.getPiece() != null) {
					start.setIcon(null);
					imageLabel.setIcon(new ImageIcon(bl.getPiece().getImage()));
					this.setVisible(true);
				}
			}
		}
	}
}
