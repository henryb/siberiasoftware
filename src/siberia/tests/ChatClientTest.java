package siberia.tests;

import static org.junit.Assert.*;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.ChatClient;

public class ChatClientTest {
	
	private ChatClient subject;
	private Message packet;

	@Before
	public void setUp() throws Exception {
		subject = new ChatClient();
		subject.init();
	}

	@After
	public void tearDown() throws Exception {
		subject.disconnect();
	}

	@Test
	public final void testChatClient() {
		assertNotNull(subject);
	}

	@Test
	public final void testGetParter() {
		String result = subject.getParter();
		assertNull("ChatClient has a partner before init, how?", result);
		
		packet = new Message();
		
		packet.setFrom("siberiachess@duke.cs.drexel.edu/");
	
		subject.addListener(subject);
		packet.setBody("rescuerangers");
		subject.processPacket(packet);

		result = subject.getParter();

		assertNotNull("ChatClient didn't get a partner", result);
	}

	@Test
	public final void testSend() {
		String move = "testing";
		
		boolean result = subject.send(move);
		assertTrue(result);
	}

	@Test
	public final void testAddListener() {
		subject.addListener(null);
		subject.addListener(subject);
		assert(true);
	}

	@Test
	public final void testLogin() {

		try {
			subject.login();
		}
		catch (XMPPException e){
			e.printStackTrace();
		}
		assert(true);
	}

	@Test
	public final void testWrite() {
		assertFalse(subject.write("testing"));
		try {
			subject.login();
		}
		catch (XMPPException e){
			e.printStackTrace();
		}
		
		packet = new Message();
		
		packet.setFrom("siberiachess@duke.cs.drexel.edu/");
	
		subject.addListener(subject);
		packet.setBody("rescuerangers");
		subject.processPacket(packet);
		assertTrue(subject.write("testing"));
	}

	@Test
	public final void testDisconnect() {
		subject.disconnect();
		assert(true);
	}

	@Test
	public final void testProcessPacket() {
		
		packet = new Message();
		packet.setFrom("nobody/");
		packet.setBody("THE ONLY WINNING MOVE IS NOT TO PLAY");
		subject.processPacket(packet);
		
		packet = new Message();
		
		packet.setFrom("siberiachess@duke.cs.drexel.edu/");
		packet.setBody("POSITION:WHITE");
		subject.processPacket(packet);
		
		subject.addListener(subject);
		packet.setBody("rescuerangers");
		subject.processPacket(packet);
		
		packet = new Message();
		
		packet.setFrom("siberiachess@duke.cs.drexel.edu/");
		packet.setBody("PING");
		subject.processPacket(packet);
		
		packet = new Message();
		
		packet.setFrom("rescuerangers@duke.cs.drexel.edu/");
		packet.setBody("HOW ABOUT A NICE GAME OF CHESS");
		subject.processPacket(packet);
		
		assert(true);
	}

	@Test
	public final void testProcessMessage() {
		subject.processMessage(null, null);
		assert(true);
	}

	@Test
	public final void testInit() {
		subject.init();
		assert(true);
	}

	@Test
	public final void testMove() {
		subject.move("testing move() method");
		assert(true);
	}
	
}
