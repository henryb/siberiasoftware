/**
 * 
 */
package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import Network.Resolver;
import Network.ChatClient;

/**
 * @author hab34
 *
 */
public class ResolverTest {

	private Resolver subject;
	private Chat chat;
	private Message chatmessage;
	private Message nonchatmessage;
	private ChatClient chatclient;
	private XMPPConnection connection;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		subject = null;
	}

	@Test
	public final void testResolver() {
		subject = new Resolver();
		assertNotNull(subject);
	}

	@Test
	public final void testConnected() {
		subject = new Resolver();
		assertTrue(subject.connected());
	}

	@Test
	public final void testProcessMessage() {
		subject = new Resolver();
		chatmessage = new Message("chat message", Message.Type.chat);
		subject.processMessage(chat, chatmessage);
		nonchatmessage = new Message("chat message", Message.Type.normal);
		subject.processMessage(chat, nonchatmessage);
		assert(true);
	}

	@Test
	public final void testChatCreated() {
		subject = new Resolver();
		boolean local = true;
		subject.chatCreated(chat, local);
	
	    String Username = "siberiaChess";
	    String Password = "siberia";
		ConnectionConfiguration config = new ConnectionConfiguration(
           "duke.cs.drexel.edu");
	    connection = new XMPPConnection(config);

	    try {
	    	connection.connect();
	    	//connection.loginAnonymously();
	    	connection.login(Username, Password);

	    } catch (XMPPException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    
		chatclient = new ChatClient();
		try {
			chatclient.init();
			chatclient.login();
		}
		catch (XMPPException e) {
			e.printStackTrace();
			fail("login to chat client threw XMPPException");
		}
		
		chat = connection.getChatManager().createChat("siberiachess@duke.cs.drexel.edu", chatclient);			

		subject.chatCreated(chat, !local);
		chat = connection.getChatManager().createChat("rescuerangers@duke.cs.drexel.edu", chatclient);			

		subject.chatCreated(chat, !local);
		
		assert(true);
	}

}
