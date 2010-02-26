import java.util.*;
import java.io.*;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.*;
import org.jivesoftware.smack.packet.*;

/*
 * idea, connect to a running bot, i.e. the server bot it pairs the participants and thats all
 * i.e. if num of chats > 1 and a new chat then send the names to each of the two chatters.
 */

public class ChatClient implements MessageListener, PacketListener, MoveListener {
	private String master = "siberiachess@duke.cs.drexel.edu";
	private XMPPConnection connection;
	private String partner = null;
	private List<MoveListener> observers;

	public ChatClient() {
		observers = new LinkedList<MoveListener>();
	}

	public void addListener(MoveListener ml) {
		if (ml != null) {
			observers.add(ml);
		}
	}

	protected void notifyObservers(String move) {
		for (MoveListener ml : observers) {
			ml.move(move);
		}
	}

	public void login() throws XMPPException {
		ConnectionConfiguration config = new ConnectionConfiguration(
				"duke.cs.drexel.edu");
		connection = new XMPPConnection(config);

		connection.connect();
		connection.loginAnonymously();
		System.err.println(connection.getUser());
	}

	public boolean write(String move) {
		boolean retval = true;
		if (partner != null) {
			try {
				this.sendMessage(move, this.partner);
			} catch (XMPPException e) {
				retval = false;
			}
		} else {
			retval = false;
		}
		return retval;
	}

	private void sendMessage(String message, String to) throws XMPPException {
		Chat chat = connection.getChatManager().createChat(to, this);
		chat.sendMessage(message);
	}

	public void disconnect() {
		connection.disconnect();
	}

	public void processPacket(Packet packet) {
		Message m = (Message) packet;
		String from = m.getFrom().substring(0, m.getFrom().indexOf('/'));
		if (from.equals(master)) {
			// a message from MASTER
			if (m.getBody().equals("PING")) {
				try {
					this.sendMessage("PONG", master);
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				this.partner = m.getBody() + "@duke.cs.drexel.edu";
				System.err.println("Partner is now: " + partner);
				try {
					this.sendMessage("Hey I can talk to you", this.partner);
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			if (from.equals(this.partner)) {
				this.notifyObservers(m.getBody());
			}
			// /System.err.println("Someone else(" + m.getFrom() + ") said: " +
			// m.getBody());
		}
	}

	public void processMessage(Chat chat, Message message) {
		return;

		// System.out.println(chat.getParticipant() + " says: " +
		// message.getBody());
	}

	public boolean init() {
		boolean retval = true;
		try {
			login();
			connection.addPacketListener(this, new PacketTypeFilter(
					Message.class));
			sendMessage("EHLO", master);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retval = false;
		}
		return retval;

	}

	public static void main(String args[]) throws XMPPException {
		ChatClient c = new ChatClient();

		// provide your login information here
		c.init();
		c.addListener(c);
		boolean write = false;
		while (true) {
			write = c.write("MOVEMOVEMOVE");
		}

	}

	@Override
	public void move(String move) {
		System.out.println(move);
		
	}

}