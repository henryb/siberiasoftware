package siberia.tests;

import static org.junit.Assert.*;

import org.jivesoftware.smack.XMPPException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.ChatClient;

public class ChatClientTest {
	
	private ChatClient subject;
	private ChatClient another;

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
		another = new ChatClient();
		another.init();
		assertNull(result);
		try {
			subject.login();
			another.login();
		}
		catch (XMPPException e){
			e.printStackTrace();
		}
		result = subject.getParter();
		another.disconnect();
		assertNotNull(result);
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
		another = new ChatClient();
		another.init();
		try {
			subject.login();
			another.login();
		}
		catch (XMPPException e){
			e.printStackTrace();
		}
		another.disconnect();
		assertTrue(subject.write("testing"));
	}

	@Test
	public final void testDisconnect() {
		subject.disconnect();
		assert(true);
	}

	@Test
	public final void testProcessPacket() {
		fail("Not yet implemented"); // TODO
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
