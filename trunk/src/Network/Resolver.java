package Network;

import java.util.*;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

public class Resolver implements MessageListener, ChatManagerListener {

    private String Username = "siberiaChess";
    private String Password = "siberia";
    protected XMPPConnection connection;
    protected List<Chat> clients;
    private boolean success = false;

    public Resolver() {
        ConnectionConfiguration config = new ConnectionConfiguration(
                "duke.cs.drexel.edu");
        connection = new XMPPConnection(config);
        clients = new LinkedList<Chat>();
        try {
            connection.connect();
            //connection.loginAnonymously();
            connection.login(Username, Password);

        } catch (XMPPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        success = connection.isAuthenticated();
        connection.getChatManager().addChatListener(this);
    }

    public boolean connected() {
        return success;
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        if (message.getType() == Message.Type.chat) {
            System.out.println("Why are you messaging me?");
        }
    }

    @Override
    public void chatCreated(Chat chat, boolean local) {
        if (local == false) {
            if (clients.size() >= 1) {
                // ok we have more than 1 person here
                Chat c = clients.remove(0);
                String first = c.getParticipant();
                first = first.substring(0, first.indexOf('@'));
                String second = chat.getParticipant();
                second = second.substring(0, second.indexOf('@'));

                //Chat c = connection.getChatManager().createChat(first, this);
                System.out.println(c.getParticipant());
                //dChat c2 = connection.getChatManager().createChat(second, this);
                try {
                    System.out.println(first + "   " + second);
                    c.sendMessage(second);
                    chat.sendMessage(first);
					chat.sendMessage("POSITION:WHITE");

                } catch (XMPPException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                clients.add(chat);
            }
        }

    }

    public static void main(String args[]) {
        // turn on the enhanced debugger
        XMPPConnection.DEBUG_ENABLED = false;
        Resolver r = new Resolver();



        if (r.connected()) {
            while (true) {
            }
        }

    }
}
