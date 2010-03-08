/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package siberia;

import javax.swing.JLayeredPane;

/**
 *
 * @author tradams
 */
public class DisplayBoard extends JLayeredPane{
	private Board board;
	private ChessApplet applet;
	private PlayerController controller;
	public DisplayBoard(PlayerController pc,ChessApplet ca){
		applet = ca;
		controller = pc;
		board = new Board(pc,pc.getWhite().equals(ca));
		board.setBounds(0,0,544,544);
		DragBoard db = new DragBoard(board,ca,pc);
		db.setBounds(0,0,544,544);
		board.setDragBoard(db);

		this.add(board,JLayeredPane.DEFAULT_LAYER);
		this.add(db,JLayeredPane.DRAG_LAYER);
		board.update(pc.getWhite().equals(ca));
	}

	public void update(){
		board.update(controller.getWhite().equals(applet));
	}
}
