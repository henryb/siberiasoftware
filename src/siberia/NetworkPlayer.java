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
   
    // ready() never gets called, so I'm commenting out for now - Henry
 /*   public boolean ready() {
        if(cc.getParter() != null){
            return true;
        } else {
            return false;
        }
    }*/

    public void move(String move) {
		
		if(move.equals("POSITION:WHITE")){
			pc.setWhitePlayer(this);
		} else {
			pc.makeMove(this, move);
		}
    }
}
