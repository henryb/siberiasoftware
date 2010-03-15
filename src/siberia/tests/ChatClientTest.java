package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Network.ChatClient;

public class ChatClientTest {
	
	private ChatClient subject;

	@Before
	public void setUp() throws Exception {
		subject = new ChatClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testChatClient() {
		assertNotNull(subject);
	}

	@Test
	public final void testGetParter() {
		String result = subject.getParter();
		
		assertNull(result);
	}

	@Test
	public final void testSend() {
		String move = "testing";
		
		boolean result = subject.send(move);
		assertTrue(result);
	}

	@Test
	public final void testAddListener() {
		
	}

	@Test
	public final void testNotifyObservers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWrite() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDisconnect() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testProcessPacket() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testProcessMessage() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInit() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testMove() {
		fail("Not yet implemented"); // TODO
	}

}
