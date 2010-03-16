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
	   
   }
   public boolean ready() {
        if(cc.getParter() != null){
            return true;
        } else {
            return false;
        }
    }

    public void move(String move) {
		
		if(move.equals("POSITION:WHITE")){
			pc.setWhitePlayer(this);
		} else {
			pc.makeMove(this, move);
		}
    }

	public void gameOver(boolean win) {

		putMove((win)?"1 1 1 1":"2 2 2 2");
	}
}
