/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siberia;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author tradams
 */
public class StartWaitPanel extends JPanel {
	private JLabel connected;
	private JLabel waiting;
	private NetworkPlayer player;
	private PlayerController controller;
	public StartWaitPanel(NetworkPlayer np,PlayerController pc) {
		connected = new JLabel("Connected to opponent: " + np.ready());
		waiting = new JLabel("You are currently waiting for an opponent.");
		this.setBounds(0, 0, 544, 544);
		this.add(waiting);
		this.add(connected);
		player = np;
		controller = pc;
		start();
	}

	public void start() {
		int delay = 1000;   // delay for 5 sec.
		int period = 1000;  // repeat every sec.
		java.util.Timer timer = new java.util.Timer();

		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				System.out.println("tick");
				connected.setText("Connected to opponent: " + player.ready());
				if(player.ready()){
					this.cancel();
					controller.start();
				}
			}
		}, delay, period);
	}
}
