/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package siberia;

import Network.*;

/**
 *
 * @author tradams
 */
public class NetworkPlayer implements Player,MoveListener{
    private ChatClient cc;
    private PlayerController pc;
	boolean gamestart = false;
    public NetworkPlayer(PlayerController pc){
        cc = new ChatClient();
        cc.init();
        cc.addListener(this);
        this.pc = pc;
    }
    public void putMove(String move) {
        cc.write(move);
    }
   
   public void gameStart(){
	   gamestart = true;
   }
   public boolean ready() {
        if(cc.getParter() != null){
            return true;
        } else {
            return false;
        }
    }

    public void move(String move) {
		if(move == null) return;
		if(move.equals("POSITION:WHITE")){
			pc.setWhitePlayer(this);
		} else {
			if(gamestart){
				pc.makeMove(this, move);
			}
		}
    }

	public void gameOver(boolean win) {

		putMove((win)?"1 1 1 1":"2 2 2 2");
	}
}
